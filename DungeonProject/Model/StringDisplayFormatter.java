package DungeonProject.Model;

/**
 * String Display Formatter gets strings and displays them in all caps with spaces in between
 * each letter. It is used for non-fixed Strings such as player name, displaying values and
 * equipment names in the status window, and values and item names in the rewards window.
 */
public class StringDisplayFormatter {

    /**
     * spacedNameFormat is used for names in the display windows. It will format the string as follows:
     * input: Christian output: c h r i s t i a n
     * input: Christian Simpson output: c. s i m p s o n
     * input: Joroff Joroffson output: j. j o r o f f s
     * input: Greatsword output: g. s w o r d
     * input: Wooden Sword IX output:  w. s w o r d
     * input: Ring of Defense VIII output: r i n g  o f  d.
     * @param name the String to be formatted
     * @return returns the string in the desired format.
     */
    public String spacedNameFormat(String name) {
        StringBuilder toReturn = new StringBuilder();
        char[] nameArray = name.toCharArray();
        //name limit in window is 9 characters
        if (nameArray.length > 9) {
            //split the name by space character if possible
            String[] names = name.split(" ");
            //for strings consisting of 2 words
            if (names.length == 2) {
                //abbreviate with the first letter of first name followed by period
                char[] firstLetter = {names[0].charAt(0), '.'};
                //add the rest of the characters afterward to the 9 char limit
                char[] lastName = names[1].toCharArray();
                toReturn.append(firstLetter);
                toReturn.append(' ');
                for (int i = 0; i < Math.min(lastName.length - 1, 6); i++) {
                    toReturn.append(lastName[i]);
                    toReturn.append(' ');
                }
                toReturn.append(lastName[Math.min(lastName.length - 1, 6)]);
                //for strings longer than 2 words
            } else if (names.length > 2) {
                //for equipment in status menu, 17 char limit
                //compare each string to find the one with the most characters
                String most = names[0];
                int i, pos = 0;
                for (i = 1; i < names.length; i++) {
                    if (most.length() < names[i].length()) {
                        most = names[i];
                        pos = i;
                    }
                }
                //abbreviate that one
                char[] mostArr = {most.charAt(0), '.'};
                for (i = 0; i < names.length; i++) {
                    if (i == pos) {
                        toReturn.append(mostArr).append(' ');
                    } else {
                        char[] nameArr = names[i].toCharArray();
                        for (char c : nameArr) {
                            toReturn.append(c).append(' ');
                            if (c == names[i].charAt(names[i].length() - 1)) {
                                toReturn.append(' ');
                            }
                        }
                    }
                }
                //for long single words
                //cut them in half and abbreviate the first half
            } else {
                int divider = nameArray.length / 2;
                char[] half1 = {nameArray[0], '.'};
                char[] half2 = new char[divider];
                for(int i = 0; i < half2.length; i++){
                    half2[i] = nameArray[i+divider];
                }
                toReturn.append(half1).append(' ');
                for (char c : half2) {
                    toReturn.append(c).append(' ');
                }
            }
        } else {
                for (int i = 0; i < nameArray.length; i++) {
                    if (i <= nameArray.length - 2) {
                        toReturn.append(nameArray[i]);
                        toReturn.append(' ');
                    } else {
                        toReturn.append(nameArray[i]);
                    }
                }
            }
        return toReturn.toString();
        }

    /**
     * valuesFormat is used for numerical values in the reward and status windows.
     * @param value the value to be formatted
     * @return returns the string in the desired format.
     */
    public String valuesFormat(int value){
        StringBuilder stb = new StringBuilder();
        char[] theValue = String.valueOf(value).toCharArray();
        for (char c : theValue) {
            stb.append(c);
        }
        return stb.toString();
    }

    /**
     * centerString center justifies any printed string according to the width passed to the method.
     * @param width the width of your line.
     * @param s the string to print.
     * @return returns the string with equal space on both sides.
     */
    public String centerString (int width, String s) {
        return String.format("%-" + width  + "S", String.format("%" + (s.length() +
                (width - s.length()) / 2) + "S", s));
    }

    public StringDisplayFormatter(){

    }


}
