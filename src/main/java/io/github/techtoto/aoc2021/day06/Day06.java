package io.github.techtoto.aoc2021.day06;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Advent of Code 2021 - Day 06: Lanternfish
 *
 * @author Tobias Reichenbach
 */
public class Day06 extends AbstractDay {
    Map<Integer, Long> lanternfishes = new HashMap<>();

    public Day06(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});

        for (int i = 0; i <= 8; i++) {
            lanternfishes.put(i, 0L);
        }

        Arrays.asList(FileInteraction.readFileToList(inputFile).get(0).split(","))
                .forEach(n -> addLanternfish(Integer.parseInt(n), 1));

        for (int day = 1; day <= 256; day++) {
            long oldZeros = lanternfishes.get(0);

            for (int i = 0; i <= 7; i++) {
                lanternfishes.put(i, lanternfishes.get(i + 1));
            }
            addLanternfish(6, oldZeros);
            lanternfishes.put(8, oldZeros);

            if (day == 80) {
                setSolution(0, countLanternfishes());
            }
        }

        setSolution(1, countLanternfishes());
    }

    private void addLanternfish(int internalTimer, long amount) {
        lanternfishes.put(internalTimer, lanternfishes.get(internalTimer) + amount);
    }

    private long countLanternfishes() {
        return lanternfishes.values().stream().mapToLong(n -> n).sum();
    }
}
