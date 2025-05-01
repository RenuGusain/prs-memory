package prs.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prs.inmemory.config.init.AppInitializer;
import prs.inmemory.network.TCPServer;
import prs.inmemory.service.api.PRSMemoryService;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        PRSMemoryService service = AppInitializer.initializeApp();
        int port = 6379;//TODO make part of configuration
        int threadPoolSize = 10;
        TCPServer server = new TCPServer(port, service);
        server.start();

    }
}
