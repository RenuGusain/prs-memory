package prs.inmemory.network.server.api;

public interface CommandServer {
    void start();
    void stop();
    void updateMetrics();
}
