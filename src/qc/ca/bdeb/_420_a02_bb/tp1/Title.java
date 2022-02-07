package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;

public class Title {
    String name;
    HashMap<String, Double> pricing = new HashMap<String, Double>();

    public Title(String pricing) {
        String[] splitPricing = pricing.split("=");
        this.name = splitPricing[0];
        this.pricing = splitPricingString(splitPricing[1]);
    }

    private HashMap<String, Double> splitPricingString(String pricing) {
        String[] pricingSplit;
        HashMap<String, Double> pricingMap = new HashMap<String, Double>();
        pricingSplit = pricing.split(",\s+|,");
        for (String item : pricingSplit) {
            String[] splitItem = item.split("-");
            pricingMap.put(splitItem[0], Double.valueOf(splitItem[1]));
        }
        return pricingMap;
    }
}
