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


    public Command(String command, Analyzer a, LinkedList<String> commands, Dialog in){
        this.command = command;
        this.a = a;
        this.commands = commands;
        f = new Fibonacci();
        this.in = in;
    }

    public double executeCommand(){
        if(commands.contains(command)){
            switch(command.toLowerCase()){
                case "ma":
                    System.out.println("{CALCULATING MA}");

                    double ma = a.movingAverage();
                    break;
                case "exma":
                    System.out.println("{CALCULATING EXMA}");
                    double exma = a.exponentialMovingAverage();
                    break;
                case "trova fibonacci":
                    System.out.println("{FINDING FIBONACCI ZONES}");
                    break;
                case "visualizza dati":
                    System.out.println("{PRINTING HISTORICAL DATA}");

                    break;
                case "scarica dati":
                    System.out.println("{DOWNLOADING DATA}");
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
