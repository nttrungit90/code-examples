package leetcode.misc;

public class MaxConsecutiveRepeatingChar {

    /**
     * Initialize variables to track the maximum streak (maxCount), the current streak (currentCount),
     * and the character associated with the maximum streak (maxChar).
     * Traverse the string and compare each character with the previous one.
     * Update maxCount and maxChar when a new maximum streak is found.
     * Return the result.
     *
     * @param s
     * @return
     */
    public static Pair<Character, Integer> findMaxConsecutiveRepeatingChar(String s) {
        if (s == null || s.isEmpty()) {
            return new Pair<>('\0', 0); // Return null character and 0 count for empty strings
        }

        char maxChar = s.charAt(0); // Character with max streak
        int maxCount = 1;           // Maximum streak length

        char currentChar = s.charAt(0); // Current character
        int currentCount = 1;          // Current streak length

        // Iterate through the string starting from the second character
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == currentChar) {
                // Increment the streak for the current character
                currentCount++;
            } else {
                // Reset for a new character
                currentChar = s.charAt(i);
                currentCount = 1;
            }

            // Update max streak if current streak exceeds it
            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxChar = currentChar;
            }
        }

        return new Pair<>(maxChar, maxCount);
    }
}
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
