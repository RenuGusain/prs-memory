package prs.inmemory.domain.model.impl;

import prs.inmemory.domain.model.api.PRSObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PRSList extends PRSObject {
    private final LinkedList<String> list = new LinkedList<>();

    public void lpush(String value) {
        list.addFirst(value);
    }

    public void rpush(String value) {
        list.addLast(value);
    }

    public String lpop() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public String rpop() {
        return list.isEmpty() ? null : list.removeLast();
    }

    public List<String> lrange(int start, int end) {
        end = Math.min(end, list.size() - 1);
        return new LinkedList<>(list.subList(start, end + 1));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public List<String> getAll() {
        return new ArrayList<>(list);
    }

}
