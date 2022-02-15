package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Order {
    String title;
    String rebateName;
    double regularPrice;
    double price;
    double savings;
    HashMap<String, Integer> change;

    /**
     * Order is an object that is generated as the user navigates through the menus.<br>
     * It contains:<br>
     * <ul>
     *     <li>Name of the title</li>
     *     <li>Name of the pricing selected</li>
     *     <li>Price without rebate (Regular)</li>
     *     <li>Price with the according to the selected pricing</li>
     *     <li>Amount of the rebate</li>
     * </ul>
     * <br>
     * It is used to generate the receipt at the end of the transaction.
     */
    public Order(String title, String rebate, double price, double regularPrice) {
        this.title = title.substring(3);
        this.rebateName = rebate.substring(3);
        this.regularPrice = regularPrice;
        this.price = price;
        this.savings = regularPrice - price;
    }

    /**
     * Method that will print the current order for user validation before the payment.
     */
    public void printOrder() {
        System.out.printf("Votre commande contient:\nTitre: %s\nTarif: %s\nPrix: %.2f$\n",
                           this.title,
                           this.rebateName,
                           this.price);
    }

    /**
     * Method that will print the receipt at the end of the transaction with the following format:<br>
     * <ul>
     *     <li>"*********************************************"</li>
     *     <li>"Titre: " + this.title</li>
     *     <li>"Prix Régulier: %.2f$\n", this.regularPrice</li>
     *     <li>"Tarif: " + this.rebateName</li>
     *     <li>"Prix avec rabais: %.2f$\n", this.price</li>
     *     <li>"Vos économies: %.2f$\n", this.savings</li>
     *     <li>"*********************************************"</li>
     *     <li>"Vous avez payé: %.2f$\n", this.price</li>
     *     <li>"*********************************************"</li>
     *     <li>"Merci de faire confiance à la STM"</li>
     *     <li>"*********************************************"</li>
     * </ul>
     */
    public void printReceipt() {
        System.out.println("*********************************************");
        System.out.println("Titre: " + this.title);
        System.out.printf("Prix Régulier: %.2f$\n", this.regularPrice);
        System.out.println("Tarif: " + this.rebateName);
        System.out.printf("Prix avec rabais: %.2f$\n", this.price);
        System.out.printf("Vos économies: %.2f$\n", this.savings);
        System.out.println("*********************************************");
        System.out.printf("Vous avez payé: %.2f$\n", this.price);
        System.out.println("*********************************************");
        System.out.println("Merci de faire confiance à la STM");
        System.out.println("*********************************************");
    }

    /**
     * Method to calculate the change to return.<br>
     * It will only return bills of 20$, 10$ and 5$.<br>
     * It will only return coins of 2$, 1$, 0.25$, 0.10$ and 0.05$<br>
     * <br>
     * It returns a HashMap of the bills or coins with the amount of each to return.
     * Anything below 0.05$ is discarded and kept by the merchant.
     */
    public void calcChange(double amount) {
        double[] changeType = {20.0, 10.0, 5.0, 2.0, 1.0, 0.25, 0.10, 0.05};
        HashMap<String, Integer> change = new LinkedHashMap<>();
        for (double d : changeType) {
            String dStr = String.format("%.2f$", d);
            change.put(dStr, 0);
            while (amount >= d) {
                change.replace(dStr, change.get(dStr) + 1);
                amount -= d;
            }
        }
        this.change = change;
    }

    /**
     * Method to print the change to return without showing the bills or coins that are at 0.
     */
    public void printChange() {
        for (String money : this.change.keySet()) {
            if (!(this.change.get(money) == 0)) {
                System.out.printf("%s - %d\n", money, this.change.get(money));
            }
        }
    }
}
