package com.vladsch.flexmark.internal.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataSet implements DataHolder {
    final protected HashMap<DataKey, Object> dataSet;

    public DataSet() {
        dataSet = new HashMap<>();
    }

    public DataSet(DataHolder other) {
        dataSet = new HashMap<>();
        dataSet.putAll(other.getAll());
    }

    @Override
    public Map<DataKey, Object> getAll() {
        return dataSet;
    }

    @Override
    public Collection<DataKey> keySet() {
        return dataSet.keySet();
    }

    @Override
    public boolean contains(DataKey key) {
        return dataSet.containsKey(key);
    }

    @Override
    public <T> T get(DataKey<T> key) {
        if (dataSet.containsKey(key)) {
            return key.getValue(dataSet.get(key));
        } else {
            return key.getDefaultValue();
        }
    }
}
