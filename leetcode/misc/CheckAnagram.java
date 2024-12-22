package leetcode.misc;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagram {

    /**
     *
     * The idea is to use a hash map or dictionary count the frequency of each character in both the input strings.
     * If the frequency of every character matches in both strings, then the strings are anagrams.
     * First, count the occurrences of each character in first string.
     * Then, decrement the count for each character in the second string.
     * If the strings are anagrams, all positions in the frequency array should be zero as any non-zero position
     * means that the frequency of that character is not same in both strings.
     * @param s1
     * @param s2
     * @return
     */
    static boolean areAnagrams(String s1, String s2) {

        // Create a hashmap to store character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        int[] freq = new int[100];
        freq[s1.charAt(1) - 'a']++;
        // Count frequency of each character in string s1
        for (char ch : s1.toCharArray())
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

        // Count frequency of each character in string s2
        for (char ch : s2.toCharArray())
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

        // Check if all frequencies are zero
        for (var pair : charCount.entrySet()) {
            if (pair.getValue() != 0) {
                return false;
            }
        }

        // If all conditions satisfied, they are anagrams
        return true;
    }
}
