package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        String name = "Test Name";
        String title = "Option selector 2000";
        String desc = "Choose an option from the list below:";
        String[] opt = {
                "1 - Option test 1",
                "2 - Option test 2",
                "3 - Option test 3",
                "4 - Option test 4"
        };
	    Menu m = new Menu(name, title, desc, opt);
        m.genMenu();
        m.printMenu();
// TESTING FEATURES
        String[] pricingSplit;
        String pricing = "1 Passage=Régulier-3.50, 6 à 17 ans-2.50, 65 ans et +-1.00";
        String pricing2 = "2 Passage=Régulier-6.50, 6 à 17 ans-4.25, 65 ans et +-2.00";
        Title testTitle = new Title(pricing);
        Title testTitle2 = new Title(pricing2);
        TitlesGroup testGroup = new TitlesGroup("Test");
        testGroup.printTitles();
        testGroup.addTitle(testTitle);
        testGroup.printTitles();
        testGroup.addTitle(testTitle2);
        testGroup.printTitles();
    }
}
