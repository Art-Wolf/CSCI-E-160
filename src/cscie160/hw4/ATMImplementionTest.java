package cscie160.hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit test of the ATMImplementation on the Server.
 *
 * @author John Doyle
 */
public class ATMImplementionTest {

    /**
     * Initialize the object being tested.
     */
    private ATMImplemention atmImplementation;

    /**
     * Initialize the test account.
     */
    private Account testAccount;

    /**
     * Set the static deposit amount to be passed.
     */
    private static final float DEPOSIT_AMOUNT = 100;

    /**
     * Set the static withdrawal amount to be passed.
     */
    private static final float WITHDRAWAL_AMOUNT = 30;

    /**
     * Set the static bad deposit amount to be passed.
     */
    private static final float BAD_DEPOSIT_AMOUNT = -100;

    /**
     * Set the static excess withdrawal amount to be passed.
     */
    private static final float EXCESS_WITHDRAWAL_AMOUNT = 200;

    @Test
    /**
     * Test that the deposit method correctly modifies the balance of the account.
     *
     */
    public final void testDeposit() {
        /**
         * Create a new account with a zero balance.
         */
        testAccount = new Account();

        /**
         * Call the ATM Implementation constructor passing the test account.
         */
        atmImplementation = new ATMImplemention(testAccount);

        try {
            /**
             * Execute the command.
             */
            atmImplementation.deposit(DEPOSIT_AMOUNT);

            /**
             * Test that the new balance exactly matches the deposit.
             */
            assertEquals(DEPOSIT_AMOUNT, testAccount.getBalance(), 0);
        } catch (ATMException e) {
            fail("Exception should have not been thrown.");
        }
    }

    @Test
    /**
     * Test that a negative balance can not be set.
     *
     */
    public void testDepositException() {
        /**
         * Create a new account with a zero balance.
         */
        testAccount = new Account();

        /**
         * Call the ATM Implementation constructor passing the test account.
         */
        atmImplementation = new ATMImplemention(testAccount);

        try {
            /**
             * Execute the command with the bad deposit.
             */
            atmImplementation.deposit(BAD_DEPOSIT_AMOUNT);

            /**
             * Fail the test if no exception is generated.
             */
            fail("Exception should have been thrown.");
        } catch (ATMException e) {
            /**
             * The expected exception was raised.
             */
            assertTrue(true);
        }
    }

    @Test
    /**
     * Test that the withdraw correctly reduces the account's balance.
     *
     */
    public void withdraw() {
        /**
         * Create a new account with a 100 initial balance.
         */
        testAccount = new Account(DEPOSIT_AMOUNT);

        /**
         * Call the ATM Implementation constructor passing the test account.
         */
        atmImplementation = new ATMImplemention(testAccount);

        try {
            /**
             * Set the withdrawal to be 30.
             */
            atmImplementation.withdraw(WITHDRAWAL_AMOUNT);

            /**
             * Check that the difference between the initial amount and the new
             * balance is 70.
             */
            assertEquals(DEPOSIT_AMOUNT, testAccount.getBalance(),
                    DEPOSIT_AMOUNT - WITHDRAWAL_AMOUNT);
        } catch (ATMException e) {
            fail("Exception should have not been thrown.");
        }
    }

    @Test
    /**
     * Test that a withdrawal can not exceed the balance of an account.
     *
     */
    public void withdrawException() {
        /**
         * Create a new account with a 100 initial balance.
         */
        testAccount = new Account(DEPOSIT_AMOUNT);

        /**
         * Call the ATM Implementation constructor passing the test account.
         */
        atmImplementation = new ATMImplemention(testAccount);

        try {
            /**
             * Set the withdrawal to be 200.
             */
            atmImplementation.withdraw(EXCESS_WITHDRAWAL_AMOUNT);

            /**
             * Fail the test if no exception is raised.
             */
            fail("Exception should have been thrown.");
        } catch (ATMException e) {
            /**
             * The expected exception was raised and caught.
             */
            assertTrue(true);
        }
    }

    @Test
    /**
     * Test that the balance command successfully returns the current balance.
     *
     */
    public void getBalance() {
        /**
         * Create a new account with a zero balance.
         */
        testAccount = new Account();

        /**
         * Call the ATM Implementation constructor passing the test account.
         */
        atmImplementation = new ATMImplemention(testAccount);

        try {
            /**
             * Confirm that the command returns a zero balance.
             */
            assertEquals(0, atmImplementation.getBalance(), 0);
        } catch (ATMException e) {
            fail("Exception should have not been thrown.");
        }
    }
}
