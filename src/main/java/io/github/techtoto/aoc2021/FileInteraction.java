package io.github.techtoto.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileInteraction {
    public static ArrayList<String> readFileToArrayList(String inFile) {
        ArrayList<String> lines;
        try {
            lines = (ArrayList<String>) Files.readAllLines(Paths.get("src/main/java/io/github/techtoto/aoc2021/" + inFile));
        } catch (IOException e) {
            lines = new ArrayList<>();
            System.out.println("File could not be found!");
            e.printStackTrace();
        }
        return lines;
    }
}
