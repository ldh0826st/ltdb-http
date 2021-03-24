package com.stlogic.ltdb.common;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public abstract class Conf<T extends Conf> implements Iterable<Map.Entry<String, String>> {
    protected Logger LOG = LoggerFactory.getLogger(getClass());

    public static interface ConfEntry {
        String key();

        Object dflt();
    }

    protected final Map<String, String> config;

    protected Conf(Properties config) {
        this.config = Maps.newConcurrentMap();
        if (config != null) {
            for (String key : config.stringPropertyNames()) {
                this.config.put(key, config.getProperty(key));
            }
        }
    }

    public String get(String key) {
        String val = config.get(key);
        if (val != null) {
            return val;
        }
        return val;
    }

    @SuppressWarnings("unchecked")
    public T set(String key, String value) {
        config.put(key, value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setIfMissing(String key, String value) {
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setAll(Conf<?> other) {
        for (Map.Entry<String, String> e : other) {
            set(e.getKey(), e.getValue());
        }
        return (T) this;
    }

    public String get(ConfEntry e) {
        Object value = get(e, String.class);
        return (String) (value != null ? value : e.dflt());
    }

    public boolean getBoolean(ConfEntry e) {
        String val = get(e, Boolean.class);
        if (val != null) {
            return Boolean.parseBoolean(val);
        } else {
            return (Boolean) e.dflt();
        }
    }

    public int getInt(ConfEntry e) {
        String val = get(e, Integer.class);
        if (val != null) {
            return Integer.parseInt(val);
        } else {
            return (Integer) e.dflt();
        }
    }

    public long getLong(ConfEntry e) {
        String val = get(e, Long.class);
        if (val != null) {
            return Long.parseLong(val);
        } else {
            return (Long) e.dflt();
        }
    }

    public T set(ConfEntry e, Object value) {
        check(typesMatch(value, e.dflt()), "Value doesn't match configuration entry type for %s.",
                e.key());
        if (value == null) {
            config.remove(e.key());
        } else {
            config.put(e.key(), value.toString());
        }
        return (T) this;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return config.entrySet().iterator();
    }

    private String get(ConfEntry e, Class<?> requestedType) {
        check(getType(e.dflt()).equals(requestedType), "Invalid type conversion requested for %s.",
                e.key());
        return this.get(e.key());
    }

    private boolean typesMatch(Object test, Object expected) {
        return test == null || getType(test).equals(getType(expected));
    }

    private Class<?> getType(Object o) {
        return (o != null) ? o.getClass() : String.class;
    }

    private void check(boolean test, String message, Object... args) {
        if (!test) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }
}
