package ua.adeptius.spribe.test.firsttask;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class WordCounter {

    private static HashMap<String, Long> countOfWords = new HashMap<>();

    public static synchronized void addNewWord(String word) {
        if (StringUtils.isEmpty(word))
            throw new IllegalArgumentException("Word is empty or null!");

        String lowerWord = word.toLowerCase();

        if (countOfWords.containsKey(lowerWord)) {
            long count = countOfWords.get(lowerWord);
            countOfWords.put(lowerWord, count + 1);
        } else {
            countOfWords.put(lowerWord, 1L);
        }
    }

    public static long getCountOfWord(String word) {
        if (!countOfWords.containsKey(word.toLowerCase()))
            throw new NoSuchElementException("There is no such word!");
        return countOfWords.get(word.toLowerCase());
    }
}