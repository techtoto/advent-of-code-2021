package io.github.techtoto.aoc2021;

public abstract class AbstractDay {
    public int[] solutions = new int[2];
    public StringBuilder[] outputs = new StringBuilder[2];

    public AbstractDay() {
        outputs[0] = new StringBuilder();
        outputs[1] = new StringBuilder();
    }

    public int getSolution(int i) {
        return solutions[i];
    }

    public String getOutput(int i) {
        return outputs[i].toString();
    }

}
