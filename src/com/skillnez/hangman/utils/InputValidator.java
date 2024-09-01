package com.skillnez.hangman.utils;

import java.util.List;
import java.util.Scanner;

public class InputValidator {

    protected static Character inputCheck(String input, List<Character> usedLetters) {
        HangmanMessages validatorMessages = new HangmanMessages();
        while (true) {
            if (input.length() > Constants.MAX_INPUT_LENGTH) {
                validatorMessages.printMessage(HangmanMessages.MORE_THAN_1_LETTER_RESTRICTION);
                input = makeInputPoint();
            } else if (input.isEmpty()) {
                validatorMessages.printMessage(HangmanMessages.NOTHING_ENTERED);
                input = makeInputPoint();
            } else if (!Character.isLetter(input.charAt(0))) {
                validatorMessages.printMessage(HangmanMessages.RESTRICTED_LETTER);
                input = makeInputPoint();
            } else if (Character.UnicodeBlock.of(input.charAt(0)) != Character.UnicodeBlock.CYRILLIC) {
                validatorMessages.printMessage(HangmanMessages.NON_CYRILLIC);
                input = makeInputPoint();
            } else if (usedLetters.contains(input.charAt(0))) {
                validatorMessages.printMessage(HangmanMessages.LETTER_ALREADY_USED);
                input = makeInputPoint();
            } else if (input.charAt(0) == '\0' || Character.isWhitespace(input.charAt(0))) {
                validatorMessages.printMessage(HangmanMessages.NOTHING_ENTERED);
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
