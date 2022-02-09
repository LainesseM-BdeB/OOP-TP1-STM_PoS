package qc.ca.bdeb._420_a02_bb.tp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<String, TitlesGroup> groups = readTitlesFile();
        List<String> main = makeList(new ArrayList<>(groups.keySet()));
        Menu mainM = new Menu("Main Menu",
                "Welcome to the STM",
                "Where you purchase your transport titles",
                main);
        mainM.genMenu();
        Scanner uInput = new Scanner(System.in);
        String uIn;
        do {
            mainM.printMenu();
            System.out.println("Quel catégorie de titre voulez vous achetez?");
            uIn = uInput.nextLine().toLowerCase().strip();
            if (uIn.equals("q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                String menuName = mainM.optionsM.get(Integer.parseInt(uIn) - 1);
                Title[] selectedCat = groups.get(menuName).titles;
                List<String> opt = makeList(selectedCat);
                Menu secMenu = new Menu(menuName, menuName, "Choose the options you need?", opt);
                secMenu.genMenu();
                secMenu.printMenu();
                System.out.println("Quel titre voulez vous achetez?");
                uIn = uInput.nextLine().toLowerCase().strip();
                if (uIn.equals("q")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    Title selectedTitle = selectedCat[Integer.parseInt(uIn) - 1];
                    ArrayList<String> priceList = new ArrayList<>();
                    StringBuilder priceLine;
                    int i = 1;
                    for (String n : selectedTitle.pricing.keySet()) {
                        priceLine = new StringBuilder(n);
                        i++;
                        String price = String.format("%.2f$", selectedTitle.pricing.get(n));
                        priceLine.append(" ".repeat(Math.max(0, (40 - n.length() - price.length() - 1))));
                        priceLine.append(price);
                        priceList.add(priceLine.toString());
                    }
                    opt = makeList(priceList);
                    Menu confMenu = new Menu(menuName, menuName, "Here are the available prices?", opt);
                    confMenu.genMenu();
                    confMenu.printMenu();
                    System.out.println("Quelle tarification voulez-vous?");
                    uIn = uInput.nextLine().toLowerCase().strip();
                    if (uIn.equals("q")) {
                        System.out.println("Goodbye!");
                        break;
                    } else {
                        Double[] tempDArr = selectedTitle.pricing.values().toArray(new Double[0]);
                        String[] tempSArr = selectedTitle.pricing.keySet().toArray(new String[0]);
                        double selectedPrice = tempDArr[(Integer.parseInt(uIn) - 1)];
                        Order order = new Order(selectedTitle.name, tempSArr[Integer.parseInt(uIn) - 1], selectedPrice, tempDArr[0]);
                        order.printOrder();
                        System.out.println("Est-ce correct? (O/N)");
                        uIn = uInput.nextLine().toLowerCase().strip();
                        if (uIn.equals("o")) {
                            System.out.printf("Vous devez payer %.2f$.\n", order.price);
                            System.out.println("Payez-vous par carte ou comptant? (1/2)");
                            uIn = uInput.nextLine().toLowerCase().strip();
                            if (uIn.equals("1")) {
                                System.out.println("Merci pour votre achat!\nVoici votre reçu.");
                                order.printReceipt();
                                break;
                            } else {
                                System.out.println("Combien voulez vous donnez d'argent pour payer le total?");
                                uIn = uInput.nextLine().toLowerCase().strip();
                                double amount = Double.parseDouble(uIn);
                                if (amount < order.price) {
                                    System.out.println("Vous n'avez pas assez d'argent pour payer le total.");
                                    break;
                                } else if (amount == order.price) {
                                    System.out.println("Merci pour votre achat!\nVoici votre reçu.");
                                    order.printReceipt();
                                    break;
                                } else {
                                    double toReturn = amount - order.price;
                                    order.calcChange(toReturn);
                                    System.out.println("Voici votre change!");
                                    order.printChange();
                                    System.out.println("Merci pour votre achat!\nVoici votre reçu.");
                                    order.printReceipt();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } while (true);
// TESTING FEATURES
    }

    public static HashMap<String, TitlesGroup> readTitlesFile() throws IOException {
        HashMap<String, TitlesGroup> groups = new LinkedHashMap<>();
        ArrayList<Title> tempGroupTitles = new ArrayList<>();
        String tempGroupName = "";
        File f = new File("resources/titles.txt");
        FileReader fReader = new FileReader(f);
        BufferedReader bfReader = new BufferedReader(fReader);
        while (bfReader.ready()) {
            String line = bfReader.readLine().strip();
            if (!line.contains("=") && !line.isEmpty()) {
                tempGroupName = line;
            } else if (!line.isEmpty()){
                tempGroupTitles.add(new Title(line));
            } else {
                TitlesGroup tempGroup = new TitlesGroup(tempGroupName);
                for (Title t : tempGroupTitles) {
                    tempGroup.addTitle(t);
                }
                groups.put(tempGroupName, tempGroup);
                tempGroupName = "";
                tempGroupTitles = new ArrayList<>();
            }
        }
        return groups;
    }

    public static List<String> makeList(ArrayList<String> arr) {
        return new ArrayList<>(arr);
    }

    public static List<String> makeList(Title[] arr) {
        List<String> list = new ArrayList<>();
        for (Title item : arr) {
            list.add(item.name);
        }
        return list;
    }

}
