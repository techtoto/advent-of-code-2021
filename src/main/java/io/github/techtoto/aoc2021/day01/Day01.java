package io.github.techtoto.aoc2021.day01;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;

/**
 * Advent of Code 2021 - Day 01: Sonar Sweep
 * @author Tobias Reichenbach
 * @version 2021-12-01
 */
public class Day01 extends AbstractDay {
    ArrayList<Integer> input = new ArrayList<>();

    public Day01(String inputFile) {
        super();
        for (String line : FileInteraction.readFileToArrayList(inputFile))
            input.add(Integer.parseInt(line));
        sonarSweep(input, 0);
        sonarSweep(sumThree(input) ,1);
    }

    public ArrayList<Integer> sumThree(ArrayList<Integer> input) {
        ArrayList<Integer> newInput = new ArrayList<>();
        for (int i = 1; i < input.size() - 1; i++)
            newInput.add(input.get(i - 1) + input.get(i) + input.get(i + 1));
        return newInput;
    }

    public void sonarSweep(ArrayList<Integer> input, int questNumber) {
        int previousDepth = 0;
        int depthIncreases = 0;
        for (int currentDepth : input) {
            outputs[questNumber].append(currentDepth);
            if (previousDepth == 0)
                outputs[questNumber].append(" (N/A - no previous measurement)\n");
            else if (currentDepth > previousDepth) {
                outputs[questNumber].append(" (increased)\n");
                depthIncreases++;
            }
            else
                outputs[questNumber].append(" (decreased)\n");
            previousDepth = currentDepth;
        }
        solutions[questNumber] = depthIncreases;
    }

}
