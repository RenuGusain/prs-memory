package prs.inmemory.datastore.impl;

import prs.inmemory.datastore.api.KeyValueStore;
import prs.inmemory.datastore.api.ValueWrapper;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataStore implements KeyValueStore {
    private final ConcurrentHashMap<String, ValueWrapper> store = new ConcurrentHashMap<>();

    @Override
    public void put(String key, ValueWrapper value) {
        store.put(key, value);
    }

    @Override
    public ValueWrapper get(String key) {
        return store.get(key);
    }

    @Override
    public void delete(String key) {
        store.remove(key);
    }

    @Override
    public boolean containsKey(String key) {
        return store.containsKey(key);
    }
}
