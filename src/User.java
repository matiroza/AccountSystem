import java.util.LinkedList;
import java.util.List;

public class User {
    private final int ID;

    public List<Account> getAccount() {
        return account;
    }

    private List<Account> account;

    public int getID() {
        return ID;
    }

    public User(int id) {
        this.ID = id;
        inintAccounts();
    }

    private void inintAccounts() {
        account = new LinkedList<>();
        account.add(new Account(0, "PLN", this.ID, this));
        account.add(new Account(0, "USD", this.ID, this));
        account.add(new Account(0, "EUR", this.ID, this));
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                '}';
    }
}
