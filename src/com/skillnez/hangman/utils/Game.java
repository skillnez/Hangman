package com.skillnez.hangman.utils;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GraphicStorage gallowsState = new GraphicStorage();
    private final FileWordParser fileWordParser = new FileWordParser("wordDictionry.txt");
    private final HangmanMessages gameMessages = new HangmanMessages();
    private List<Character> usedLetters = new ArrayList<>();
    private List<Character> guessedLetters = new ArrayList<>();
    private char[] hiddenWord = fileWordParser.parseRandomWord().toCharArray();
    private char[] maskedWord = InputHandler.mask(hiddenWord);
    private char inputCharBuffer;
    private int gameFaults;

    public void start() {
        gameMessages.printMessage(HangmanMessages.WELCOME);
        gameMessages.printMessage(HangmanMessages.MENU);
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            usedLetters.clear();
            guessedLetters.clear();
            String userInput = InputHandler.makeInputPoint().toLowerCase();
            gameFaults = 0;
            int turn = 1;
            if (userInput.equals(HangmanMessages.USER_YES)) {
                System.out.println("===============| " + "Ход: " + turn + " |===============");
                System.out.println(gallowsState.getCondition(gameFaults));
                while (isTheGameContinues()) {
                    System.out.println("Вам загадано слово: " + InputHandler.charToString(maskedWord));
                    System.out.println("Ранее введено: " + InputHandler.listToString(usedLetters));
                    collectLettersFromInput();
                    processCollectedLetters();
                    turn++;
                    System.out.println("===============| " + "Ход: " + turn + " |===============");
                    System.out.println(gallowsState.getCondition(gameFaults));
                }
            } else if (userInput.equals(HangmanMessages.USER_NO)) {
                System.out.println("До встречи!");
                break;
            } else {
                gameMessages.printMessage(HangmanMessages.MENU);
            }
        }
    }

    private void collectLettersFromInput() {
        gameMessages.printMessage(HangmanMessages.ENTER_ANY_LETTER);
        inputCharBuffer = InputHandler.input();
        while (isLetterUsed()) {
            inputCharBuffer = InputHandler.input();
        }
        usedLetters.add(inputCharBuffer);
        System.out.println("вы ввели: " + usedLetters.getLast());
    }

    private void processCollectedLetters() {
        succeedCheck();
        faultCheck();
        System.out.println("Ваш счет: " + guessedLetters.size() + " |" + " ошибок: " + gameFaults);
    }

    private void succeedCheck() {
        for (int i = 0; i < hiddenWord.length; i++) {
            if (usedLetters.getLast() == hiddenWord[i]) {
                maskedWord[i] = usedLetters.getLast();
                guessedLetters.add(usedLetters.getLast());
            }
        }
    }

    private void faultCheck() {
        if (!guessedLetters.contains(inputCharBuffer)) {
            gameFaults += 1;
        }
    }

    private boolean isLetterUsed() {
        if (usedLetters.contains(inputCharBuffer)) {
            gameMessages.printMessage(HangmanMessages.LETTER_ALREADY_USED);
            return true;
        } else {
            return false;
        }
    }

    private boolean isTheGameContinues() {
        return hangmanEndCondition();
    }

    private boolean hangmanEndCondition() {
        if (gameFaults >= Constants.MAX_FAULTS) {
            System.out.println("Вы проиграли, загаданное слово: " + InputHandler.charToString(hiddenWord) + "\n" +
                    "Нажмите Enter чтобы вернуться в меню");
            return false;
        }
        if (guessedLetters.size() == hiddenWord.length) {
            System.out.println("Вы отгадали слово" + InputHandler.charToString(hiddenWord));
            return false;
        }
        return true;
    }

}
