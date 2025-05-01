package prs.inmemory.network;

import prs.inmemory.service.api.PRSMemoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final PRSMemoryService service;

    public ClientHandler(Socket socket, PRSMemoryService service) {
        this.clientSocket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            writer.println("Welcome to PRS Redis-like Server. Type commands like SET key val, GET key...");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) break;
                String[] parts = line.trim().split("\\s+");
                String response = service.runCommand(parts);
                writer.println(response);
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {
            }
        }
    }
}