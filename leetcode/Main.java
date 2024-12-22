package leetcode;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        String longestString = words.stream().reduce("", (currentLongest, currentString) -> {
            return currentString.length() > currentLongest.length()? currentString:currentLongest;
        });
        System.out.println(longestString);

    }


}
