package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    String nameM;
    String titleM;
    String descM;
    String[] optionsM;
    String[] menuM;
    String borderM = "#"; //Character to use for the menu border
    String borderLine = "";
    String skipLine = "";
    Map<Integer, String> locHeadBlock = new HashMap<>(); //Map to place precisely the header's text lines
    int widthM = 50; //Width of the menu
    int widthMargingM = 5; //Margin of the menu for each side
    int widthMaxTextM = widthM - (widthMargingM * 2); //Max length of the text allowed based on menu width and margins
    int headLength = 7; //Length of the menu's header
    int optionsLength;

    public Menu(String name, String title, String desc, String[] options) throws Exception {
        nameM = name;
        //Checks for any text value that exceeds the maximum width allowed for the menu and returns an error if
        //the text is longer.
        if (title.length() > widthMaxTextM) {
            String errMsg = "You cannot have a title more than " + (widthMaxTextM)
                    + " characters long.\nThe title is " + title.length() + " characters long.\nIt cuts at ("
                    + title.substring(0, widthMaxTextM) + ").";
            throw new Exception(new AssertionError(errMsg));
        } else {
            titleM = title.toUpperCase();
        }
        if (desc.length() > widthMaxTextM) {
            String errMsg = "You cannot have a description more than " + (widthMaxTextM)
                    + " characters long.\nThe description is " + desc.length() + " characters long.\nIt cuts at ("
                    + desc.substring(0, widthMaxTextM) + ").";
            throw new Exception(new AssertionError(errMsg));
        } else {
            descM = desc;
        }
        for (String opt : options) {
            if (opt.length() > widthMaxTextM) {
                String errMsg = "You cannot have an option more than " + (widthMaxTextM)
                        + " characters long.\nThe option (" + opt + ") is " + opt.length()
                        + " characters long.\n It cuts at (" + opt.substring(0, widthMaxTextM) + ").";
                throw new Exception(new AssertionError(errMsg));
            }
        }
        optionsM = options;
        //Creates the skip line and border line used in the menu creation
        for (int i = 0; i < widthM; i++) {
            if (i >= 1 && i < widthM - 1) {
                skipLine += " ";
            } else {
                skipLine += borderM;
            }
            borderLine += borderM;
        }
        skipLine += "\n";
        borderLine += "\n";
        optionsLength = optionsM.length;
        //Initialise the locHeadBlock map that is used to place the title and description at the correct location
        //Not totally necessary but might come in handy if the ui is updated
        locHeadBlock.put(2, "Title");
        locHeadBlock.put(4, "Description");
    }

    //Method to generate a menu
    public void genMenu() {
        String line = "";
        int lengthMenu = 6 + (optionsM.length * 2) + 4;
        menuM = new String[lengthMenu];
        String[] header = genHeader();
        System.arraycopy(header, 0, menuM, 0, header.length);
        String[] options = genOptions();
        System.arraycopy(options, 0, menuM, header.length, options.length);
    }

    //Method to generate the header of the menu based on a specific design, might need to make it more dynamic
    private String[] genHeader() {
        String[] headBody = new String[headLength];
        for (int i = 0; i < headLength; i++) {
            if (i == 0 | i == headLength - 1) {
                headBody[i] = borderLine;
            } else if (locHeadBlock.get(i) != null && locHeadBlock.get(i).equals("Title")) {
                headBody[i] = genTextLine(titleM, true);
            } else if (locHeadBlock.get(i) != null && locHeadBlock.get(i).equals("Description")) {
                headBody[i] = genTextLine(descM, true);
            } else {
                headBody[i] = skipLine;
            }
        }
        return headBody;
    }

    //Method to generate the options part of the menu. It will add a skip line between each option.
    private String[] genOptions() {
        int optionBodyLength = optionsLength * 2 + 3;
        String[] optionsBody = new String[optionBodyLength];
        for (int i = 0; i < optionBodyLength; i++) {
            if (i == 0 | i == optionBodyLength - 1) {
                optionsBody[i] = borderLine;
            } else if (i == 1) {
                optionsBody[i] = skipLine;
            } else {
                for (String opt : optionsM) {
                    optionsBody[i] = genTextLine(opt, false);
                    i++;
                    optionsBody[i] = skipLine;
                    i++;
                }
                i--;
            }
        }
        return optionsBody;
    }

    //Method to generate the lines in the menu that contains dynamic text.
    //It also accepts a bool to center or left align the text.
    private String genTextLine(String textM, Boolean centered) {
        // Put a check for mid or left align
        int startText;
        int lengthText = textM.length();
        if (centered) {
            startText = (widthM - lengthText) / 2;
        } else {
            startText = 5;
        }
        String finalText = "";
        for (int j = 0; j <= widthM; j++) {
            if (j >= startText && j <= startText + lengthText) {
                if (j == startText) {
                    finalText += textM;
                } else continue;
            } else if (j == 0 | j == widthM) {
                finalText += borderM;
            } else {
                finalText += " ";
            }
        }
        finalText += "\n";
        return finalText;
    }

    //Method to print the menu
    public void printMenu() {
        for (String ln : menuM) {
            System.out.print(ln);
        }
    }
}
