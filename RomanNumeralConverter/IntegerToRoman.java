package RomanNumeralConverter;

public class IntegerToRoman {

    public String intToRoman(int num) {
        //values correspond to roman numerals
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        //for each value,
        for (int i = 0; i < values.length; i++) {
            //subtract from the passed number until you are less than the current value in array
            while (num >= values[i]) {
                num = num - values[i];
                //add that roman numeral to the string
                roman.append(romanLetters[i]);
            }
            //repeat until you have a correct string
        }
        return roman.toString();
    }

    public IntegerToRoman(){

    }

}
