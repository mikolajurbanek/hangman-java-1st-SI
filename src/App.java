import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        GameProvider gameProvider = new GameProvider();
        System.out.println("\n ~~~WELCOME TO HANGMAN GAME~~~");
        gameProvider.menu();


    }
}
