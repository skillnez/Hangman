package com.skillnez.hangman.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    private WordHandler wordHandler = new WordHandler();
    private Scanner inputScanner = new Scanner(System.in); // не делай final
    private List<Character> usedLetters = new ArrayList<>();
    private List<Character> guessedLetters = new ArrayList<>();
    private char[] hiddenWord;
    private char[] maskedWord;
    private Character inputCharBuffer;
    private File gameDictionary;

    public Game(String fileName) {
        gameDictionary = new File(fileName);
    }

    public void startGame() {
        System.out.println("Добро пожаловать игру!");
        System.out.println("Введите [да] чтобы начать или [нет] чтобы выйти...");
        while (true) {
            String userInput = InputValidator.makeInputPoint().toLowerCase();
            if (userInput.equals("да")) {
                hiddenWord = wordHandler.wordSelector(gameDictionary).toCharArray();
                maskedWord = wordHandler.mask(hiddenWord);
                while (true) { //пофиксь зацикленность после прописывания условий игры и вынеси начало игрового цикла в отдельный метод
                    System.out.println("Вам загадано слово: " + Arrays.toString(maskedWord).replaceAll("[\\s,]+", "")); //Arrays.toString(maskedWord));
                    System.out.println("Введите букву(кириллица, любой регистр): ");
                    userInput = InputValidator.makeInputPoint().toLowerCase();
                    inputCharBuffer = InputValidator.inputCheck(userInput, usedLetters);
                    usedLetters.add(inputCharBuffer);
                    System.out.println("вы ввели: " + usedLetters.getLast());
                    for (int i = 0; i < hiddenWord.length; i++) {
                        if (usedLetters.getLast() == hiddenWord[i]) {
                            maskedWord[i] = usedLetters.getLast();
                            guessedLetters.add(usedLetters.getLast());
                        }
                    }
                    System.out.println("Ранее введено: " + usedLetters.toString().replaceAll("[\\s]+", ""));
                    System.out.println("Ваш счет: " + guessedLetters.size());
                    if (guessedLetters.size() == hiddenWord.length) {
                        System.out.println("Вы отгадали слово" + Arrays.toString(hiddenWord).replaceAll("[\\s,]+", ""));
                        break;
                    }
                }
            } else if (userInput.equals("нет")) {
                System.out.println("До встречи!");
                break;
            } else {
                System.out.println("Команда не распознана");
                System.out.println("Введите [да] чтобы начать или [нет] чтобы выйти...");
            }
        }
    }
}
