package com.skillnez.hangman.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FileWordParser {
    private String randomWord;
    private final String fileName;


    public FileWordParser(String fileName) {
        this.fileName = fileName;
    }

    protected String parseRandomWord() {
        try {
            InputStream resource = FileWordParser.class.getClassLoader().getResourceAsStream(fileName);
            assert resource != null;
            List<String> words = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();
            Random rand = new Random();
            randomWord = words.get(rand.nextInt(words.size()));
            resource.close();
        } catch (Exception e) {
            System.out.println("Файл не найден!");
            System.exit(0);
        }
        return randomWord;
    }
}
