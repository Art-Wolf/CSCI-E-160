package cscie160.lecture7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.junit.Test;

public class EchoClientTest {

    private static final String TEST_HOSTNAME = "localhost";
    private static final int TEST_PORT = 6123;
    EchoClient echoClient = null;

    @Test
    public void testConnect() {
        ServerSocket testServerSocket = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            echoClient = new EchoClient(TEST_HOSTNAME, TEST_PORT);
            testServerSocket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(false);
        }

        try {
            testServerSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        echoClient.closeConnection();
    }

    @Test
    public void testClientSendMessage() {
        ServerSocket testServerSocket = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            echoClient = new EchoClient(TEST_HOSTNAME, TEST_PORT);
            Socket testSocket = testServerSocket.accept();
            Scanner testScanner = new Scanner(testSocket.getInputStream());
            String testMessage = "Test Message";
            echoClient.sendMessage(testMessage);
            String result = testScanner.nextLine();
            assertEquals(testMessage, result);

        } catch (Exception e) {
        }

        try {
            testServerSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        echoClient.closeConnection();
    }

    @Test
    public void testServerResponse() {
        ServerSocket testServerSocket = null;

        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            echoClient = new EchoClient(TEST_HOSTNAME, TEST_PORT);
            Socket testSocket = testServerSocket.accept();
            PrintWriter testWriter = new PrintWriter(
                    testSocket.getOutputStream(), true);
            String testMessage = "Test Message";
            testWriter.println(testMessage);
            String result = echoClient.serverResponse();
            assertEquals(testMessage, result);
        } catch (Exception e) {
        }

        try {
            testServerSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        echoClient.closeConnection();
    }

    @Test
    public void testClientDisconnect() {
        ServerSocket testServerSocket = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);

            echoClient = new EchoClient(TEST_HOSTNAME, TEST_PORT);
            Socket testSocket = testServerSocket.accept();

            Scanner testScanner = new Scanner(testSocket.getInputStream());
            echoClient.closeConnection();
            String testMessage = "Test Message";
            echoClient.sendMessage(testMessage);
            String result = testScanner.next();

            assertEquals("BYE", result);
        } catch (Exception e) {
        }

        try {
            testServerSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        echoClient.closeConnection();
    }
}
