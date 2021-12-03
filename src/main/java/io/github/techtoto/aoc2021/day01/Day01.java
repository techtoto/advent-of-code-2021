package io.github.techtoto.aoc2021.day01;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;

/**
 * Advent of Code 2021 - Day 01: Sonar Sweep
 * @author Tobias Reichenbach
 */
public class Day01 extends AbstractDay {
    ArrayList<Integer> input = new ArrayList<>();

    public Day01(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});
        for (String line : FileInteraction.readFileToList(inputFile)) {
            input.add(Integer.parseInt(line));
        }
        sonarSweep(input, 0);
        sonarSweep(sumThree(input) ,1);
    }

    private ArrayList<Integer> sumThree(ArrayList<Integer> input) {
        ArrayList<Integer> newInput = new ArrayList<>();
        for (int i = 1; i < input.size() - 1; i++) {
            newInput.add(input.get(i - 1) + input.get(i) + input.get(i + 1));
        }
        return newInput;
    }

    private void sonarSweep(ArrayList<Integer> input, int questNumber) {
        int previousDepth = 0;
        int depthIncreases = 0;
        for (int currentDepth : input) {
            appendOutput(questNumber, Integer.toString(currentDepth));
            if (previousDepth == 0) {
                appendOutput(questNumber, " (N/A - no previous measurement)\n");
            }
            else if (currentDepth > previousDepth) {
                appendOutput(questNumber, " (increased)\n");
                depthIncreases++;
            } else {
                appendOutput(questNumber, " (decreased)\n");
            }
            previousDepth = currentDepth;
        }
        setSolution(questNumber, depthIncreases);
    }

}
