import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Source {
    public static void main(String[] args) throws InterruptedException {
        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(3);
        User user5 = new User(4);

        Users users = new Users();
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);
        users.addUser(user4);
        users.addUser(user5);


        //wpłata
        user1.getAccount().get(0).deposit(100);
        user2.getAccount().get(0).deposit(100);


        //transfer
        System.out.println("-----transfer------");
        System.out.println("user1: " + user1.getAccount().get(0).getBalance() + user1.getAccount().get(0).getCurrency());
        System.out.println("user2: " + user2.getAccount().get(0).getBalance() + user2.getAccount().get(0).getCurrency());

        user1.getAccount().get(0).transfer(50, user2);

        //Thread.sleep(1000);

        System.out.println("user1: " + user1.getAccount().get(0).getBalance() + user1.getAccount().get(0).getCurrency());
        System.out.println("user2: " + user2.getAccount().get(0).getBalance() + user2.getAccount().get(0).getCurrency());

        //wyplata
        System.out.println("-----wyplata------");
        user3.getAccount().get(0).deposit(10);
        //Thread.sleep(1000);
        user3.getAccount().get(0).withdraw(8);
        System.out.println("user3: " + user3.getAccount().get(0).getBalance() + user3.getAccount().get(0).getCurrency());
        user3.getAccount().get(0).withdraw(3);
        System.out.println("user3: " + user3.getAccount().get(0).getBalance() + user3.getAccount().get(0).getCurrency());
        //
        user1.getAccount().get(0).withdraw(10);

        //history
        System.out.println("-----history-user1-----");
        System.out.println(user1.getAccount().get(0).getHistory());
        System.out.println("-----history-user2-----");
        System.out.println(user2.getAccount().get(0).getHistory());

        //przewalutowanie
        System.out.println("-----przewalutowanie-user5-----");
        user5.getAccount().get(0).deposit(100);
        System.out.println("user5: " + user5.getAccount().get(0).getBalance() + user5.getAccount().get(0).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(1).getBalance() + user5.getAccount().get(1).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(2).getBalance() + user5.getAccount().get(2).getCurrency());
        user5.getAccount().get(0).exchange(30,"USD");
        user5.getAccount().get(0).exchange(30,"EUR");
        System.out.println("----------");
        System.out.println("user5: " + user5.getAccount().get(0).getBalance() + user5.getAccount().get(0).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(1).getBalance() + user5.getAccount().get(1).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(2).getBalance() + user5.getAccount().get(2).getCurrency());
        user5.getAccount().get(1).exchange(6.96,"PLN");
        user5.getAccount().get(2).exchange(6.47,"PLN");
        System.out.println("----------");
        System.out.println("user5: " + user5.getAccount().get(0).getBalance() + user5.getAccount().get(0).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(1).getBalance() + user5.getAccount().get(1).getCurrency());
        System.out.println("user5: " + user5.getAccount().get(2).getBalance() + user5.getAccount().get(2).getCurrency());
        System.out.println("----------");
        System.out.println(user5.getAccount().get(0).getHistory());
        System.out.println(user5.getAccount().get(1).getHistory());
        System.out.println(user5.getAccount().get(2).getHistory());


        System.out.println("-----history for PLN-----");
        LinkedList<Operation> operations = users.historyCurrency("PLN");
        for(var op : operations){
            System.out.println(op);
        }

        System.out.println("----history for exchange----");
        LinkedList<Operation> operations2 = users.historyOperation("exchange");
        for(var op2 : operations2){
            System.out.println(op2);
        }

        //exchange jest ustawione na localtime.now + 5min wiec nie ma go w historii
        System.out.println("----history date----");
        LinkedList<Operation> operations3 = users.historyDate(
                LocalDateTime.now().minusMinutes(1),
                LocalDateTime.now());
        for(var op3 : operations3){
            System.out.println(op3);
        }

        System.out.println("----history ----");
        LinkedList<Operation> operations4 = users.history();
        for(var op4 : operations4){
            System.out.println(op4);
        }

    }
}
