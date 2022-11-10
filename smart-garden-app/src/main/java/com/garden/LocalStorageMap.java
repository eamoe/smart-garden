package com.garden;

import java.util.*;

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

    @Override
    public Pair<Long, String> getFirstItem() {

        Pair<Long, String> pair = new Pair<>(0L, "");

        if (!storageMap.isEmpty()) {
            SortedSet<Long> keys = new TreeSet<>(storageMap.keySet());
            Long key = keys.first();
            String value = storageMap.get(key);

            pair.setKey(key);
            pair.setValue(value);
        }

        return pair;
    }

    @Override
    public void removeItem(long key) {
        storageMap.remove(key);
    }
}
