package analyzer;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import crypto.Crypto;

public class Fibonacci{

    private LinkedHashMap<LocalDateTime, Crypto> datiStorici;
    private LinkedList<Double> goldenSection;

    public Fibonacci(LinkedHashMap<LocalDateTime, Crypto> datiStorici){
        this.datiStorici = datiStorici;
        goldenSection = new LinkedList<Double>();

    }

    public LinkedHashMap<LocalDateTime, Crypto> getDatiStorici(){
        return datiStorici;
    }

    public void setDatiStorici(LinkedHashMap<LocalDateTime, Crypto> datiStorici){
        this.datiStorici = datiStorici;
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