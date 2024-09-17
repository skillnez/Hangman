package com.skillnez.hangman.utils;

public class HangmanMessages implements Message{

    public final static String FILE_NOT_FOUND = "Файл не найден";

    public final static String MORE_THAN_1_LETTER_RESTRICTION = "Не вводите больше одной буквы за раз, повторите ввод...";

    public final static String NOTHING_ENTERED = "Вы ничего не ввели, введите 1 букву русского алфавита...";

    public final static String RESTRICTED_LETTER = "Недопустимый символ, введите 1 русскую букву...";

    public final static String NON_CYRILLIC = "Допускается только кириллица, введите букву...";

    public final static String LETTER_ALREADY_USED = "Такая буква ранее вводилась, введите другую букву...";

    public final static String WELCOME = "Добро пожаловать игру!";

    public final static String MENU = "Введите [да] чтобы начать или [нет] чтобы выйти...";

    public final static String ENTER_ANY_LETTER = "Введите букву: ";

    public final static String USER_YES = "да";

    public final static String USER_NO = "нет";

    public void printMessage (String message) {
        System.out.println(message);
    }
}
