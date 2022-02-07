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
        String pricing = "1 passage=regular-10.00, student-8.00, senior-5.00";
        Title testTitle = new Title(pricing);
        System.out.println(testTitle.name);
        for (String test : testTitle.pricing.keySet()) {
            System.out.println(test + " - " + testTitle.pricing.get(test));        }
    }
}
