package prs.inmemory.executor.impl;

import prs.inmemory.datastore.api.DataStore;
import prs.inmemory.datastore.impl.InMemoryDataStore;
import prs.inmemory.domain.model.api.PRSObject;
import prs.inmemory.domain.model.impl.PRSString;
import prs.inmemory.executor.api.CommandExecutor;

public class StringExecutor implements CommandExecutor {

    private final DataStore store;

    public StringExecutor(DataStore store) {
        this.store = store;
    }


    @Override
    public String execute(String[] args) {
        if (args.length < 2) return "Error: Missing command name or arguments";

        String command = args[0].toUpperCase();

        switch (command) {
            case "SET":
                if (args.length != 3) return "Error: SET requires 2 arguments: SET key value";
                store.set(args[1], new PRSString(args[2]));
                return "OK";

            case "GET":
                if (args.length != 2) return "Error: GET requires 1 argument: GET key";
                PRSObject obj = store.get(args[1]);
                if (obj instanceof PRSString) {
                    return ((PRSString) obj).getValue();
                }
                return "(nil)";

            default:
                return "Error: Unsupported string command";
        }
    }
}
