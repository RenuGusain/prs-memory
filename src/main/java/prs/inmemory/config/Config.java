package prs.inmemory.config;

public interface Config {
    int getSnapshotIntervalSeconds();
    int getMaxHeapSizeMB();
    boolean isPersistenceEnabled();
    boolean isReplicationEnabled();
}
