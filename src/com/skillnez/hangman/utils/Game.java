package com.skillnez.hangman.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private GraphicStorage gallowsState = new GraphicStorage();
    private WordPicker wordPicker = new WordPicker();
    private Scanner inputScanner = new Scanner(System.in); // не делай final
    private List<Character> usedLetters = new ArrayList<>();
    private List<Character> guessedLetters = new ArrayList<>();
    private char[] hiddenWord;
    private char[] maskedWord;
    private Character inputCharBuffer;
    private File gameDictionary;
    private int gameFaults;

    public Game(String fileName) {
        gameDictionary = new File(fileName);
    }

    public void startGame() {
        System.out.println("Добро пожаловать игру!");
        System.out.println("Введите [да] чтобы начать или [нет] чтобы выйти...");
        while (true) {
            String userInput = InputValidator.makeInputPoint().toLowerCase();
            gameFaults = 0;
            if (userInput.equals("да")) {
                hiddenWord = wordPicker.wordSelector(gameDictionary).toCharArray();
                maskedWord = wordPicker.mask(hiddenWord);
                while (hangmanEnd(gameFaults, guessedLetters, hiddenWord)) {
                    System.out.println("Вам загадано слово: " + wordPicker.charToString(maskedWord));
                    System.out.println("Введите букву(кириллица, любой регистр): ");
                    userInput = InputValidator.makeInputPoint().toLowerCase();
                    inputCharBuffer = InputValidator.inputCheck(userInput, usedLetters);
                    usedLetters.add(inputCharBuffer);
                    System.out.println("вы ввели: " + usedLetters.getLast());
                    succeedCheck(hiddenWord, maskedWord, guessedLetters, usedLetters);
                    gameFaults = faultCheck(guessedLetters, inputCharBuffer, gameFaults);
                    System.out.println(gallowsState.getCondition(gameFaults));
                    System.out.println("Ранее введено: " + wordPicker.listToString(usedLetters));
                    System.out.println("Ваш счет: " + guessedLetters.size() + " ошибок: " + gameFaults);
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

    private void succeedCheck(char[] guessedWord, char[] maskedWord, List<Character> suceedLetters, List<Character> usedLetters) {
        for (int i = 0; i < guessedWord.length; i++) {
            if (usedLetters.getLast() == guessedWord[i]) {
                maskedWord[i] = usedLetters.getLast();
                suceedLetters.add(usedLetters.getLast());
            }
        }
    }

    private boolean hangmanEnd(int gameEndState, List<Character> succeedLetters, char[] guessedWord) {
        if (gameEndState >= Constants.MAX_FAULTS) {
            System.out.println("Вы проиграли, загаданное слово: " + Arrays.toString(guessedWord).replaceAll("[\\s,]+", ""));
            return false;
        } else if (succeedLetters.size() == guessedWord.length) {
            System.out.println("Вы отгадали слово" + Arrays.toString(guessedWord).replaceAll("[\\s,]+", ""));
            return false;
        } else {
            return true;
        }
    }

    private int faultCheck(List<Character> succeedLetters, char currentChar, int wrongLetters) {
        if (!succeedLetters.contains(currentChar)) {
            wrongLetters += 1;
        }
        return wrongLetters;
    }


}
