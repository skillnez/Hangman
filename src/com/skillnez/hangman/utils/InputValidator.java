package com.skillnez.hangman.utils;

import java.util.List;
import java.util.Scanner;

public class InputValidator {
private static int VALIDATOR_STATE = 0;

    public static int getValidatorState() {
        return VALIDATOR_STATE;
    }

    protected static String letterQuantityCheck(String input) {
        while (input.length() > Constants.MAX_USER_INPUT_LENGTH) {
            System.out.println("Не вводите больше или меньше одной буквы за раз, повторите ввод...");
            input = makeInputPoint();
            VALIDATOR_STATE = 0;
        }
        return input;
    }

    protected static String isEmpty(String input) {
        while (input.isEmpty()) {
            System.out.println("Вы ничего не ввели, введите букву...");
            input = makeInputPoint();
            VALIDATOR_STATE = 1;
        }
        return input;
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
