package com.stlogic.ltdb.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VectorTileBuilder extends com.stlogic.fbgis.vector_tile.VectorTileBuilder {
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

    public static boolean isDataTypeForGeometryClass(DataType type) {
        return (type instanceof UserDefinedType) &&
                Geometry.class.isAssignableFrom(((UserDefinedType) type).userClass());
    }

    public static byte[] build(StructType schema, RDD<Row> rdd, String typeName, com.stlogic.fbgis.vector_tile.VectorTileBuilder.AggregateType type) throws IOException {
//        Geometry tileGeometry = createTileEnvelope(0, 0, 0);

        VectorTile.Tile.Layer.Builder layerBuilder = VectorTile.Tile.Layer.newBuilder();
        layerBuilder.setName(typeName);
        layerBuilder.setExtent(VectorTileCommand.EXTENT);
        layerBuilder.setVersion(1);

        int index = -1;
        for (StructField field : schema.fields()) {
            if (isDataTypeForGeometryClass(field.dataType())) {
                index = schema.indexOf(field);
                continue;
            }
            layerBuilder.addKeys(field.name());
        }

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
