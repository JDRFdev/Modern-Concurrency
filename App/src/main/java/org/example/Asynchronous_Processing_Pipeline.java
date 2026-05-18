package org.example;

public class Asynchronous_Processing_Pipeline {

    public static String fetchPrice(){
        try {
            Thread.sleep(200);
            return "Price: $456.000";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String fetchInventory(){
        try {
            Thread.sleep(200);
            return "All it's good :D";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String fetchShippingDate(){
        try {
            Thread.sleep(200);
            return "31/6/2026";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }
}
