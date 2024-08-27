import com.skillnez.hangman.utils.Game;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //введите название нужного файла в конструктор класса Game с учетом регистра.
        Game hangman = new Game("wordDictionary.txt");
        hangman.startGame();
    }
}
