package io.github.techtoto.aoc2021.day05;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advent of Code 2021 - Day 05: Hydrothermal Venture
 *
 * @author Tobias Reichenbach
 */
public class Day05 extends AbstractDay {
    Map<Point, Point> lines = new HashMap<>();
    List<HashMap<Point, Integer>> diagrams = new ArrayList<>();

    public Day05(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});
        diagrams.add(new HashMap<>());
        diagrams.add(new HashMap<>());
        List<String> input = FileInteraction.readFileToList(inputFile);
        Pattern pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

        for (String line : input) {
            Matcher currentMatch = pattern.matcher(line);
            boolean matchFound = currentMatch.matches();
            if (matchFound) {
                lines.put(new Point(Integer.parseInt(currentMatch.group(1)), Integer.parseInt(currentMatch.group(2))),
                        new Point(Integer.parseInt(currentMatch.group(3)), Integer.parseInt(currentMatch.group(4))));
            }
        }

        for (Point coordinate1 : lines.keySet()) {
            Point coordinate2 = lines.get(coordinate1);
            Point currentCoordinates;
            int x1 = coordinate1.x;
            int y1 = coordinate1.y;
            int x2 = coordinate2.x;
            int y2 = coordinate2.y;

            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    currentCoordinates = new Point(x1, y);
                    updateDiagram(currentCoordinates, 0);
                    updateDiagram(currentCoordinates, 1);
                }
            } else if (y1 == y2) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    currentCoordinates = new Point(x, y1);
                    updateDiagram(currentCoordinates, 0);
                    updateDiagram(currentCoordinates, 1);
                }
            } else {
                for (int n = 0; n <= Math.abs(x1 - x2); n++) {
                    int x = Math.min(x1, x2) + n;
                    if ((x1 > x2 && y1 > y2) || (x1 < x2 && y1 < y2)) {
                        currentCoordinates = new Point(x, Math.min(y1, y2) + n);
                    } else {
                        currentCoordinates = new Point(x, Math.max(y1, y2) - n);
                    }
                    updateDiagram(currentCoordinates, 1);
                }
            }
        }

        int[] twoOrMoreOverlaps = {0, 0};

        for (int i = 0; i < 2; i++) {
            for (Integer overlaps : diagrams.get(i).values()) {
                if (overlaps >= 2) {
                    twoOrMoreOverlaps[i]++;
                }
            }
            setSolution(i, twoOrMoreOverlaps[i]);
        }
    }

    private void updateDiagram (Point currentCoordinates, int diagram) {
        if (!diagrams.get(diagram).containsKey(currentCoordinates)) {
            diagrams.get(diagram).put(currentCoordinates, 0);
        }
        diagrams.get(diagram).put(currentCoordinates, diagrams.get(diagram).get(currentCoordinates) + 1);
    }
}
