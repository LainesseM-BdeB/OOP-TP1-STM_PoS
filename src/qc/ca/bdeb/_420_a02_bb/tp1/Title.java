package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Title {
    String name;
    HashMap<String, Double> pricing = new HashMap<>();

    /**
     * Title accepts a pricing String in this format:<br>
     * x- name=pricingName-price, pricingName-price,...<br>
     * i.e.: 1- 1 Passage=Régulier-3.50, 6 à 17 ans-2.50, 65 ans et +-1.00<br>
     *<br>
     * These titles will be used to make the menus and generate the order/invoice<br>
     */
    public Title(String pricing) {
        // Splits the pricing on '='
        String[] splitPricing = pricing.split("=");
        this.name = splitPricing[0];
        this.pricing = splitPricingString(splitPricing[1]);
    }

    /**
     * Method that takes a pricing string and splits it between pricing name and price.<br>
     * It returns a Map of the result.
     */
    private HashMap<String, Double> splitPricingString(String pricing) {
        String[] pricingSplit;
        HashMap<String, Double> pricingMap = new LinkedHashMap<>();
        // Splits the pricing on a ', ' or a ','
        pricingSplit = pricing.split(",\s+|,");
        int i = 1;
        for (String item : pricingSplit) {
            // Splits the item on '-'
            String[] splitItem = item.split("-");
            pricingMap.put(i + "- " + splitItem[0], Double.valueOf(splitItem[1]));
            i++;
        }
        return pricingMap;
    }
}
