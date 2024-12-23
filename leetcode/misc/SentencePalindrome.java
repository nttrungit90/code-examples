package leetcode.misc;

public class SentencePalindrome {

    /**
     * https://www.geeksforgeeks.org/sentence-palindrome-palindrome-removing-spaces-dots-etc/
     * A palindrome sentence is a sequence of characters, such as a word, phrase, or series of symbols, that reads the same backward
     * as forward after converting all uppercase letters to lowercase and removing all non-alphanumeric characters.
     */
    static boolean isSentencePalindrome(String s) {
        // create a new string having only alphanumeric characters
        StringBuilder s1 = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                s1.append(Character.toLowerCase(ch));
            }
        }

        // find reverse of this new string
        StringBuilder rev = new StringBuilder(s1.toString());
        rev.reverse();

        // compare string and its reverse
        return s1.toString().equals(rev.toString());
    }

    static boolean isSentencePalindromeWith2Pointer(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
            } else if(!Character.isLetterOrDigit(s.charAt(j))){
                j++;
            } else if(Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))){
                i++;
                j++;
            }  else {
                return false;
            }
        }
        return true;
    }
}
