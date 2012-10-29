package cscie160.lecture7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    static private final String SIGNOFF_TOKEN = "BYE";
    private Socket echoSocket = null;
    private PrintWriter out = null;
    private Scanner socketScanner = null;

    /**
     * Constructor to connect to the server.
     * 
     * @param hostname
     * @param port
     */
    public EchoClient(String hostname, int port) {
        try {
            echoSocket = new Socket(hostname, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            socketScanner = new Scanner(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostname);
            return;
        }
    }

    /**
     * Read a line from the server socket.
     * 
     * @return the message recieved
     */
    public String serverResponse() {
        return socketScanner.nextLine();
    }

    /**
     * send the message to the server socket.
     * 
     * @param message
     */
    public void sendMessage(String message) {
        out.println(message);
    }

    /**
     * Send the sign off token and close the port.
     * 
     * @return
     */
    public boolean closeConnection() {

        sendMessage(SIGNOFF_TOKEN);

        try {
            echoSocket.close();
            return true;
        } catch (java.io.IOException eio) {
            System.err.println(eio);
            return false;
        }
    }

}
