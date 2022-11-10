package com.garden;

import java.util.HashMap;
import java.util.Map;

public class LocalStorageMap implements LocalStorage {

    private final Map<Long, String> storageMap;

    public LocalStorageMap() {
        storageMap = new HashMap<>();
    }

    public Map<Long, String> getStorageMap() {
        return storageMap;
    }

    @Override
    public void addItem(String data) {
        if (data == null) throw new AssertionError();
        long key = System.currentTimeMillis();
        storageMap.put(key, data);
    }
}
