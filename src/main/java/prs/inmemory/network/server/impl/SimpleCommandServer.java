package prs.inmemory.network.server.impl;

import io.prometheus.client.exporter.HTTPServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prs.inmemory.network.handler.api.CommandHandler;
import prs.inmemory.network.protocol.api.CommandParser;
import prs.inmemory.network.server.api.CommandServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import io.prometheus.client.Gauge;

public class SimpleCommandServer implements CommandServer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleCommandServer.class);
    private final int port;
    private ServerSocket serverSocket;
    private final CommandParser parser;
    private final CommandHandler handler;
    private final ExecutorService clientPool;
    private static final Gauge memoryUsage = Gauge.build()
            .name("jvm_memory_usage_bytes")
            .help("JVM memory usage in bytes.")
            .register();

    public SimpleCommandServer(int port, CommandParser parser, CommandHandler handler) {
        this.port = port;
        this.parser = parser;
        this.handler = handler;
        clientPool = Executors.newCachedThreadPool();
    }


    @Override
    public void start() {
        try {
            HTTPServer metricsServer = new HTTPServer(8081);
            serverSocket = new ServerSocket(port);
            logger.info("[CommandServer] Listening on port {}", port);
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                clientPool.submit(() -> handleClient(clientSocket));
            }

        } catch (Exception e) {
            logger.error("[CommandServer] Encountered error ", e);
        }

    }



    private void handleClient(Socket clientSocket) {
        try (var input = clientSocket.getInputStream();
             var output = clientSocket.getOutputStream()) {
            while (true) {
                String command = parser.parse(input);
                if (command == null) {
                    break;
                }
                String response = handler.handle(command);
                output.write(response.getBytes());
                output.flush();
            }
        } catch (Exception e) {
            logger.error("[CommandServer] Client disconnected:", e);
        }

    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
            clientPool.shutdownNow();
            logger.info("[CommandServer] Server stopped");
        } catch (IOException e) {
            logger.error("Failed to stop server", e);
        }
    }

    @Override
    public void updateMetrics() {
        new Thread(() -> {
            while (true) {
                long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                memoryUsage.set(memoryUsed);
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
