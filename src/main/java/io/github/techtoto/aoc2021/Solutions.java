package io.github.techtoto.aoc2021;

import io.github.techtoto.aoc2021.day01.Day01;
import io.github.techtoto.aoc2021.day02.Day02;
import io.github.techtoto.aoc2021.day03.Day03;
import io.github.techtoto.aoc2021.day04.Day04;
import io.github.techtoto.aoc2021.day05.Day05;
import io.github.techtoto.aoc2021.day06.Day06;
import io.github.techtoto.aoc2021.day07.Day07;
import io.github.techtoto.aoc2021.day08.Day08;

import java.util.ArrayList;

public class Solutions {
    public static void main(String[] args) {
        ArrayList<AbstractDay> days = new ArrayList<>();

        days.add(new Day01("day01/day01_input.txt", false, false));
        days.add(new Day02("day02/day02_input.txt", false, false));
        days.add(new Day03("day03/day03_input.txt", false, false));
        days.add(new Day04("day04/day04_input.txt", false, false));
        days.add(new Day05("day05/day05_input.txt", false, false));
        days.add(new Day06("day06/day06_input.txt", false, false));
        days.add(new Day07("day07/day07_input.txt", false, false));
        days.add(new Day08("day08/day08_input.txt", false, false));

        for (AbstractDay day : days) {
            System.out.println(day.getClass().getSimpleName() + ": ");
            System.out.println("Solutions: " + day.getSolution(0) + "; " + day.getSolution(1));
            if (day.getPrintOutput(0)) {
                System.out.println("Further outputs for part 1:");
                System.out.println(day.getOutput(0));
            }
            if (day.getPrintOutput(1)) {
                System.out.println("Further outputs for part 2:");
                System.out.println(day.getOutput(1));
            }
            System.out.println("==============");
        }
    }
}
