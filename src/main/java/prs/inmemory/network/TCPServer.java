package prs.inmemory.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prs.inmemory.service.api.PRSMemoryService;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    Logger logger= LoggerFactory.getLogger(TCPServer.class);
    private final int port;
    private final PRSMemoryService prsService;
    private final ExecutorService clientExecutor;
    public TCPServer(int port, PRSMemoryService service)
    {
        this.port=port;
       this.prsService=service;
       this.clientExecutor= Executors.newCachedThreadPool();
    }
    public void start()
    {
        try(ServerSocket serverSocket=new ServerSocket(port))
        {
            logger.info("Server started on port {} ",port);
            Socket clientSocket=serverSocket.accept();
            clientExecutor.submit(new ClientHandler(clientSocket, prsService));

        }catch (Exception e)
        {
            logger.error("Server error",e);

        }
    }
    private void shutdown() {
        clientExecutor.shutdown();
        System.out.println("Server shutdown.");
    }
}
