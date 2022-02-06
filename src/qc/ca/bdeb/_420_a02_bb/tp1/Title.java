package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;

public class Title {
    String name;
    HashMap<String, Double> pricing = new HashMap<String, Double>();

    public Title(String name, String pricing) {
        this.name = name;

    }

    private HashMap<String, Double> splitPricingString(String pricing) {
        String[] pricingSplit;
        HashMap<String, Double> pricingMap = new HashMap<String, Double>();
        pricingSplit = pricing.split(",");
        for (String item : pricingSplit) {
            System.out.println(item);
        }
        return pricingMap;
    }
}
