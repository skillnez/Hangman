package com.skillnez.hangman.utils;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GraphicStorage gallowsState = new GraphicStorage();
    private WordPickerFromFile wordPickerFromFile = new WordPickerFromFile("wordDictionary.txt");
    private List<Character> usedLetters = new ArrayList<>();
    private List<Character> guessedLetters = new ArrayList<>();
    private HangmanMessages gameMessages = new HangmanMessages();

    public void startGame() {
        gameMessages.printMessage(HangmanMessages.WELCOME);
        gameMessages.printMessage(HangmanMessages.MENU);
        while (true) {
            usedLetters.clear();
            guessedLetters.clear();
            String userInput = InputValidator.makeInputPoint().toLowerCase();
            int gameFaults = 0;
            int turn = 1;
            if (userInput.equals(HangmanMessages.USER_YES)) {
                char[] hiddenWord = wordPickerFromFile.wordSelector().toCharArray();
                char[] maskedWord = wordPickerFromFile.mask(hiddenWord);
                System.out.println(gallowsState.getCondition(gameFaults));
                while (hangmanEnd(gameFaults, guessedLetters, hiddenWord)) {
                    System.out.println("Вам загадано слово: " + wordPickerFromFile.charToString(maskedWord));
                    gameMessages.printMessage(HangmanMessages.ENTER_ANY_LETTER);
                    userInput = InputValidator.makeInputPoint().toLowerCase();
                    Character inputCharBuffer = InputValidator.inputCheck(userInput, usedLetters);
                    usedLetters.add(inputCharBuffer);
                    System.out.println("вы ввели: " + usedLetters.getLast());
                    System.out.println("===============| " + "Ход: " + turn + " |===============");
                    succeedCheck(hiddenWord, maskedWord, guessedLetters, usedLetters);
                    gameFaults = faultCheck(guessedLetters, inputCharBuffer, gameFaults);
                    System.out.println(gallowsState.getCondition(gameFaults));
                    System.out.println("Ранее введено: " + wordPickerFromFile.listToString(usedLetters));
                    System.out.println("Ваш счет: " + guessedLetters.size() + " ошибок: " + gameFaults);
                    turn++;
                }
            } else if (userInput.equals(HangmanMessages.USER_NO)) {
                System.out.println("До встречи!");
                break;
            } else {
                gameMessages.printMessage(HangmanMessages.MENU);
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
            System.out.println("Вы проиграли, загаданное слово: " + wordPickerFromFile.charToString(guessedWord) + "\n" +
                    "Нажмите Enter чтобы вернуться в меню");
            return false;
        } else if (succeedLetters.size() == guessedWord.length) {
            System.out.println("Вы отгадали слово" + wordPickerFromFile.charToString(guessedWord));
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
