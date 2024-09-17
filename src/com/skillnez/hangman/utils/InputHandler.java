package com.skillnez.hangman.utils;

import java.util.Scanner;

public class InputHandler {

    protected static Character input() {
        HangmanMessages validatorMessages = new HangmanMessages();
        while (true) {
            String input = makeInputPoint();
            if (input.length() > Constants.MAX_INPUT_LENGTH) {
                validatorMessages.printMessage(HangmanMessages.MORE_THAN_1_LETTER_RESTRICTION);
                continue;
            }
            if (input.isEmpty()) {
                validatorMessages.printMessage(HangmanMessages.NOTHING_ENTERED);
                continue;
            }
            if (!Character.isLetter(input.charAt(0))) {
                validatorMessages.printMessage(HangmanMessages.RESTRICTED_LETTER);
                continue;
            }
            if (Character.UnicodeBlock.of(input.charAt(0)) != Character.UnicodeBlock.CYRILLIC) {
                validatorMessages.printMessage(HangmanMessages.NON_CYRILLIC);
                continue;
            }
            if (input.charAt(0) == '\0' || Character.isWhitespace(input.charAt(0))) {
                validatorMessages.printMessage(HangmanMessages.NOTHING_ENTERED);
                continue;
            }
                return input.charAt(0);
        }
    }

    protected static String makeInputPoint() {
        return new Scanner(System.in).nextLine().toLowerCase();
    }
}
