package cscie160.hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.junit.Test;

/**
 * Unit Test the provided ATMProxy client code.
 *
 * @author John Doyle
 * @version 4.0
 */
public class ATMProxyTest {

    /**
     * Set the static hostname to be used for the client/server.
     */
    private static final String TEST_HOSTNAME = "localhost";

    /**
     * Set the static port to be used for the client/server.
     */
    private static final int TEST_PORT = 6123;

    /**
     * Set the static timeout on connections.
     */
    private static final int TIMEOUT = 100;

    /**
     * Set the static amount sent.
     */
    private static final float AMOUNT_SENT = 100;

    /**
     * The object being tested.
     */
    private ATMProxy atmProxy = null;

    /**
     * Initialize our server and connection socket.
     */
    private ServerSocket testServerSocket = null;
    private Socket testSocket = null;

    @Test
    /**
     * Test that the client can successfully connect to the server. This is done by setting a timeout variable on the server's accept. If a socket does not connect to the server in the time given the test will fail. If any exception is thrown, the test will fail.
     *
     */
    public final void testConnection() {

        try {
            /**
             * Open the server socket on the designated port.
             */
            testServerSocket = new ServerSocket(TEST_PORT);

            /**
             * Put a timeout on the accept for the purpose of this test.
             */
            testServerSocket.setSoTimeout(TIMEOUT);

            /**
             * Initialize the ATMProxy to connect to the test hostname and post.
             */
            atmProxy = new ATMProxy(TEST_HOSTNAME, TEST_PORT);

            /**
             * Have the server receive the connection attempt.
             */
            testSocket = testServerSocket.accept();

            /**
             * If no socket connected, fail the test.
             */
            if (testSocket == null) {
                fail("ServerSocket timed out waiting for the client to connect.");
            }
        } catch (Exception e) {
            fail("Unexpected exception.");
        } finally {
            /**
             * Ensure that we close the connections.
             */
            try {
                testServerSocket.close();
                testSocket.close();
            } catch (IOException e) {
                fail("Unexpected exception.");
            }
        }
    }

    @Test
    /**
     * Test that after the client connects the deposit command is sent and received by the server. Fail on any exception.
     *
     */
    public final void testDeposit() {

        try {
            /**
             * Set the server and let the client connect.
             */
            testServerSocket = new ServerSocket(TEST_PORT);
            atmProxy = new ATMProxy(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();

            /**
             * Setup the scanner to read any data passed from the client.
             */
            Scanner testScanner = new Scanner(testSocket.getInputStream());

            /**
             * Have the client send the deposit command for a designated amount.
             */
            String commandSent = Commands.DEPOSIT.toString();
            atmProxy.deposit(AMOUNT_SENT);

            /**
             * Check that the string received matches the expected command
             * string.
             */
            assertEquals(commandSent + " " + AMOUNT_SENT, testScanner.nextLine());
        } catch (Exception e) {
            fail("Unexpected exception.");
        } finally {
            /**
             * Ensure that we close the connections.
             */
            try {
                testServerSocket.close();
                testSocket.close();
            } catch (IOException e) {
                fail("Unexpected exception.");
            }
        }
    }

    @Test
    /**
     * Test that after the client connects the withdraw command is sent and received by the server. Fail on any exception.
     *
     */
    public final void testWithdraw() {

        try {
            /**
             * Set the server and let the client connect.
             */
            testServerSocket = new ServerSocket(TEST_PORT);
            atmProxy = new ATMProxy(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();

            /**
             * Setup the scanner to read any data passed from the client.
             */
            Scanner testScanner = new Scanner(testSocket.getInputStream());

            /**
             * Have the client send the withdraw command for a designated
             * amount.
             */
            String commandSent = Commands.WITHDRAW.toString();
            atmProxy.withdraw(AMOUNT_SENT);

            /**
             * Check that the string received matches the expected command
             * string.
             */
            assertEquals(commandSent + " " + AMOUNT_SENT, testScanner.nextLine());
        } catch (Exception e) {
            fail("Unexpected exception.");
        } finally {
            /**
             * Ensure that we close the connections.
             */
            try {
                testServerSocket.close();
                testSocket.close();
            } catch (IOException e) {
                fail("Unexpected exception.");
            }
        }
    }

    @Test
    /**
     * Test that after the client connects the getBalance command is sent and received by the server. Fail on any exception.
     */
    public final void testGetBalance() {
        try {
            /**
             * Set the server and let the client connect.
             */
            testServerSocket = new ServerSocket(TEST_PORT);
            atmProxy = new ATMProxy(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();

            /**
             * Setup the scanner to read any data passed from the client.
             */
            Scanner testScanner = new Scanner(testSocket.getInputStream());

            /**
             * Send the amount out to the socket.
             */
            PrintWriter testWriter = new PrintWriter(
                    testSocket.getOutputStream(), true);
            testWriter.println(AMOUNT_SENT);

            /**
             * Have the client send the balance command.
             */
            String commandReceived = Commands.BALANCE.toString();

            /**
             * Execute the command and save the balance returned.
             */
            float balanceReturned = atmProxy.getBalance();

            /**
             * Check that the string received matches the expected command
             * string.
             */
            assertEquals(commandReceived, testScanner.nextLine());

            /**
             * Check that the balance returned matches the balance the server
             * sent.
             */
            assertEquals(AMOUNT_SENT, balanceReturned, 0);
        } catch (Exception e) {
            fail("Unexpected exception.");
        } finally {
            /**
             * Ensure that we close the connections.
             */
            try {
                testServerSocket.close();
                testSocket.close();
            } catch (IOException e) {
                fail("Unexpected exception.");
            }
        }
    }

    @Test
    /**
     * Test that the client throws the expected exception when the client recieves bad data back from the server.
     *
     */
    public final void testGetBalanceException() {

        try {
            /**
             * Set the server and let the client connect.
             */
            testServerSocket = new ServerSocket(TEST_PORT);
            atmProxy = new ATMProxy(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();

            /**
             * Set the bad response.
             */
            String badResponse = "BAD";

            /**
             * Send the bad response to the connected client.
             */
            PrintWriter testWriter = new PrintWriter(
                    testSocket.getOutputStream(), true);
            testWriter.println(badResponse);

            /**
             * Execute the client command.
             */
            atmProxy.getBalance();

            /**
             * If the client did not raise an exception, fail the test.
             */
            fail("Exception should have been thrown.");

        } catch (ATMException e) {
            /**
             * This is the expected exception to be caught, pass the test as a
             * result.
             */
            assertTrue(true);
        } catch (IOException e) {
            fail("Unexpected exception.");
        } finally {
            /**
             * Ensure that we close the connections.
             */
            try {
                testServerSocket.close();
                testSocket.close();
            } catch (IOException e) {
                fail("Unexpected exception.");
            }
        }
    }
}
