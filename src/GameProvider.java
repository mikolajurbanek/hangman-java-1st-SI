import java.io.FileNotFoundException;
import java.util.*;

public class GameProvider {

    private int lives = 10;
    private ArrayList<String> wrongLetters = new ArrayList<>();
    private TreeMap<Integer, String> usedLetters = new TreeMap<>();
    private Scanner scan = new Scanner(System.in);
    private boolean isRunning = true;
    private String playerName;
    private boolean notWon = true;
    private int playTime;
    private FileManager fileManager = new FileManager();


    public void menu() throws FileNotFoundException {
        System.out.println(
                "\n   MENU:" +
                "\n        (1) GAME" +
                "\n        (2) SCORES" +
                "\n        (3) ABOUT" +
                "\n        (0) EXIT");

    int opt = scan.nextInt();
    switch(opt) {
        case 1:
            game();
            break;
        case 2:
            fileManager.scoreToString();
            menu();
            break;
        case 3:
            about();
            menu();
            break;

    }

    }

    private void game() throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        wrongLetters.clear();
        usedLetters.clear();
        lives = 10;
        isRunning = true;
        notWon = true;

        String randomLine = fileManager.getRandomLine();
        String capital = fileManager.getCapital(randomLine).toUpperCase();
        String country = fileManager.getCountry(randomLine).toUpperCase();

        while (isRunning) {
            if (notWon){
            System.out.println();
            System.out.print("Lives: " + lives);
            System.out.print("    Wrong letters: " + wrongLetters.toString());
            System.out.println("    Used letters: " + usedLetters.values().toString());
            System.out.println("Provide Your guess: ");

            String guess = scan.next().toUpperCase();
            if (guess.length() > 1){
                compireWords(capital, guess);
            }
            else {
                compireLetters(capital, guess);
            }

            checkWin(capital);
            viewProgress(capital);
            checkLives(country);

            }else {
                highScore(startTime);
                playGameAgain();
                fileManager.addToFile(playerName, playTime);
                fileManager.scoreToString();
                }
        }
    }

    private void compireLetters(String guessString, String guess) {
        String charToAdd;
        String wrongCharToAdd = new String();

        int placeToAdd = 0;
        int letterPlace = 0;
        for (String c : guessString.split("")) {
            if (c.equals(guess)) {
                charToAdd = c;
                placeToAdd = letterPlace;
                usedLetters.put(placeToAdd, charToAdd);
            } else {
                wrongCharToAdd = guess;
            }

            letterPlace++;
        }

        if (!wrongLetters.contains(wrongCharToAdd) && !usedLetters.values().contains(wrongCharToAdd)) {
            wrongLetters.add(wrongCharToAdd);
            lives--;
        }
    }

    private void viewProgress(String guessString) {
        for (int i = 0; i < guessString.length(); i++) {
            if (usedLetters.keySet().contains(i)) {
                System.out.print(" " + usedLetters.get(i) + " ");
            } else {
                System.out.print(" _ ");
            }
        }
    }

    private void checkLives(String country) throws FileNotFoundException {
        System.out.println();
        if (lives <= 0){
            System.out.println("You have died, do You want to play again? (y/n)");
            playGameAgain();
        }
        if (lives <= 3 && lives>0){
            System.out.println("This is capital of " + country);
        }
    }


    private void compireWords(String capital, String guess)  {
        if(capital.equals(guess)){
            notWon = false;
        }
        else{ lives = lives - 2;}
    }


    private void checkWin(String capital) {
        if (usedLetters.size() == capital.length()){
            notWon = false;
        }
    }


    private void playGameAgain() throws FileNotFoundException {
        isRunning = false;
        System.out.println("Do you want to play again or save and leave? ([p]lay / [s]ave and leave) ");
        String choose = scan.next();
        switch (choose) {
            case "p":
                game();
                break;
            case "s":
                break;
        }
    }

    private void highScore(long startTime){
        isRunning = false;
        System.out.println("You have won! Congratulations!");
        long endTime = System.currentTimeMillis();
        playTime = (int) (endTime - startTime)/1000;
        System.out.println("Your time: " + playTime + " second");
        System.out.println("Please enter your name: ");
        playerName = scan.next();
    }

    private void about(){
        System.out.println(" This is Hangman Game done by Mikołaj Urbanek, student of Codecool, rewrited in InteliJ." +
                "\n Point of game is to guess one of the world's capitals." +
                "\n To play you need to change your source path in FileManager file. ");
    }
}