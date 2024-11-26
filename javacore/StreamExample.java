package javacore;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 10, 8, 4, 18, 9);
        List<String> words = Arrays.asList("babana", "apple", "avocado", "avocado", "coconut", "starfruit", "tamarind");
        System.out.println(findFirstAndSecondLargest(numbers));
    }

    public static double average(List<Integer> numbers) {
        return numbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    public static List<String> toUpperCase(List<String> words) {
        return words.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static Long sumOdd(List<Integer> numbers) {
        return numbers.stream().filter(num -> num % 2 != 0).mapToLong(Integer::longValue).sum();
    }

    public static List<String> removeDuplicate(List<String> words) {
        return words.stream().distinct().toList();
    }

    public static List<String> sortList(List<String> words) {
        return words.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public static Optional<Integer> findMaxInt(List<Integer> numbers) {
        return numbers.stream().max(Comparator.comparingInt(Integer::intValue));
    }

    public static Optional<String> getLongestString(List<String> words) {
        Comparator<String> maxLengthComparator = new Comparator<String>() {
            public int compare(String word1, String word2) {
                return Integer.compare(word1.length(), word2.length());
            }
        };
        // or maxLengthComparator = (word1, word2) -> Integer.compare(word1.length(), word2.length());
        // or maxLengthComparator = Comparator.comparingInt(String::length);
        return words.stream().max(maxLengthComparator);
    }

    public static IntSummaryStatistics findMaxAndMin(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
    }

    public static List<Integer> findFirstAndSecondLargest(List<Integer> numbers) {
        return numbers.stream().distinct().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
    }

    public static Map<String, Integer> toNameLengthMap(List<String> words) {
        return words.stream().collect(Collectors.toMap(Function.identity(), String::length));
    }

    public static List<Integer> findNumberHasOneOccurrence(List<Integer> numbers) {
        Map<Integer, Long> countMap = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static String joinStrings(List<String> words) {
        return String.join(", ", words);
    }

}
