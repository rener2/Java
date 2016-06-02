/**
 * Create and manage accounts.
 */
public class EX06 {

    /**
     * Create and manage.
     * @param args for console
     */
    public static void main(String[] args) {
        BankAccount first = new BankAccount();
        BankAccount second = new BankAccount();
        first.addMoney(101.0);
        second.addMoney(200.0);

        first.transferMoneyTo(second, 100.0);
        System.out.println(second.getBalance()); // 300.0
        System.out.println(first.getBalance()); // 0.0
        second.withdrawMoney(67.3);
        System.out.println(second.getBalance()); // 232.7
    }
}
