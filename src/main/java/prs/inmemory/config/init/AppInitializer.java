package prs.inmemory.config.init;

import prs.inmemory.config.CommandExecutorRegistry;
import prs.inmemory.datastore.api.DataStore;
import prs.inmemory.datastore.impl.InMemoryDataStore;
import prs.inmemory.executor.api.CommandExecutor;
import prs.inmemory.executor.impl.ListExecutor;
import prs.inmemory.executor.impl.StringExecutor;
import prs.inmemory.service.api.PRSMemoryService;
import prs.inmemory.service.impl.DefaultPRSMemoryService;

public class AppInitializer {
    public static PRSMemoryService initializeApp() {
        LogDirInitializer.initializelogDirectory();;
        DataStore store = new InMemoryDataStore();
        CommandExecutorRegistry registry = new CommandExecutorRegistry();

        // Register string commands
        CommandExecutor stringExecutor = new StringExecutor(store);
        registry.register("SET", stringExecutor);
        registry.register("GET", stringExecutor);

        // Register list commands
        CommandExecutor listExecutor = new ListExecutor(store);
        registry.register("LPUSH", listExecutor);
        registry.register("RPUSH", listExecutor);
        registry.register("LPOP", listExecutor);
        registry.register("RPOP", listExecutor);
        registry.register("LRANGE", listExecutor);
        registry.register("LGET",listExecutor);

        return new DefaultPRSMemoryService(registry);
    }
}
