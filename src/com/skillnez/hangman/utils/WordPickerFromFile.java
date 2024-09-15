package com.skillnez.hangman.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordPickerFromFile {
    private HangmanMessages hangmanMessages = new HangmanMessages();
    private String randomWord;
    private String fileName;


    public WordPickerFromFile(String fileName) {
        this.fileName = fileName;
    }

    protected String wordSelector() {
        try {
            InputStream resource = WordPickerFromFile.class.getClassLoader().getResourceAsStream(fileName);
            assert resource != null;
            List<String> words = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();
            Random rand = new Random();
            randomWord = words.get(rand.nextInt(words.size()));
        } catch (Exception e) {
            hangmanMessages.printMessage(HangmanMessages.FILE_NOT_FOUND);
            System.exit(0);
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
