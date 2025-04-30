package prs.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prs.inmemory.datastore.api.KeyValueStore;
import prs.inmemory.datastore.impl.InMemoryDataStore;
import prs.inmemory.network.handler.api.CommandHandler;
import prs.inmemory.network.handler.impl.DefaultCommandHandler;
import prs.inmemory.network.protocol.api.CommandParser;
import prs.inmemory.network.protocol.impl.SimpleCommandParser;
import prs.inmemory.network.server.api.CommandServer;
import prs.inmemory.network.server.impl.SimpleCommandServer;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        KeyValueStore dataStore = new InMemoryDataStore();
        CommandParser parser = new SimpleCommandParser();

        CommandHandler handler = new DefaultCommandHandler(dataStore);

        int port = 6379; // Default
        String portEnv = System.getenv("COMMAND_SERVER_PORT");
        if (portEnv != null && !portEnv.isEmpty()) {
            try {
                port = Integer.parseInt(portEnv); // Convert to integer
                System.out.println("Using provided port: " + port);
            } catch (NumberFormatException e) {
                logger.error("Invalid port number provided, falling back to default port:{} ", port);
            }
        } else {
            logger.info("Using default port: {}", port);
        }
        CommandServer server = new SimpleCommandServer(port, parser, handler);

        server.start();
        server.updateMetrics();
    }
}
