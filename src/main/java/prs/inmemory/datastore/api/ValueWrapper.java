package prs.inmemory.datastore.api;

public class ValueWrapper {
    private final Object value;
    private final ValueType type;

    public enum ValueType {STRING, MAP, JSON, INTEGER}

    public ValueWrapper(Object value, ValueType valueType) {
        this.value = value;
        this.type = valueType;
    }

    public Object get() {
        return value;
    }

    public ValueType getType() {
        return type;
    }

}
