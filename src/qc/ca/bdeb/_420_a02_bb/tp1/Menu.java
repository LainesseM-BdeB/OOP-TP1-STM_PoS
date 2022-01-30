package qc.ca.bdeb._420_a02_bb.tp1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Menu {
    String nameM;
    String titleM;
    String descM;
    String[] optionsM;
    String[] menuM;
    String borderM = "#";
    String borderLine = "";
    String skipLine = "";
    Map<Integer, String> locHeadBlock = new HashMap<>();
    int widthM = 50;
    int headLength = 7;
    int optionsLength;

    public Menu(String name, String title, String desc, String[] options) throws Exception {
        nameM = name;
        if (title.length() > widthM - 2) {
            String errMsg = "You cannot have a title more than " + (widthM - 2) + " characters long.";
            throw new Exception(new AssertionError(errMsg));
        } else {
            titleM = title;
        }
        if (desc.length() > widthM - 2) {
            String errMsg = "You cannot have a description more than " + (widthM - 2) + " characters long.";
            throw new Exception(new AssertionError(errMsg));
        } else {
            descM = desc;
        }
        for (String opt : options) {
            if (opt.length() > widthM - 2) {
                String errMsg = "You cannot have an option more than " + (widthM - 2) + " characters long.";
                throw new Exception(new AssertionError(errMsg));
            }
        }
        optionsM = options;
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
        locHeadBlock.put(2, "Title");
        locHeadBlock.put(4, "Description");
    }

    public void genMenu() {
        String line = "";
        int lengthMenu = 6 + (optionsM.length * 2) + 5;
        menuM = new String[lengthMenu];
        String[] header = genHeader();
        System.arraycopy(header, 0, menuM, 0, header.length);
        String[] options = genOptions();
        System.arraycopy(options, 0, menuM, header.length, options.length);
    }

    private String[] genHeader() {
        String[] headBody = new String[headLength];
        for (int i = 0; i < headLength; i++) {
            if (i == 0 | i == headLength - 1) {
                headBody[i] = borderLine;
            } else if (locHeadBlock.get(i) != null && locHeadBlock.get(i).equals("Title")) {
                headBody[i] = genTextLine(titleM);
            } else if (locHeadBlock.get(i) != null && locHeadBlock.get(i).equals("Description")) {
                headBody[i] = genTextLine(descM);
            } else {
                headBody[i] = skipLine;
            }
        }
        return headBody;
    }

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
                    optionsBody[i] = genTextLine(opt);
                    i++;
                    optionsBody[i] = skipLine;
                    i++;
                }
                i--;
            }
        }
        return optionsBody;
    }

    private String genTextLine(String textM) {
        // Put a check for mid or left align
        int lengthTitle = textM.length();
        int startTitle = (widthM - lengthTitle) / 2;
        String stringTitle = "";
        for (int j = 0; j <= widthM; j++) {
            if (j >= startTitle && j <= startTitle + lengthTitle) {
                if (j == startTitle) {
                    stringTitle += textM;
                } else continue;
            } else if (j == 0 | j == widthM) {
                stringTitle += borderM;
            } else {
                stringTitle += " ";
            }
        }
        stringTitle += "\n";
        return stringTitle;
    }
}
