package analyzer;

import java.util.LinkedList;

public class Fibonacci{

    private LinkedList<Double> goldenSection;

    public Fibonacci(){
        goldenSection = new LinkedList<Double>();
        this.setUpGoldenSection();
    }

    private void setUpGoldenSection() {
        double[] s = {0.236, 0.382, 0.5, 0.618, 0.764, 1};
        for (Double d : s)
            goldenSection.add(d);
    }

    public LinkedList<Double> getGoldenSection(){
        return goldenSection;
    }

    public String toString(){
        return "0.236, 0.382, 0.5, 0.618, 0.764, 1";
    }
}