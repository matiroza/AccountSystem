public class CurrencyRate {
    private double USDtoPLN = 4.31;
    private double USDtoEUR = 0.93;
    private double PLNtoEUR = USDtoEUR/USDtoPLN;
    private double PLNtoUSD = 1.0/USDtoPLN;
    private double EURtoUSD = 1.0/USDtoEUR;
    private double EURtoPLN = 1/PLNtoEUR;

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

}
