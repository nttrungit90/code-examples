package leetcode.misc;

public class LongestPalindromicSubString {

    /**
     * https://www.geeksforgeeks.org/longest-palindromic-substring/
     * Naive Approach
     * Generate all substrings.
     * For each substring, check if it is palindrome or not.
     * If substring is Palindrome, then update the result on the basis of longest palindromic substring found till now.
     * Time complexity: O(N3). Three nested loops are needed to find the longest palindromic substring in this approach.
     * Auxiliary complexity: O(1). As no extra space is needed.
     */

    static String longestPalindrome(String s) {
        int start = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i ; j < s.length(); j++) {
                if(isPalindrome(s, i, j) && maxLength < j - i + 1) {
                    start = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    static boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
