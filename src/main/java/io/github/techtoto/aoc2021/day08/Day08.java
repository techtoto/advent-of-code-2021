package io.github.techtoto.aoc2021.day08;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Advent of Code 2021 - Day 08: Seven Segment Search
 *
 * @author Tobias Reichenbach
 */
public class Day08 extends AbstractDay {
    List<String[]> input = new ArrayList<>();

    public Day08(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});

        FileInteraction.readFileToList(inputFile).forEach(line -> input.add(line.split(" ")));

        int uniqueNumberOfSegments = 0;

        for (String[] line : input) {
            for (int i = 11; i <= 14; i++) {
                int currentOutputValue = line[i].length();
                if (currentOutputValue == 2 || currentOutputValue == 4 || currentOutputValue == 3 || currentOutputValue == 7) { // digits 1, 4, 7 and 8
                    uniqueNumberOfSegments++;
                }
            }
        }

        setSolution(0, uniqueNumberOfSegments);
    }
}
