package io.github.techtoto.aoc2021.day03;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Advent of Code 2021 - Day 03: Binary Diagnostic
 *
 * @author Tobias Reichenbach
 */
public class Day03 extends AbstractDay {

    public Day03(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});
        List<String> input = FileInteraction.readFileToList(inputFile);

        binaryDiagnostic1(input);
        binaryDiagnostic2(input);
    }

    private void binaryDiagnostic1(List<String> input) {
        StringBuilder gammaRateStringBuilder = new StringBuilder();
        StringBuilder epsilonRateStringBuilder = new StringBuilder();

        for (int i = 0; i < input.get(0).length(); i++) {
            long oneOccurrences = countOccurrencesPerCol(input, i, '1');
            int colLength = input.size();

            if (oneOccurrences > colLength / 2.0) {
                gammaRateStringBuilder.append("1");
                epsilonRateStringBuilder.append("0");
            } else {
                gammaRateStringBuilder.append("0");
                epsilonRateStringBuilder.append("1");
            }
        }

        int gammaRate = Integer.valueOf(gammaRateStringBuilder.toString(), 2);
        int epsilonRate = Integer.valueOf(epsilonRateStringBuilder.toString(), 2);

        setSolution(0, gammaRate * epsilonRate);
        appendOutput(0, "gamma rate = " + gammaRate + "; epsilon rate = " + epsilonRate);
    }

    private void binaryDiagnostic2(List<String> input) {
        int oxygenRating = calcRating((ArrayList<String>) input, "oxygen");
        int co2Rating = calcRating((ArrayList<String>) input, "co2");

        setSolution(1, oxygenRating * co2Rating);
        appendOutput(1, "oxygen: " + oxygenRating + "; co2: " + co2Rating);
    }

    private int calcRating(ArrayList<String> input, String type) {
        ArrayList<String> tempInput = new ArrayList<>(input);
        int currentBitPos = 0;

        while (tempInput.size() > 1) {
            long oneOccurrences = countOccurrencesPerCol(tempInput, currentBitPos, '1');
            int finalCurrentBitPos = currentBitPos;

            if ((oneOccurrences >= tempInput.size() / 2.0 && type.equals("oxygen")) ||
                    (oneOccurrences < tempInput.size() / 2.0 && type.equals("co2"))) {
                tempInput.removeIf(n -> (n.charAt(finalCurrentBitPos) == '0'));
            } else if ((oneOccurrences < tempInput.size() / 2.0 && type.equals("oxygen")) ||
                    (oneOccurrences >= tempInput.size() / 2.0 && type.equals("co2"))) {
                tempInput.removeIf(n -> (n.charAt(finalCurrentBitPos) == '1'));
            }

            currentBitPos++;
        }

        return Integer.valueOf(tempInput.get(0), 2);
    }

    private long countOccurrencesPerCol(List<String> listInRows, int colNr, char c) {
        StringBuilder col = new StringBuilder();

        listInRows.forEach(row -> col.append(row.charAt(colNr)));

        return col.chars().filter(ch -> ch == c).count();
    }
}
