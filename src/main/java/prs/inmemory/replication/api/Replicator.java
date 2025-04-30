package prs.inmemory.replication.api;

import prs.inmemory.datastore.api.ValueWrapper;

public interface Replicator {
    void replicatePut(String key, ValueWrapper value);
    void replicateDelete(String key);
}