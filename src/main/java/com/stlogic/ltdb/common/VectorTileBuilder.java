package com.stlogic.ltdb.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stlogic.fbgis.vector_tile.GlobalMercator;
import com.stlogic.fbgis.vector_tile.VectorTile;
import com.stlogic.fbgis.vector_tile.VectorTileCommand;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import scala.Tuple2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

public class VectorTileBuilder extends com.stlogic.fbgis.vector_tile.VectorTileBuilder {
    private static final GlobalMercator sMercator = new GlobalMercator();

    private static final Cache<String, int[]> sStartPointXYZ = //
            CacheBuilder.newBuilder() //
                    .expireAfterAccess(10, TimeUnit.MINUTES) //
                    .expireAfterWrite(10, TimeUnit.MINUTES) //
                    .build();

    private static Callable<int[]> startPointXYZCallable(final String tid) {
        return new Callable<int[]>() {
            public int[] call() throws Exception {
                String[] splitted = tid.split("_");
                int zoom = Integer.parseInt(splitted[0]);
                int tx = Integer.parseInt(splitted[1]);
                int ty = Integer.parseInt(splitted[2]);

                int[] tile = sMercator.TMSTileFromGoogleTile(tx, ty, zoom);
                double[] bounds = sMercator.TileLatLonBounds(tile[0], tile[1], zoom);
                double minx = Math.min(bounds[1], bounds[3]);
                double miny = Math.min(bounds[0], bounds[2]);
                double maxx = Math.max(bounds[1], bounds[3]);
                double maxy = Math.max(bounds[0], bounds[2]);

                double[] meters1 = sMercator.LatLonToMeters(miny, minx);
                double[] meters2 = sMercator.LatLonToMeters(maxy, maxx);
                int[] pixel1 = sMercator.MetersToPixels(meters1[0], meters1[1], zoom);
                pixel1 = sMercator.PixelsToRaster(pixel1[0], pixel1[1], zoom);
                int[] pixel2 = sMercator.MetersToPixels(meters2[0], meters2[1], zoom);
                pixel2 = sMercator.PixelsToRaster(pixel2[0], pixel2[1], zoom);

                int startx = Math.min(pixel1[0], pixel2[0]);
                int starty = Math.min(pixel1[1], pixel2[1]);

                return new int[]{startx, starty, zoom};
            }
        };
    }

    public static byte[] compress(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = null;
        GZIPOutputStream gos = null;

        try {
            baos = new ByteArrayOutputStream(1024 * 1024);
            gos = new GZIPOutputStream(baos);
            gos.write(bytes);
            gos.flush();
        } finally {
            try {
                gos.close();
            } catch (Throwable var12) {
            }

            try {
                baos.close();
            } catch (Throwable var11) {
            }

        }

        return baos.toByteArray();
    }

    private static Object getValue(Object value, long count, AggregateType type) {
        switch (type) {
            case SUM:
                return value;
            default:
                Class<?> binding = value.getClass();
                if (Integer.class.equals(binding)) {
                    switch (type) {
                        case AVG:
                            return new Integer((int) ((long) ((Number) value).intValue() / count));
                    }
                } else if (Long.class.equals(binding)) {
                    switch (type) {
                        case AVG:
                            return new Long(((Number) value).longValue() / count);
                    }
                } else if (Float.class.equals(binding)) {
                    switch (type) {
                        case AVG:
                            return new Float(((Number) value).floatValue() / (float) count);
                    }
                } else if (Double.class.equals(binding)) {
                    switch (type) {
                        case AVG:
                            return new Double(((Number) value).doubleValue() / (double) count);
                    }
                }

                return value;
        }
    }

    private static VectorTile.Tile.Value.Builder setValue(Object value) {
        VectorTile.Tile.Value.Builder valueBuilder = VectorTile.Tile.Value.newBuilder();
        Class<?> binding = value.getClass();
        if (String.class.equals(binding)) {
            valueBuilder.setStringValue((String) value);
            return valueBuilder;
        } else if (Boolean.class.equals(binding)) {
            valueBuilder.setBoolValue((Boolean) value);
            return valueBuilder;
        } else if (Integer.class.equals(binding)) {
            valueBuilder.setSintValue((long) ((Number) value).intValue());
            return valueBuilder;
        } else if (Long.class.equals(binding)) {
            valueBuilder.setSintValue(((Number) value).longValue());
            return valueBuilder;
        } else if (Float.class.equals(binding)) {
            valueBuilder.setFloatValue(((Number) value).floatValue());
            return valueBuilder;
        } else if (Double.class.equals(binding)) {
            valueBuilder.setDoubleValue(((Number) value).doubleValue());
            return valueBuilder;
        } else {
            throw new RuntimeException("Unsupported data type: " + binding.getName());
        }
    }

    public static boolean isGeometryType(DataType type) {
        return (type instanceof UserDefinedType) &&
                Geometry.class.isAssignableFrom(((UserDefinedType) type).userClass());
    }

    public static boolean isPointType(DataType type) {
        return (type instanceof UserDefinedType) &&
                type.getClass().getName().contains("PointUDT");
    }

    public static byte[] build(StructType schema, RDD<Row> rdd, String typeName, com.stlogic.fbgis.vector_tile.VectorTileBuilder.AggregateType type,
                               int zoom, int tx, int ty) throws Exception {
//        Geometry tileGeometry = createTileEnvelope(0, 0, 0);

        VectorTile.Tile.Layer.Builder layerBuilder = VectorTile.Tile.Layer.newBuilder();
        layerBuilder.setName(typeName);
        layerBuilder.setExtent(VectorTileCommand.EXTENT);
        layerBuilder.setVersion(1);

        int index = -1;
//        boolean isPoint = false;
        for (StructField field : schema.fields()) {
            if (isGeometryType(field.dataType())) {
                index = schema.indexOf(field);
//                isPoint = isPointType(field.dataType());
                continue;
            }
            layerBuilder.addKeys(field.name());
        }
//        if (isPoint) {
//            layerBuilder.addKeys("longitude");
//            layerBuilder.addKeys("latitude");
//        }

        Map<Object, Integer> values = Maps.newHashMap();
        List<Row> rows = rdd.toJavaRDD().collect();
        long count = 0;
        List<Integer> commands = Lists.newArrayList();
        for (Row row : rows) {
            Geometry geometry = (Geometry) row.get(index);
            while (geometry != null) {
                if (geometry.isEmpty()) {
                    break;
                }
//            if (!geometry.intersects(tileGeometry)) {
//                continue;
//            }
//            logger.info("geometry: " + geometry.toText());
                Object userData = geometry.getUserData();
                long rowCount = 0;
                if (userData instanceof Long) {
                    rowCount = (Long) userData;
                } else if (userData instanceof Tuple2) {
                    rowCount = ((Tuple2<String, Long>) userData)._2();
                }

                VectorTile.Tile.Feature.Builder featureBuilder = VectorTile.Tile.Feature.newBuilder();
                featureBuilder.setId(count);
                featureBuilder.setType(getGeomType(geometry.getClass()));

                boolean outline = false;
                if (userData instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) userData;
                    featureBuilder.addAllGeometry((List<Integer>) map.get("commands"));
                    if (map.containsKey("outline")) {
                        geometry.setUserData(map.get("outline"));
                        outline = true;
                    }
                } else {
                    commands.clear();
                    VectorTileCommand.commands(geometry, 0, 0, commands, new int[]{0, 0});
                    if (commands.size() == 0) {
                        break;
                    }
                    featureBuilder.addAllGeometry(commands);
                }

                int keyIndex = 0;
                for (int i = 0; i < row.length(); i++) {
                    if (i == index) {
                        continue;
                    }
                    try {
                        Object value = row.get(i);
                        if (value == null) {
                            continue;
                        }
                        value = getValue(value, rowCount, type);
                        VectorTile.Tile.Value.Builder valueBuilder = setValue(value);
                        if (valueBuilder == null) {
                            continue;
                        }

                        Integer valueIndex = values.get(value);
                        if (valueIndex == null) {
                            valueIndex = values.size();
                            values.put(value, valueIndex);
                            layerBuilder.addValues(valueBuilder.build());
                        }

                        featureBuilder.addTags(keyIndex);
                        featureBuilder.addTags(valueIndex);
                    } finally {
                        keyIndex++;
                    }
                }
//                if (isPoint) {
//                    String tid = zoom + "_" + tx + "_" + ty;
//                    int[] startXYZ = sStartPointXYZ.get(tid, startPointXYZCallable(tid));
//                    double x = geometry.getCoordinate().x + startXYZ[0];
//                    double y = geometry.getCoordinate().y + startXYZ[1];
//                    double[] pixel = sMercator.RasterToPixels_Double(x, y, zoom);
//                    pixel = sMercator.PixelsToMeters(pixel[0], pixel[1], zoom);
//                    double[] latLon = sMercator.MetersToLatLon(pixel[0], pixel[1]);
//                    for (double value : new double[]{latLon[1], latLon[0]}) {
//                        try {
//                            VectorTile.Tile.Value.Builder valueBuilder = setValue(value);
//                            Integer valueIndex = values.get(value);
//                            if (valueIndex == null) {
//                                valueIndex = values.size();
//                                values.put(value, valueIndex);
//                                layerBuilder.addValues(valueBuilder.build());
//                            }
//
//                            featureBuilder.addTags(keyIndex);
//                            featureBuilder.addTags(valueIndex);
//                        } finally {
//                            keyIndex++;
//                        }
//                    }
//                }
                count++;
                layerBuilder.addFeatures(featureBuilder.build());
                if (outline) {
                    Geometry temp = geometry.getFactory().createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 1)});
                    temp.setUserData(geometry.getUserData());
                    geometry = temp;
                } else {
                    geometry = null;
                }
            }
        }

        VectorTile.Tile.Builder tileBuilder = VectorTile.Tile.newBuilder();
        tileBuilder.addLayers(layerBuilder.build());
        return tileBuilder.build().toByteArray();
    }

    public static byte[] build(StructType schema, Iterator<Row> rows, String typeName, com.stlogic.fbgis.vector_tile.VectorTileBuilder.AggregateType type,
                               int zoom, int tx, int ty) throws Exception {
//        Geometry tileGeometry = createTileEnvelope(0, 0, 0);

        VectorTile.Tile.Layer.Builder layerBuilder = VectorTile.Tile.Layer.newBuilder();
        layerBuilder.setName(typeName);
        layerBuilder.setExtent(VectorTileCommand.EXTENT);
        layerBuilder.setVersion(1);

        int index = -1;
//        boolean isPoint = false;
        for (StructField field : schema.fields()) {
            if (isGeometryType(field.dataType())) {
                index = schema.indexOf(field);
//                isPoint = isPointType(field.dataType());
                continue;
            }
            layerBuilder.addKeys(field.name());
        }
//        if (isPoint) {
//            layerBuilder.addKeys("longitude");
//            layerBuilder.addKeys("latitude");
//        }

        Map<Object, Integer> values = Maps.newHashMap();
        long count = 0;
        List<Integer> commands = Lists.newArrayList();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row == null) {
                continue;
            }
            Geometry geometry = (Geometry) row.get(index);
            while (geometry != null) {
                if (geometry.isEmpty()) {
                    break;
                }
//            if (!geometry.intersects(tileGeometry)) {
//                continue;
//            }
//            logger.info("geometry: " + geometry.toText());
                Object userData = geometry.getUserData();
                long rowCount = 0;
                if (userData instanceof Long) {
                    rowCount = (Long) userData;
                } else if (userData instanceof Tuple2) {
                    rowCount = ((Tuple2<String, Long>) userData)._2();
                }

                VectorTile.Tile.Feature.Builder featureBuilder = VectorTile.Tile.Feature.newBuilder();
                featureBuilder.setId(count);
                featureBuilder.setType(getGeomType(geometry.getClass()));

                boolean outline = false;
                if (userData instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) userData;
                    featureBuilder.addAllGeometry((List<Integer>) map.get("commands"));
                    if (map.containsKey("outline")) {
                        geometry.setUserData(map.get("outline"));
                        outline = true;
                    }
                } else {
                    commands.clear();
                    VectorTileCommand.commands(geometry, 0, 0, commands, new int[]{0, 0});
                    if (commands.size() == 0) {
                        break;
                    }
                    featureBuilder.addAllGeometry(commands);
                }

                int keyIndex = 0;
                for (int i = 0; i < row.length(); i++) {
                    if (i == index) {
                        continue;
                    }
                    try {
                        Object value = row.get(i);
                        if (value == null) {
                            continue;
                        }
                        value = getValue(value, rowCount, type);
                        VectorTile.Tile.Value.Builder valueBuilder = setValue(value);
                        if (valueBuilder == null) {
                            continue;
                        }

                        Integer valueIndex = values.get(value);
                        if (valueIndex == null) {
                            valueIndex = values.size();
                            values.put(value, valueIndex);
                            layerBuilder.addValues(valueBuilder.build());
                        }

                        featureBuilder.addTags(keyIndex);
                        featureBuilder.addTags(valueIndex);
                    } finally {
                        keyIndex++;
                    }
                }
//                if (isPoint) {
//                    String tid = zoom + "_" + tx + "_" + ty;
//                    int[] startXYZ = sStartPointXYZ.get(tid, startPointXYZCallable(tid));
//                    double x = geometry.getCoordinate().x + startXYZ[0];
//                    double y = geometry.getCoordinate().y + startXYZ[1];
//                    double[] pixel = sMercator.RasterToPixels_Double(x, y, zoom);
//                    pixel = sMercator.PixelsToMeters(pixel[0], pixel[1], zoom);
//                    double[] latLon = sMercator.MetersToLatLon(pixel[0], pixel[1]);
//                    for (double value : new double[]{latLon[1], latLon[0]}) {
//                        try {
//                            VectorTile.Tile.Value.Builder valueBuilder = setValue(value);
//                            Integer valueIndex = values.get(value);
//                            if (valueIndex == null) {
//                                valueIndex = values.size();
//                                values.put(value, valueIndex);
//                                layerBuilder.addValues(valueBuilder.build());
//                            }
//
//                            featureBuilder.addTags(keyIndex);
//                            featureBuilder.addTags(valueIndex);
//                        } finally {
//                            keyIndex++;
//                        }
//                    }
//                }
                count++;
                layerBuilder.addFeatures(featureBuilder.build());
                if (outline) {
                    Geometry temp = geometry.getFactory().createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 1)});
                    temp.setUserData(geometry.getUserData());
                    geometry = temp;
                } else {
                    geometry = null;
                }
            }
        }

        VectorTile.Tile.Builder tileBuilder = VectorTile.Tile.newBuilder();
        tileBuilder.addLayers(layerBuilder.build());
        return tileBuilder.build().toByteArray();
    }
}
