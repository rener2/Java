/**
 * Created by Rene on 27.02.2016.
 */
public class BankAccount {

    /**
     * 1% transfer fee.
     */
    public static final double TRANSFER_FEE = 0.01;

    /**
     * Accounts current balance.
     */
    private double balance;

    /**
     * @return balance of an account.
     */
    public final double getBalance() {
        return balance;
    }

    /**
     * Withdraw given amount from the account.
     * @param amount the amount to be withdrawn.
     * @return boolean if the withdrawal was successful.
     */
    public final double withdrawMoney(double amount) {
        if (amount > balance) {
            return Double.NaN;
        }
        balance -= amount;
        return balance;
    }

    /**
     * Add given amount to the account.
     * @param amount the amount to be added.
     */
    public final void addMoney(double amount) {
        balance += amount;
    }
    /**
     * Withdraw given amount from the account.
     * @param targetAccount the receiving account.
     * @param amount        the amount to be transferred.
     * @return boolean if the transfer was successful.
     */
    public final boolean transferMoneyTo(BankAccount targetAccount, double amount) {
        if (targetAccount == null || amount == Double.NaN) {
            return false;
        }
        double lostAmount = amount * TRANSFER_FEE;
        if (balance - lostAmount < 0 || amount < 0) {
            return false;
        }
        targetAccount.balance += amount;
        balance -= amount + lostAmount;

        return true;
    }
}
