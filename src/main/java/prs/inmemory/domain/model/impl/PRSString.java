package prs.inmemory.domain.model.impl;

import prs.inmemory.domain.model.api.PRSObject;

public class PRSString extends PRSObject {
    private final String value;

    public PRSString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
