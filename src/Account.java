import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Account implements AccountInterface{
    public double getBalance() { return balance; }

    public String getCurrency() { return currency; }

    private double balance;
    private String currency;
    private Integer userID;
    private List<Operation> history;
    User user;

    public String getHistory() {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");

        for(var operation : history)
            result.append(operation.message).append("  |  ").append(dtf.format(operation.now)).append("\n");
        result.append("current balance: ").append(balance).append(" ").append(currency);

        return String.valueOf(result);
    }

    public List<Operation> history(){
        return history;
    }

    public Account(int balance, String currency, Integer userID, User user) {
        this.balance = balance;
        this.currency = currency;
        this.userID = userID;
        this.history = new LinkedList<Operation>();
        this.user = user;
    }

    @Override
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            history.add(new Operation(
                    "ID = " + this.userID + ", " +"deposit: " + amount,
                    LocalDateTime.now(),
                    currency,
                    "deposit"));
        } else
            System.out.println("");
    }

    public void deposit(double amount, int from) {
        if(amount > 0) {
            balance += amount;
            history.add(new Operation(
                    "ID = " + this.userID + ", " +"deposit: " + amount + " from ID = " + from,
                    LocalDateTime.now(),
                    currency,
                    "deposit"));
        } else
            System.out.println("");
    }

    public void deposit(double amount, String curr) {
        if(amount > 0) {
            balance += amount;
            history.add(new Operation(
                    "ID = " + this.userID + ", " +"deposit: " + amount + " from " + curr,
                    LocalDateTime.now(),
                    currency,
                    "deposit"));
        } else
            System.out.println("");
    }

    @Override
    public void withdraw(double amount) {
        if(balance - amount >= 0){
            balance -= amount;
            history.add(new Operation(
                    "ID = " + this.userID + ", " + "withdraw: " + amount,
                    LocalDateTime.now(),
                    currency,
                    "withdraw"));
        } else
            System.out.println("nie masz wystarczająco środków");


    }

    @Override
    public void exchange(double amount, String curr) {
        if(amount <= balance){
            double rate = checkRate(this.currency, curr);
            double tempAmount = amount * rate;
            user.getAccount().get(getAccount(curr)).deposit(Math.floor(tempAmount*100.0)/100.0, this.currency);
            balance -= amount;
            history.add(new Operation(
                    "ID = " + this.userID + ", " + "exchange: " + amount +" to " + curr,
                    LocalDateTime.now().plusMinutes(5),
                    currency,
                    "exchange"));
        } else {
            System.out.println("nie masz wystarczająco środków");
        }
    }

    private double checkRate(String currFrom, String currTo) {
        CurrencyRate currencyRate = new CurrencyRate();
        String s = currFrom + "to" + currTo;
        return currencyRate.getRate(s);
    }

    @Override
    public void transfer(double amount, User toTransfer) {
        if(balance - amount >= 0){
            Account acc = toTransfer.getAccount().get(getAccount(this.currency));
            acc.deposit(amount, this.userID);
            this.balance -= amount;
            //dodanie historii transferu
            history.add(new Operation(
                    "ID = " + this.userID + ", " + "transfer: " + amount + " to " +  "ID = " +toTransfer.getID(),
                    LocalDateTime.now(),
                    currency,
                    "transfer"));
        } else {
            System.out.println("nie masz wystarczająco środków");
        }
    }

    private int getAccount(String cur){
        return switch (cur){
            case "PLN" -> 0;
            case "USD" -> 1;
            case "EUR" -> 2;
            default -> -1;
        };
    }
}
