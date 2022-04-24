public interface AccountInterface {
    void deposit(double amount);

    void withdraw(double amount);

    void exchange(double amount, String currency);

    void transfer(double amount, User toTransfer);
}
