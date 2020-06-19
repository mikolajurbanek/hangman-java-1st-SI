import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FileManager {


    private String randomLine;
    private String bestScoreFile = "src/resources/scores.txt";
    private String fileName = "src/resources/countries_and_capitals.txt";



    private void randomLine(int randNum) throws FileNotFoundException {
        File temp = new File(fileName);
        Scanner file = new Scanner(temp);
        int line = 0;
        while (file.hasNext()) {
            if (line == randNum) {
                randomLine = file.nextLine();
            }
            line++;
            file.nextLine();
        }
    }


    private int fileSize() throws FileNotFoundException {
        int fileLineCounter = 0;
        File temp = new File(fileName);
        Scanner file = new Scanner(temp);
        while (file.hasNext()) {
            file.nextLine();
            fileLineCounter++;
        }
            return fileLineCounter;
    }


    private int randomNumber(int border){
        Random random = new Random();
        return random.nextInt(border);
    }


    public String getRandomLine() throws FileNotFoundException {
        int randomNumber = randomNumber(fileSize());
        randomLine(randomNumber);
        return randomLine;
    }


    public String getCapital(String randomLine) {
        return randomLine.split("\\|")[1].trim();
    }


    public String getCountry(String randomLine) {
        return randomLine.split("\\|")[0].trim();
    }


    public void addToFile(String playerName, int playTime){
        try {
            FileWriter myWriter = new FileWriter(bestScoreFile, true);
            myWriter.write(playerName + " | in " + playTime + " seconds" + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void scoreToString() throws FileNotFoundException {
        System.out.println("SCORES: ");
        File temp = new File(bestScoreFile);
        Scanner file = new Scanner(temp);
        while (file.hasNext()) {
            System.out.println(file.nextLine());
        }
    }
}
