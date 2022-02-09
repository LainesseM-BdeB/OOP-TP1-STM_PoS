package qc.ca.bdeb._420_a02_bb.tp1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TitlesGroup {
    String name;
    Title[] titles;

    public TitlesGroup(String name) {
        this.name = name;
    }

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
