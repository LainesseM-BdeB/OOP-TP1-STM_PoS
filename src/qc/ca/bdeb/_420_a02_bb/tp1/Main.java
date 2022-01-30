package qc.ca.bdeb._420_a02_bb.tp1;

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
    }
}
