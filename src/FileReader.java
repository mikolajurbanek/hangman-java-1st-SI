import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class FileReader {





//      while(file.hasNextLine()){
//            String filecontent = filecontent.concat(file.nextLine() + "\n");
//
//        }

    public void readFile() throws FileNotFoundException {


        File temp = new File("/home/mikolaj/IdeaProjects/hangman-java-1st-SI/out/countries_and_capitals.txt");
        Scanner file = new Scanner(temp);
        System.out.println(file.nextLine());


    }
}
