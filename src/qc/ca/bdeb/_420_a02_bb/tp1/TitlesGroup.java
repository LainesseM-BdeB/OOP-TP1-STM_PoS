package qc.ca.bdeb._420_a02_bb.tp1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TitlesGroup {
    String name;
    Title[] titles;

    /**
     * Object that takes the name of a Category of Titles.<br>
     * It can then be filled with the method addTitle.
     */
    public TitlesGroup(String name) {
        this.name = name;
    }

    /**
     * Method that adds a Title to the group.<br>
     * If the group was empty, it generates the new array.
     */
    public void addTitle(Title title) {
        if (this.titles == null) {
            this.titles = new Title[]{title};
        } else {
            ArrayList<Title> temp_titles = new ArrayList<>(Arrays.asList(this.titles));
            temp_titles.add(title);
            this.titles = new Title[temp_titles.size()];
            temp_titles.toArray(this.titles);
        }
    }

    /**
     * Method that prints the content of the TitlesGroup.<br>
     * Used only for testing purposes.
     */
    public void printTitles() {
        if (this.titles == null) {
            System.out.println("There are no titles in this group.");
        } else {
            for (Title t : this.titles) {
                System.out.println(t.name);
                for (String tp : t.pricing.keySet()) {
                    System.out.printf("%s is %.2f$\n", tp, t.pricing.get(tp));
                }
                System.out.println("----------------------------------------");
            }
        }
    }

}
