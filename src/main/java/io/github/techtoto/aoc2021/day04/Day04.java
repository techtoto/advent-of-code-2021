package io.github.techtoto.aoc2021.day04;

import io.github.techtoto.aoc2021.AbstractDay;
import io.github.techtoto.aoc2021.FileInteraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Advent of Code 2021 - Day 04: Giant Squid
 *
 * @author Tobias Reichenbach
 */
public class Day04 extends AbstractDay {

    public Day04(String inputFile, boolean printOutput1, boolean printOutput2) {
        super(new boolean[]{printOutput1, printOutput2});
        List<String> input = FileInteraction.readFileToList(inputFile);

        ArrayList<ArrayList<String[]>> results = playBingo(input);

        int unmarkedNumbers1 = sumBoard(results.get(0)) - sumBoard(results.get(1));
        int winningNumber1 = Integer.parseInt(results.get(2).get(0)[0]);
        int unmarkedNumbers2 = sumBoard(results.get(3)) - sumBoard(results.get(4));
        int winningNumber2 = Integer.parseInt(results.get(5).get(0)[0]);

        setSolution(0, unmarkedNumbers1 * winningNumber1);
        setSolution(1, unmarkedNumbers2 * winningNumber2);

        appendOutput(0, "sum of all unmarked numbers: " + unmarkedNumbers1 + "; winning number: " + winningNumber1);
        appendOutput(1, "sum of all unmarked numbers: " + unmarkedNumbers2 + "; winning number: " + winningNumber2);
    }

    private ArrayList<ArrayList<String[]>> playBingo(List<String> input) {
        ArrayList<ArrayList<String[]>> bingoBoards = new ArrayList<>();
        ArrayList<ArrayList<String[]>> bingoBoardsChecked = new ArrayList<>();

        String[] numbers = input.get(0).split(",");
        input.remove(0);

        int bingoCount = -1;
        for (String line : input) {
            if (line.equals("")) {
                bingoBoards.add(new ArrayList<>());
                bingoBoardsChecked.add(new ArrayList<>());
                bingoCount++;
                continue;
            }
            if (line.startsWith(" ")) {
                line = line.substring(1);
            }
            bingoBoards.get(bingoCount).add(line.replace("  ", " ").split(" "));
            bingoBoardsChecked.get(bingoCount).add(new String[5]);
        }

        ArrayList<ArrayList<String[]>> finalBoards = new ArrayList<>();
        boolean haveWinningBoard = false;
        out:
            // iterate over every given number
            for (String number : numbers) {
                // iterate over all bingo boards
                for (int currentBoard = 0; currentBoard < bingoBoards.size(); currentBoard++) {
                    // iterate over all rows of a bingo board
                    for (int currentRow = 0; currentRow < bingoBoards.get(currentBoard).size(); currentRow++) {
                        // iterate over all single numbers in a bingo board line
                        for (int currentNumber = 0; currentNumber < bingoBoards.get(currentBoard).get(currentRow).length; currentNumber++) {
                            // if the current given number is equal to the current number on the current bingo board
                            if (number.equals(bingoBoards.get(currentBoard).get(currentRow)[currentNumber])) {
                                bingoBoardsChecked.get(currentBoard).get(currentRow)[currentNumber] = bingoBoards.get(currentBoard).get(currentRow)[currentNumber];

                                int winningBoards = 0;
                                for (ArrayList<String[]> currentFilledBoard : bingoBoardsChecked) {
                                    for (int i = 0; i < currentFilledBoard.size(); i++) { // assumption that it's a quadratic board
                                        String[] currentLine = currentFilledBoard.get(i);
                                        boolean isWinningBoard = (
                                                currentLine[0] != null && currentLine[1] != null && // check for rows
                                                        currentLine[2] != null && currentLine[3] != null &&
                                                        currentLine[4] != null) ||
                                                (currentFilledBoard.get(0)[i] != null && // check for cols
                                                        currentFilledBoard.get(1)[i] != null &&
                                                        currentFilledBoard.get(2)[i] != null &&
                                                        currentFilledBoard.get(3)[i] != null &&
                                                        currentFilledBoard.get(4)[i] != null);
                                        if (isWinningBoard) {
                                            winningBoards++;

                                            if (!haveWinningBoard || winningBoards == bingoBoards.size()) {
                                                ArrayList<String[]> winningNumber = new ArrayList<>();
                                                ArrayList<String[]> finalFilledBoard = new ArrayList<>();

                                                // I have to do this to create a real copy of this ArrayList so that it will never change
                                                for (String[] lines : bingoBoardsChecked.get(currentBoard)) {
                                                    finalFilledBoard.add(lines.clone());
                                                }

                                                winningNumber.add(new String[]{number});
                                                finalBoards.add(bingoBoards.get(currentBoard));
                                                finalBoards.add(finalFilledBoard);
                                                finalBoards.add(winningNumber);
                                            }

                                            if (winningBoards == bingoBoards.size()) {
                                                break out;
                                            }
                                            haveWinningBoard = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return finalBoards;
    }

    private int sumBoard(ArrayList<String[]> board) {
        int sum = 0;
        for (String[] line : board) {
            for (String value : line) {
                if (value != null) {
                    sum += Integer.parseInt(value);
                }
            }
        }
        return sum;
    }
}
