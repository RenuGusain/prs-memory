package prs.inmemory.datastore.api;

import prs.inmemory.domain.model.api.PRSObject;

import java.util.List;

public interface DataStore {
    public PRSObject get(String key);
    public void set(String key, PRSObject value);
    public boolean exists(String key);
}
