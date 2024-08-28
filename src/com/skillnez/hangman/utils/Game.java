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
    private String hiddenWord;
    private char inputCharBuffer;
    private String input;
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
                while (true) { //пофиксь зацикленность после прописывания условий игры и вынеси начало игрового цикла в отдельный метод
                    hiddenWord = Arrays.toString(wordHandler.wordToChar(gameDictionary));
                    System.out.println("Вам загадано слово: " + wordHandler.mask(gameDictionary));
                    System.out.println("Введите букву(кириллица, любой регистр): ");
                    //Реализовать проверку повторного ввода и потом вынести из этого метода в класс валидатор
                    userInput = InputValidator.makeInputPoint().toLowerCase();
                    switch (InputValidator.getValidatorState()) {
                        case 0:
                            inputCharBuffer = InputValidator.isEmpty(userInput).charAt(0);
                            break;
                        case 1:
                            inputCharBuffer = InputValidator.letterQuantityCheck(userInput).charAt(0);
                            break;
                    }
                    usedLetters.add(inputCharBuffer);
                    System.out.println("вы ввели: " + usedLetters.getLast());
                    System.out.println("Ранее введено: " + usedLetters.toString());
                }
            }
            else if (userInput.equals("нет")) {
                System.out.println("До встречи!");
                break;
            } else {
                System.out.println("Команда не распознана");
                System.out.println("Введите [да] чтобы начать или [нет] чтобы выйти...");
            }
        } //while (true);
    }
}
