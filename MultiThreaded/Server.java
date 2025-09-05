import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream());
                toSocket.println("Hello from server " + clientSocket.getInetAddress());
                toSocket.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    // Create and start a new thread for each client
                    Thread thread = new Thread(() -> server.getConsumer().accept(clientSocket));
                    thread.start();
                } catch (SocketTimeoutException ste) {
                    System.out.println("Accept timed out, continuing to listen...");
                    // This catch block handles the timeout and keeps the loop running.
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}