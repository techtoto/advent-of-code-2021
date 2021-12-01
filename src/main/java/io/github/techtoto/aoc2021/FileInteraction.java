package io.github.techtoto.aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInteraction {
    public static String readFileToString(String inFile) {
        StringBuilder content = new StringBuilder();
        for (String line : readFileToArrayList(inFile))
            content.append(line).append("\n");
        return content.toString();
    }

    public static ArrayList<String> readFileToArrayList(String inFile) {
        ArrayList<String> fileContent = new ArrayList<>();
        try {
            File myFile = new File("src/main/java/io/github/techtoto/aoc2021/" + inFile);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine())
                fileContent.add(myScanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
            e.printStackTrace();
        }
        return fileContent;
    }
}
