import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class CurrencyRate {
    private double USDtoPLN;
    private double USDtoEUR;
    private double PLNtoEUR;
    private double PLNtoUSD;
    private double EURtoUSD;
    private double EURtoPLN;

    public CurrencyRate() {
        try {
            init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initRest();
    }

    private void initRest() {
        PLNtoEUR = USDtoEUR/USDtoPLN;
        PLNtoUSD = 1.0/USDtoPLN;
        EURtoUSD = 1.0/USDtoEUR;
        EURtoPLN = 1/PLNtoEUR;
    }

    public double getRate(String s){
        return switch (s){
            case "USDtoPLN" -> USDtoPLN;
            case "USDtoEUR" -> USDtoEUR;
            case "PLNtoEUR" -> PLNtoEUR;
            case "PLNtoUSD" -> PLNtoUSD;
            case "EURtoUSD" -> EURtoUSD;
            case "EURtoPLN" -> EURtoPLN;
            default -> -1;
        };
    }

    private void init() throws FileNotFoundException {
            String fileName = "currency.csv";
            Scanner s = new Scanner(new File(fileName));
            LinkedList<String[]> lines = new LinkedList<>();
            while (s.hasNext()) {
                lines.add(s.nextLine().split(","));
            }
            s.close();
            USDtoPLN = Double.parseDouble(lines.get(0)[1]);
            USDtoEUR = Double.parseDouble(lines.get(1)[1]);

    }

}
