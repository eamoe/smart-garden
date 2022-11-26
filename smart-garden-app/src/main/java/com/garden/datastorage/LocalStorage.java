package com.garden.datastorage;

public interface LocalStorage {
    public void addItem(String data);
    public Pair<Long, String> getFirstItem();
    public void removeItem(long key);
    //public void clear();
}
