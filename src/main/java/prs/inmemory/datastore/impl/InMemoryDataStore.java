package prs.inmemory.datastore.impl;

import prs.inmemory.datastore.api.DataStore;
import prs.inmemory.domain.model.api.PRSObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataStore implements DataStore {
    private final ConcurrentHashMap<String, PRSObject> store = new ConcurrentHashMap<>();

    public PRSObject get(String key) {
        return store.get(key);
    }

    public void set(String key, PRSObject value) {
        store.put(key, value);
    }

    public boolean exists(String key) {
        return store.containsKey(key);
    }
}
