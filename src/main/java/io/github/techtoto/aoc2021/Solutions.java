package io.github.techtoto.aoc2021;

import io.github.techtoto.aoc2021.day01.Day01;
import io.github.techtoto.aoc2021.day02.Day02;

import java.util.ArrayList;

public class Solutions {
    public static void main(String[] args) {
        ArrayList<AbstractDay> days = new ArrayList<>();

        days.add(new Day01("day01/day01_input.txt", false, false));
        days.add(new Day02("day02/day02_input.txt", false, false));

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
