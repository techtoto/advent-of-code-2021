package io.github.techtoto.aoc2021.day02;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.List;

/**
 * Advent of Code 2021 - Day 02: Dive!
 * @author Tobias Reichenbach
 */
public class Day02 extends AbstractDay {
    List<String> input;

    public Day02(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});
        input = FileInteraction.readFileToList(inputFile);
        dive(input);
    }

    private void dive(List<String> input) {
        int x = 0;
        int y = 0;
        int newY = 0;
        int aim = 0;
        for (String line : input) {
            String[] currentLine = line.split(" ");
            int currentValue = Integer.parseInt(currentLine[1]);
            switch (currentLine[0]) {
                case "forward" -> {
                    x += currentValue;
                    newY += (aim * currentValue);
                }
                case "up" -> {
                    y -= currentValue;
                    aim -= currentValue;
                }
                case "down" -> {
                    y += currentValue;
                    aim += currentValue;
                }
            }
        }

        appendOutput(0, "horizontal position (x) = " + x + "; depth (y) = " + y);
        setSolution(0, x * y);
        appendOutput(1, "horizontal position (x) = " + x + "; new depth (newY) = " + newY);
        setSolution(1, x * newY);
    }
}
