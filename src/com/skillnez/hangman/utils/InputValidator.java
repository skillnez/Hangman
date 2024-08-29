package com.skillnez.hangman.utils;

import java.util.List;
import java.util.Scanner;

public class InputValidator {

    protected static Character inputCheck(String input, List<Character> usedLetters) {
        while (true) {
            if (input.length() > Constants.MAX_INPUT_LENGTH) {
                System.out.println("Не вводите больше одной буквы за раз, повторите ввод...");
                input = makeInputPoint();
            } else if (input.isEmpty()) {
                System.out.println("Вы ничего не ввели, введите 1 букву русского алфавита...");
                input = makeInputPoint();
            } else if (!Character.isLetter(input.charAt(0))) {
                System.out.println("Недопустимый символ, введите 1 русскую букву...");
                input = makeInputPoint();
            } else if (Character.UnicodeBlock.of(input.charAt(0)) != Character.UnicodeBlock.CYRILLIC) {
                System.out.println("Допускается только кириллица, введите букву...");
                input = makeInputPoint();
            } else if (usedLetters.contains(input.charAt(0))) {
                System.out.println("Такая буква ранее вводилась, введите другую букву...");
                input = makeInputPoint();
            } else if (input.charAt(0) == '\0' || Character.isWhitespace(input.charAt(0))) {
                System.out.println("Вы ничего не ввели, введите букву...");
                input = makeInputPoint();
            } else {
                return input.charAt(0);
            }
        }
    }

    protected static void isLetterUsed(List<Character> letters, char inputChar) {
        while (letters.contains(inputChar)) {
            System.out.println("Такая буква уже есть, введите другую...");
            inputChar = getFirstLetter(makeInputPoint());
        }
    }

    private static char getFirstLetter(String input) {
        return input.charAt(0);
    }

    protected static String makeInputPoint() {
        return new Scanner(System.in).nextLine().toLowerCase();
    }
}
