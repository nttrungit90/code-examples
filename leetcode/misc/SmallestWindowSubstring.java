package leetcode.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SmallestWindowSubstring {

    /**
     * Problem: https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
     * Approach:
     * Use a Frequency Map:
     *      Create a frequency map of characters in string s2 to know how many of each character is required.
     * Sliding Window:
     * Expand the window to include characters from s1 until it contains all characters of s2.
     *      Once all characters are included, shrink the window from the left to minimize its size while still satisfying the condition.
     * Track the Minimum Window:
     *      Keep track of the smallest window's length and starting index as you expand and shrink the window.
     *
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String findSmallestWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return ""; // No valid window exists
        }

        // Step 1: Create a frequency map for s2
        HashMap<Character, Integer> targetFreq = new HashMap<>();
        for (char c : s2.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sliding window variables
        HashMap<Character, Integer> windowFreq = new HashMap<>();
        int start = 0, minStart = 0, minLen = Integer.MAX_VALUE;
        int matched = 0; // Count of characters matched

        // Step 3: Expand the window using the 'end' pointer
        for (int end = 0; end < s1.length(); end++) {
            char endChar = s1.charAt(end);
            windowFreq.put(endChar, windowFreq.getOrDefault(endChar, 0) + 1);

            // If the character in window matches the frequency in target
            if (targetFreq.containsKey(endChar) &&
                    windowFreq.get(endChar).equals(targetFreq.get(endChar))) {
                matched++;
            }

            // Step 4: Shrink the window from the left to minimize its size
            while (matched == targetFreq.size()) {
                int windowLen = end - start + 1;
                if (windowLen < minLen) {
                    minLen = windowLen;
                    minStart = start;
                }

                char startChar = s1.charAt(start);
                windowFreq.put(startChar, windowFreq.get(startChar) - 1);

                if (targetFreq.containsKey(startChar) &&
                        windowFreq.get(startChar) < targetFreq.get(startChar)) {
                    matched--;
                }
                start++;
            }
        }

        // Step 5: Return the result
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(minStart, minStart + minLen);
    }
}
