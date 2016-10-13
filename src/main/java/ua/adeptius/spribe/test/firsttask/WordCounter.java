package ua.adeptius.spribe.test.firsttask;



import java.util.HashMap;
import java.util.NoSuchElementException;

public class WordCounter {

    private static HashMap<String, Long> countOfWords = new HashMap<>();

    public static synchronized void addNewWord(String word) throws IllegalArgumentException {
        if (word == null || word.equals(""))
            throw new IllegalArgumentException("Word is empty or null!");

        String lowerWord = word.toLowerCase();

        if (countOfWords.containsKey(lowerWord)) {
            long count = countOfWords.get(lowerWord);
            countOfWords.put(lowerWord, count + 1);
        } else {
            countOfWords.put(lowerWord, 1L);
        }
    }

    public static long getCountOfWord(String word) throws NoSuchElementException {
        if (!countOfWords.containsKey(word.toLowerCase()))
            throw new NoSuchElementException("There is no such word!");

        return countOfWords.get(word.toLowerCase());
    }
}
