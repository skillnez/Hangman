import com.skillnez.hangman.utils.Game;

public class Main {
    public static void main(String[] args) {
        //вынести внутреннюю реализацию классов начала игры и обработки в отдельный пакет, чтобы не делать методы публичными
        Game.startGame();
    }
}
