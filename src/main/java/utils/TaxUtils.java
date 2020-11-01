package utils;


public class TaxUtils {

    public static Double getValueWithAddedTax(Double price, Double tax){
        return (double) Math.round((price + (price * tax)) * 100d) /  100d;
    }
}
