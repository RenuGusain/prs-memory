package prs.inmemory.config;

import prs.inmemory.executor.api.CommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorRegistry {
    private final Map<String, CommandExecutor> executorMap = new HashMap<>();

    public void register(String command, CommandExecutor executor) {
        executorMap.put(command.toUpperCase(), executor);
    }

    public CommandExecutor resolve(String command) {
        return executorMap.get(command.toUpperCase());
    }
}
