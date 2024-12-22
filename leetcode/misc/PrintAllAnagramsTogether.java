package leetcode.misc;

import java.util.*;

public class PrintAllAnagramsTogether {

    /**
     * https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
     * To print all anagrams together from a sequence of words, the goal is to group words that are anagrams of each other.
     * Approach
     * Definition of Anagrams: Two words are anagrams if they contain the same characters in the same frequency, irrespective of the order.
     * Key Idea: Sort each word alphabetically and use the sorted word as a key. Group all words that have the same sorted representation.
     * Steps:
     * Create a map (HashMap) to group words by their sorted form.
     * Iterate through the list of words:
     *      Sort each word.
     *      Add it to the corresponding group in the map.
     * Print all groups from the map.
     * @param words
     */
    public static void printAnagrams(String[] words) {
        // Step 1: Create a map to group anagrams
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Step 2: Process each word
        for (String word : words) {
            // Sort the characters in the word
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            // Add the word to the corresponding group in the map
            anagramMap.computeIfAbsent(sortedWord, key -> new ArrayList<>()).add(word);
        }

        // Step 3: Print all anagram groups
        for (List<String> group : anagramMap.values()) {
            System.out.println(group);
        }
    }
}
