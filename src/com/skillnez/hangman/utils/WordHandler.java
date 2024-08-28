package com.skillnez.hangman.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordHandler {
    private static String randomWord;


    private String wordSelector(File fileToRead) {
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
            System.out.println("Файл не найден, проверьте название файла, либо установите название своего" +
                    " файла по умолчанию 'wordDictionary.txt'");
            System.out.println("Важно!!! файл должен быть в корневом каталоге программы");
        }
        return randomWord;
    }

    protected char[] wordToChar(File fileToRead) {
        return wordSelector(fileToRead).toCharArray();
    }

    //Возможно придется переделать;
    protected String mask (File fileToRead) {
        return wordSelector(fileToRead).replaceAll("\\D", "*");
    }

}
