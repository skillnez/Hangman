package com.skillnez.hangman.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final int MAX_USER_INPUT_LENGTH = 1;

    private WordHandler wordHandler = new WordHandler();
    private Scanner inputScanner = new Scanner(System.in); // не делай final
    private List<Character> UsedLetters = new ArrayList<>();
    private String hiddenWord;
    private char inputCharBuffer;
    private String inputSting;
    private File gameDictionary;

    public Game (String fileName) {
        gameDictionary = new File(fileName);
    }

    public void startGame() {
        System.out.println("Добро пожаловать игру!");
        do { //пофиксь зацикленность после прописывания условий игры
            System.out.println("Введите [да] чтобы начать или [нет] чтобы выйти...");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toLowerCase();
            if (userInput.equals("да")) {
                hiddenWord = Arrays.toString(wordHandler.wordToChar(gameDictionary));
                System.out.println("Вам загадано слово: " + wordHandler.mask(gameDictionary));
                System.out.println("Введите букву(кириллица, любой регистр): ");

                //Реализовать проверку повторного ввода и потом вынести из этого метода в класс валидатор
                inputSting = inputScanner.nextLine().toLowerCase();
                if (inputSting.length() > MAX_USER_INPUT_LENGTH | inputSting.isEmpty()) {
                    System.out.println("Не вводите больше или меньше одной буквы за раз, повторите ввод...");
                    inputSting = inputScanner.nextLine().toLowerCase();
                }
                    inputCharBuffer = inputSting.charAt(0);
                    if (UsedLetters.contains(inputCharBuffer)) {
                        System.out.println("Такая буква уже есть, введите другую...");
                        inputCharBuffer = inputScanner.nextLine().charAt(0);
                    } else {
                        UsedLetters.add(inputCharBuffer);
                        System.out.println("вы ввели: " + UsedLetters.getLast());
                        System.out.println("Ранее введено: " + UsedLetters.toString());
                    }
                }
////                break;
//            if (userInput.equals("нет")) {
//                System.out.println("До встречи!");
//                break;
//            } else {
//                System.out.println("Выбран неверный вариант!");
//            }
////            не добавляй сюда закрытие сканера, иначе получишь ошибку
        } while (true);
    }

}
