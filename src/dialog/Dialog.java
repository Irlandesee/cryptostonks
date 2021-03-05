package dialog;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedList;
import crypto.Crypto;
import analyzer.Analyzer;


public class Dialog {

    private Analyzer a;

    private final String ma = "[MA]";
    private final String wma = "[WMA]";
    private final String exma = "[EXMA]";
    private final String intervalliSuggeriti = "\nIntervalli Suggeriti: 20, 50, 200";
    private final String valoriFibonacci = "0.236, 0.382, 0.5, 0.618, 0.764, 1";
    private final String readyString = ">>>";

    private LinkedList<String> availableCommands;
    private String[] commands = {"ma", "exma", "trova fibonacci", "visualizza dati", "scarica dati", "quit"};

    public Dialog(Analyzer a){
        System.out.println("CryptoStonks Dialog");
        this.a = a;
        availableCommands = new LinkedList<String>();
    }

    private void setUpCommands(){
        for(String x : commands)
            availableCommands.add(x);
    }

    public void printFunctions(){
        System.out.println("---1. Calcola Media Mobile: " +intervalliSuggeriti);
        //System.out.println("---Calcola Media Mobile Pesata: ");
        System.out.println("---2. Calcola Media Mobile Esponenziale: " +intervalliSuggeriti);
        System.out.println("---3. Trova sezioni di fibonacci: " +intervalliSuggeriti);
        System.out.println("---4. Visualizza dati storici ");
        System.out.println("---5. Scarica nuovi dati storici: ");
        System.out.println("---6. quit");
    }

    public void inputFunctions(){
        Scanner in = new Scanner(System.in);
        System.out.print(readyString);
        String command = "";
        while((command = in.nextLine()).equals("quit")){
            for(int i = 0; i < commands.length; i++){
                if(command.equals(commands[i])){
                    Command c = new Command(command, a, availableCommands, this);
                    c.executeCommand();
                }
                else{
                    System.out.println("Comando non valido");
                    System.out.print(readyString);
                }
            }
        }
        in.close();
    }

    //2007-12-03T10:15:30
    private LocalDateTime parseTime(String time){
        String[] s = time.split(" ");
        String ris = s[0].concat("T").concat(s[1]);
        return LocalDateTime.parse(ris);
    }

    public LocalDateTime getTime(){
        Scanner in = new Scanner(System.in);
        String line = "";
        LocalDateTime res = LocalDateTime.MAX;
        System.out.println(readyString);
        while(!(line = in.nextLine()).equals("")){
            res = parseTime(line);
            System.out.println(readyString);
        }

        in.close();
        return res;
    }

}
