package io.github.techtoto.aoc2021.day07;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Advent of Code 2021 - Day 07: The Treachery of Whales
 *
 * @author Tobias Reichenbach
 */
public class Day07 extends AbstractDay {
    List<Integer> crabs = new ArrayList<>();

    public Day07(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});

        Arrays.asList(FileInteraction.readFileToList(inputFile).get(0).split(","))
                .forEach(n -> crabs.add(Integer.parseInt(n)));

        int[] fuels = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int pos = 0; pos <= Collections.max(crabs); pos++) {
            int[] currentFuels = {0, 0};
            for (Integer crab : crabs) {
                int distance = Math.abs(crab - pos);
                currentFuels[0] += distance;
                currentFuels[1] += ((distance * distance) + distance) / 2;
            }
            for (int i = 0; i <= 1; i++) {
                if (currentFuels[i] < fuels[i]) {
                    fuels[i] = currentFuels[i];
                }
            }
        }

        setSolution(0, fuels[0]);
        setSolution(1, fuels[1]);
    }

}
