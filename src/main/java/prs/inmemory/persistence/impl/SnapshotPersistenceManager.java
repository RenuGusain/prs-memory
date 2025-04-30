package prs.inmemory.persistence.impl;



import prs.inmemory.datastore.impl.InMemoryDataStore;
import prs.inmemory.persistence.api.PersistenceManager;

import java.io.*;
import java.util.Map;

public class SnapshotPersistenceManager implements PersistenceManager {
    private final InMemoryDataStore dataStore;
    private final String filePath = "snapshot.dat";

    public SnapshotPersistenceManager(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void saveSnapshot() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dataStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadSnapshot() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            InMemoryDataStore restored = (InMemoryDataStore) ois.readObject();
            // Not the best way but illustrative
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
