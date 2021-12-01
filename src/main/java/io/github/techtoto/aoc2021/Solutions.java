package io.github.techtoto.aoc2021;

import io.github.techtoto.aoc2021.day01.Day01;

import java.util.ArrayList;

public class Solutions {
    public static void main(String[] args) {
        ArrayList<AbstractDay> days = new ArrayList<>();

        days.add(new Day01("day01/day01_input.txt"));

        for (AbstractDay day : days)
            System.out.print(day.getClass().getSimpleName() + ": " + day.getSolution(0) + ", " + day.getSolution(1));
    }
}
