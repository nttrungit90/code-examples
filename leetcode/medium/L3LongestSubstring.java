package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L3LongestSubstring {

    /**
     * Problem: Function to find the length of the longest
     * substring without repeating characters
     *
     * Solution: Start the outer loop from the beginning of the string.
     * For each starting index, use an inner loop to find the longest substring starting at that index.
     * Use a Set to detect repeated characters efficiently.
     * Update the maximum length when a valid substring is found.
     */
    static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        // Outer loop: starting index of the substring
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            int currentLength = 0;

            // Inner loop: extend the substring
            for (int j = i; j < s.length(); j++) {
                char currentChar = s.charAt(j);

                // If character is already in the set, break the inner loop
                if (seen.contains(currentChar)) {
                    break;
                }

                // Add the character to the set and update the current length
                seen.add(currentChar);
                currentLength++;
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
     * https://www.youtube.com/watch?v=GS9TyovoU4c&t=705s
     * https://www.youtube.com/watch?v=pDsrfYcMvvY
     *
     * The sliding window approach is a technique used to solve problems involving arrays,
     * strings, or other sequential data structures efficiently. It is particularly useful
     * when dealing with problems that require finding a subarray, substring, or any other subsequence that meets certain criteria.
     *
     * Here's a step-by-step explanation of the sliding window approach:
     * Initialization: Start by initializing two pointers, usually named left and right, which define the boundaries of the window or subarray.
     * Initially, set both pointers to the beginning of the array or string.
     *
     * Expand the Window: Move the right pointer (or expand the window) to the right until you reach a point where the
     * current window/subarray no longer satisfies the problem's constraints. Meanwhile, keep track of relevant information
     * or perform operations as needed.
     *
     * Update Results (Optional): At each step, update the result if necessary based on the current window's properties
     * or the problem's requirements.
     *
     * Contract the Window: Once the window no longer satisfies the problem's constraints (e.g., a character repeats in a substring problem),
     * move the left pointer (or contract the window) to the right until the window becomes valid again. This step is optional depending on the problem.
     *
     * Repeat: Repeat steps 2-4 until the right pointer reaches the end of the array or string.
     *
     * The sliding window approach allows you to efficiently explore all possible subarrays or substrings by iteratively adjusting the window boundaries.
     * By only moving one pointer at a time, the approach achieves a linear time complexity in many cases, making it very efficient.
     *
     * This approach is commonly used in problems that involve finding the maximum or minimum sum subarray,
     * the longest substring without repeating characters, or any other problem where you need to find an optimal substructure within a larger sequence.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringWithSlidingWindow(String s) {

        // Initialize the maximum length of the substring
        int maxLength = 0;

        // Initialize the left pointer of the window
        int left = 0;

        // Map to store the index of the last occurrence of each character
        Map<Character, Integer> visitedChars = new HashMap<>();

        // Iterate through the string using the right pointer
        for (int right = 0; right < s.length(); right++) {
            // Get the current character
            char currentChar = s.charAt(right);

            // If the current character has been visited before
            if (visitedChars.containsKey(currentChar)) {
                // Update the left pointer if it's less than the last occurrence of the current character
                // in other words, the current char is in the map but it does not in the window
                // we are processing [left, right], so we ignore it
                if (left < visitedChars.get(currentChar) + 1) { // 1,2,3,3,[4,5,6,2] 2 is in map but not in the current window
                    left = visitedChars.get(currentChar) + 1;
                }
            }

            // Update the index of the current character
            visitedChars.put(currentChar, right);

            // Update the maximum length of the substring
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return the maximum length of the substring
        return maxLength;
    }
}
