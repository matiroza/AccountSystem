import java.time.LocalDateTime;

public class Operation {
    public String message;
    public LocalDateTime now;
    public String currency;
    public String ope;

    public Operation(String message, LocalDateTime now, String currency, String ope) {
        this.message = message;
        this.now = now;
        this.currency = currency;
        this.ope = ope;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "message='" + message + '\'' +
                ", currency='" + currency + '\'' +
                ", ope='" + ope + '\'' +
                '}';
    }
}
