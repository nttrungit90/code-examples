package javacore;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static void convertString() {
        String str = "keep on going";
        char[] chars = str.toCharArray();
        Character[] characters = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        List<Character> characterList = str.chars().mapToObj(c -> (char) c).toList();

        String newString1 = new String(chars);
        String newString2 = String.valueOf(chars);
        String newString3 = Stream.of(characters).map(String::valueOf).collect(Collectors.joining());
        String newString4 = Arrays.stream(characters).map(String::valueOf).collect(Collectors.joining());
        String newString5 = characterList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void convertIntArray() {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);

        List<Integer> integerList = Arrays.stream(intArray).boxed().toList();
        List<Integer> integerList2 = Arrays.asList(integerArray);
        List<Integer> integerList3 = Arrays.stream(integerArray).toList();
        List<Integer> integerList4 = Stream.of(integerArray).toList();

        Integer[] newIntArray = integerList.toArray(new Integer[0]);
    }

}
