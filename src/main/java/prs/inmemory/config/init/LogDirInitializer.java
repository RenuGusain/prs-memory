package prs.inmemory.config.init;

import java.io.File;

public class LogDirInitializer {

    public static void initializelogDirectory() {
        String logDir = "app/inmemory/logs"; // get env var


        File dir = new File(logDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.err.println("⚠ Failed to create log directory: " + logDir);
            }
        }
    }
}
