package prs.inmemory.persistence.api;

public interface PersistenceManager {
    void saveSnapshot();
    void loadSnapshot();
}
