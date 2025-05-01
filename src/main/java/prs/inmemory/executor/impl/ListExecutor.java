package prs.inmemory.executor.impl;

import prs.inmemory.datastore.api.DataStore;
import prs.inmemory.datastore.impl.InMemoryDataStore;
import prs.inmemory.domain.model.api.PRSObject;
import prs.inmemory.domain.model.impl.PRSList;
import prs.inmemory.executor.api.CommandExecutor;

public class ListExecutor implements CommandExecutor {
    private final DataStore store;

    public ListExecutor(DataStore store) {
        this.store = store;
    }


    @Override
    public String execute(String[] args) {
        if (args.length < 2) return "Error: Missing list command or key";

        String cmd = args[0];
        String key = args[1];
        PRSList list = getOrCreateList(key);
        switch (cmd) {
            case "LGET":
                if (args.length != 2) return "Error: LGET requires 1 argument: LGET key";
                return getFullList(key);

            case "LPUSH":
                if(args.length<3)return "Error LPUSH INVALID Argument";
                list.lpush(args[2]);
                return "OK";
            case "RPUSH":
                list.rpush(args[2]);
                return "OK";
            case "LPOP":
                return list.lpop();
            case "RPOP":
                return list.rpop();
            case "LRANGE":
                int start = Integer.parseInt(args[2]);
                int end = Integer.parseInt(args[3]);
                return list.lrange(start, end).toString();
            default:
                return "Unsupported command";
        }
    }

    private PRSList getOrCreateList(String key) {
        PRSObject obj = store.get(key);
        if (obj instanceof PRSList) return (PRSList) obj;
        PRSList list = new PRSList();
        store.set(key, list);
        return list;
    }

    private String getFullList(String key) {
        PRSObject obj = store.get(key);
        if (!(obj instanceof PRSList)) return "(nil)";
        return ((PRSList) obj).getAll().toString();
    }
}
