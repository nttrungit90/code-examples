package leetcode.easy;

public class EncodeConsecutiveChar {

    /**
     * In an encoding scheme when characters are consecutive, shorten it by encoding it
     * Eg: the character string YYYYY, is expressed as hashtag#5Y. Write a program that
     * shortens the string Input the text: XYZZZZZZ10000%2%%13 XYhashtag#6Z1#40%2#2%13 = fixed length *
     *
     * Approach
     * Iterate through the string: For each character in the string, count how many times it repeats consecutively.
     * Encode repeate
     * d characters: If a character repeats, replace the sequence with #<count><character>.
     * Handle non-repeating characters: If a character doesn't repeat, just add it as is.
     * Return the result: Combine the transformed characters and return the shortened version of the string.
     */
    public static String encode(String input) {
        StringBuilder encodedString = new StringBuilder();
        int length = input.length();

        int i = 0;
        while (i < length) {
            char currentChar = input.charAt(i);
            int count = 1;

            // Count consecutive characters
            while (i + 1 < length && input.charAt(i + 1) == currentChar) {
                count++;
                i++;
            }

            // Append the result
            if (count > 1) {
                encodedString.append("hashtag#").append(count).append(currentChar);
            } else {
                encodedString.append(currentChar);
            }

            i++;
        }

        return encodedString.toString();
    }
}
