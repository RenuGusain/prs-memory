package prs.inmemory.datastore.api;

public interface  KeyValueStore{
    void put(String key, ValueWrapper value);
    ValueWrapper get(String key);
    void delete(String key);
    boolean containsKey(String key);

}
