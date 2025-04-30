package prs.inmemory.util;

public class MetricsCollector {
    public static void logMemoryUsage() {
        Runtime runtime=Runtime.getRuntime();
        long memory=(runtime.totalMemory()-runtime.freeMemory())/(1024*1024);
        System.out.println("Used Memory "+memory+" Mb");
    }
}
