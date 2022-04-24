import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Users {
    public List<User> usersList;

    public Users(){
        usersList = new LinkedList<>();
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public Users(LinkedList<User> usersList) {
        this.usersList = usersList;
    }

    public void addUser(User user){
        int id = user.getID();
        User tempUser = usersList.stream().filter(u -> u.getID() == id).findAny().orElse(null);

        if(tempUser == null)
            usersList.add(user);
        else System.out.println("Dany użytkownik już istniej: " + user);
    }

    public LinkedList<Operation> historyCurrency(String currency) {
        LinkedList<Operation> operations = new LinkedList<>();
        for (var user : usersList)
            for (var acc : user.getAccount())
                for (var line : acc.history())
                    if (line.currency.equals(currency))
                        operations.add(line);
        return operations;
    }

    public LinkedList<Operation> historyOperation(String operation) {
        LinkedList<Operation> operations = new LinkedList<>();
        for (var user : usersList)
            for (var acc : user.getAccount())
                for (var line : acc.history())
                    if (line.ope.equals(operation))
                        operations.add(line);
        return operations;
    }

    public LinkedList<Operation> historyDate(LocalDateTime start, LocalDateTime end) {
        LinkedList<Operation> operations = new LinkedList<>();
        for (var user : usersList)
            for (var acc : user.getAccount())
                for (var line : acc.history())
                    if (line.now.isAfter(start) && line.now.isBefore(end))
                        operations.add(line);
        return operations;
    }

    public LinkedList<Operation> history() {
        LinkedList<Operation> operations = new LinkedList<>();
        for (var user : usersList)
            for (var acc : user.getAccount())
                operations.addAll(acc.history());
        return operations;
    }

}
