import com.skillnez.hangman.utils.Game;

public class Main {
    public static void main(String[] args) {
        //введите название нужного файла в параметры с учетом регистра.
        Game hangman = new Game();
        hangman.start();
    }
}
