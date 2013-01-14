package cscie160.project;

import java.io.Serializable;

/**
 * Data object passing the account number and pin from the client
 * to the ATM.
 * 
 * @author John Doyle
 *
 */
public class AccountInfo implements Serializable {

    /**
     * Serializable object passed between the ATM, Client, and Bank systems.
     */
    private static final long serialVersionUID = 1L;

    /**
     * PIN code
     */
    private int pin;

    /**
     * Account Identifier
     */
    private int accountNumber;
    
    /**
     * Default constructor, which expects an account number and pin code.
     *
     * @param accountNumber
     * @param pin
     */
    public AccountInfo(int accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    /**
     * Return the account number.
     *
     * @return the Account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Compare the pin code provided against the pin code given by the constructor.
     * This method is used by the Security system to authenticate the account.
     *
     * @param int pin
     */
    public boolean checkPin(int pin1) {
        if(this.pin == pin1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Return the pin code to validate against another account.
     *
     * @return pin code
     */
    public int getPin() {
        return pin;
    }
}
