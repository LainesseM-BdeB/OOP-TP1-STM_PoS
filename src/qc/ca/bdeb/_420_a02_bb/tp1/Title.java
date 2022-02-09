package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Title {
    String name;
    HashMap<String, Double> pricing = new HashMap<>();

    public Title(String pricing) {
        String[] splitPricing = pricing.split("=");
        this.name = splitPricing[0];
        this.pricing = splitPricingString(splitPricing[1]);
    }

    private HashMap<String, Double> splitPricingString(String pricing) {
        String[] pricingSplit;
        HashMap<String, Double> pricingMap = new LinkedHashMap<>();
        pricingSplit = pricing.split(",\s+|,");
        int i = 1;
        for (String item : pricingSplit) {
            String[] splitItem = item.split("-");
            pricingMap.put(i + "- " + splitItem[0], Double.valueOf(splitItem[1]));
            i++;
        }
        return pricingMap;
    }
}
