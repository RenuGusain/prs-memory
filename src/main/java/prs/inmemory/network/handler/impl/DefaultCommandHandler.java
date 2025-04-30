package prs.inmemory.network.handler.impl;

import prs.inmemory.datastore.api.KeyValueStore;
import prs.inmemory.datastore.api.ValueWrapper;
import prs.inmemory.network.handler.api.CommandHandler;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommandHandler implements CommandHandler {
    List<BigObject> list=new ArrayList<>(1000);

    private final KeyValueStore dataStore;

    public DefaultCommandHandler(KeyValueStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public String handle(String command) {
        try {
            String[] parts = command.trim().split("\\s+");
            String cmd = parts[0].toUpperCase();

            switch (cmd) {
                case "SET":
                    if (parts.length != 3) {
                        return "-ERR wrong number of arguments for 'set' command\r\n";
                    }
                    dataStore.put(parts[1], new ValueWrapper(parts[2], ValueWrapper.ValueType.STRING));
                    return "+OK\r\n";

                case "GET":
                    if (parts.length != 2) {
                        return "-ERR wrong number of arguments for 'get' command\r\n";
                    }
                    ValueWrapper value = dataStore.get(parts[1]);
                    if (value == null) {
                        return "$-1\r\n";
                    }
                    return "$" + value.get().toString().length() + "\r\n" + value.get().toString() + "\r\n";

                case "DEL":
                    if (parts.length != 2) {
                        return "-ERR wrong number of arguments for 'del' command\r\n";
                    }
                    dataStore.delete(parts[1]);
                    return ":1\r\n";

                case "PING":
                    return "+PONG\r\n";
                case "CRASH":
                    crash();
                    return "server might crash";

                default:
                    return "-ERR unknown command '" + parts[0] + "'\r\n";
            }
        } catch (Exception e) {
            return "-ERR server error\r\n";
        }
    }
    public void crash()
    {

        for(int i=0;i<1000000000;i++)
        {
            list.add(new BigObject("nameaaaaaaaaaaaa"+i,"age"+i,"surennaaaaaaaa"+i));

        }

    }
   class BigObject
   {
      String name;
      String age;
      String surname;
      BigObject(String name,String age, String surname)
      {
          this.name=name;
          this.age=age;
          this.surname=surname;
      }

   }
}