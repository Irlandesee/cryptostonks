package dialog;

import analyzer.Analyzer;
import analyzer.Fibonacci;
import dialog.Dialog;
import crypto.Crypto;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Command{

    private String command;
    private Analyzer a;
    private Fibonacci f;
    private LinkedList<String> commands;
    private Dialog in;
    private String commandError = "Command not found";
    private LocalDateTime start = LocalDateTime.MAX, end = LocalDateTime.MAX;

    public Command(String command, Analyzer a, LinkedList<String> commands, Dialog in){
        this.command = command;
        this.a = a;
        this.commands = commands;
        this.in = in;
        f = new Fibonacci();
    }

    public double executeCommand(){
        if(commands.contains(command)){
            switch(command.toLowerCase()){
                case "ma":
                    System.out.println("{CALCULATING MA}");
                    System.out.print("Inserisci data e ora di inizio.");
                    start = in.getTime();
                    System.out.print("Inserisci data e ora di fine.");
                    end = in.getTime();
                    double ma = a.movingAverage(start, end);
                    break;
                case "exma":
                    System.out.println("{CALCULATING EXMA}");
                    System.out.print("Inserisci data e ora di inizio.");
                    start = in.getTime();
                    System.out.print("Inserisci data e ora di fine.");
                    end = in.getTime();
                    double exma = a.exponentialMovingAverage(start, end);
                    break;
                case "trova fibonacci":
                    System.out.println("{FINDING FIBONACCI ZONES}");
                    System.out.print("Inserisci data e ora di inizio.");
                    start = in.getTime();
                    System.out.print("Inserisci data e ora di fine.");
                    end = in.getTime();
                    break;
                case "visualizza dati":
                    System.out.println("{PRINTING HISTORICAL DATA}");
                    System.out.print("Inserisci data e ora di inizio.");
                    start = in.getTime();
                    System.out.print("Inserisci data e ora di fine.");
                    end = in.getTime();
                    break;
                case "scarica dati":
                    System.out.println("{DOWNLOADING DATA}");
                    System.out.print("Inserisci data e ora di inizio.");
                    start = in.getTime();
                    System.out.print("Inserisci data e ora di fine.");
                    end = in.getTime();
                    break;
                case "quit":
                    System.out.println("{EXITING}");
                    System.exit(0);
                    break;
            }
        }

        System.out.println(commandError);
        return -1;
    }


    public String toString(){
        return this.command;
    }

}
