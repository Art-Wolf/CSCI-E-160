package cscie160.lecture7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {
    static private final int DEFAULT_PORT = 8192;
    static private final String SIGN_OFF_TOKEN = "BYE";
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    Scanner socketScanner = null;
    PrintWriter outputToClient = null;
    OutputStream outputStream = null;

    /**
     * Constructor to set the server port
     */
    public EchoServer() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            serverSocket.setSoTimeout(100);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Wait 100ms for a client to connect
     * 
     * @return true when a client connects
     */
    public boolean clientConnect() {
        try {
            clientSocket = serverSocket.accept();
            socketScanner = new Scanner(clientSocket.getInputStream());
            outputStream = clientSocket.getOutputStream();
            outputToClient = new PrintWriter(outputStream, true);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Disconnect the client port.
     * 
     * @return true if disconnect
     */
    public boolean clientDisconnect() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Close the server's ports and connected client.
     * 
     * @return true if disconnect
     */
    public boolean serverDisconnect() {
        try {
            serverSocket.close();
            if (clientSocket != null) {
                clientDisconnect();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * Get a message sent from the connected client. If the sign off,
     * disconnect.
     * 
     * @return the message
     */
    public String getMessage() {
        String message = socketScanner.nextLine();
        message = message.trim();

        if (message.toUpperCase().startsWith(SIGN_OFF_TOKEN)) {
            clientDisconnect();
        }

        return message;
    }
}
