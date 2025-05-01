package prs.inmemory.service.impl;

import prs.inmemory.config.CommandExecutorRegistry;
import prs.inmemory.executor.api.CommandExecutor;
import prs.inmemory.service.api.PRSMemoryService;

public class DefaultPRSMemoryService  implements PRSMemoryService {
    private final CommandExecutorRegistry registry;

    public DefaultPRSMemoryService(CommandExecutorRegistry registry) {
        this.registry = registry;
    }

    public String runCommand(String[] args) {
        if (args.length == 0) return "Empty command";
        CommandExecutor executor = registry.resolve(args[0]);
        if (executor == null) return "Unknown command";
        return executor.execute(args);
    }
}
