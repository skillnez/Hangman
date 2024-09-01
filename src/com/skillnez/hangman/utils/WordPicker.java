package com.skillnez.hangman.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordPicker {
    private HangmanMessages hM = new HangmanMessages();
    private static String randomWord;


    protected String wordSelector(File fileToRead) {
        List<String> words = new ArrayList<>();
        try {
            Scanner wordScanner = new Scanner(fileToRead);
            Random rand = new Random();
            while (wordScanner.hasNextLine()) {
                words.add(wordScanner.nextLine());
            }
            randomWord = words.get(rand.nextInt(words.size()));
            wordScanner.close();
        } catch (FileNotFoundException e) {
            hM.printMessage(HangmanMessages.FILE_NOT_FOUND);
        }
        return randomWord;
    }

    protected char[] mask (char[] charArray) {
        char[] mask = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            mask[i] = Constants.MASK_CHAR;
        }
        return mask;
    }

    protected String charToString(char[] charArray) {
        return Arrays.toString(charArray).replaceAll("[\\s,]+", "");
    }

    protected String listToString(List<Character> list) {
        return list.toString().replaceAll("[\\s]+", "");
    }

}
