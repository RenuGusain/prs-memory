package prs.inmemory.replication.impl;

import prs.inmemory.datastore.api.ValueWrapper;
import prs.inmemory.replication.api.Replicator;

public class SimpleReplicator implements Replicator {

    @Override
    public void replicatePut(String key, ValueWrapper value) {
        // simulate replication
        System.out.println("[Replication] PUT key=" + key);
    }

    @Override
    public void replicateDelete(String key) {
        System.out.println("[Replication] DELETE key=" + key);
    }
}