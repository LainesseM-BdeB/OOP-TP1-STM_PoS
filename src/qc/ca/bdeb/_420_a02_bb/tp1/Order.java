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

    public Order(String title, String rebate, double price, double regularPrice) {
        this.title = title.substring(3);
        this.rebateName = rebate.substring(3);
        this.regularPrice = regularPrice;
        this.price = price;
        this.savings = regularPrice - price;
    }

    public void printOrder() {
        System.out.printf("Votre commande contient:\nTitre: %s\nTarif: %s\nPrix: %.2f$\n",
                           this.title,
                           this.rebateName,
                           this.price);
    }

    public void printReceipt() {
        System.out.println("****************************************");
        System.out.println("Titre: " + this.title);
        System.out.printf("Prix Régulier: %.2f$\n", this.regularPrice);
        System.out.println("Tarif: " + this.rebateName);
        System.out.printf("Prix avec rabais: %.2f$\n", this.price);
        System.out.printf("Vos économies: %.2f$\n", this.savings);
        System.out.println("****************************************");
        System.out.printf("Vous avez payé: %.2f$\n", this.price);
        System.out.println("****************************************");
        System.out.println("Merci de faire confiance à la STM");
        System.out.println("****************************************");
    }

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

    public void printChange() {
        for (String money : this.change.keySet()) {
            if (!(this.change.get(money) == 0)) {
                System.out.printf("%s - %d\n", money, this.change.get(money));
            }
        }
    }
}
