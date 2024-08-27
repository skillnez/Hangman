package com.skillnez.hangman.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordHandler {
    private static String randomWord;

    protected String wordSelector() {
        File gameWords = new File("GameWordTuple.txt");
        List<String> words = new ArrayList<>();
        try {
            Scanner wordScanner = new Scanner(gameWords);
            Random rand = new Random();
            while (wordScanner.hasNextLine()) {
                words.add(wordScanner.nextLine());
            }
            randomWord = words.get(rand.nextInt(words.size()));
            wordScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return randomWord;
    }

    protected char[] wordToChar() {
        return wordSelector().toCharArray();
    }

    protected String mask () {
        return wordSelector().replaceAll("\\D", "*");
    }

}
