import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import java.util.Iterator;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import crypto.Crypto;
import analyzer.Analyzer;
import dialog.Command;
import dialog.Dialog;
import interval.Interval;
import interval.IntervalDate;
import interval.IntervalDateTime;

public class Main{

	private static final String error_pathname = "Could not build pathname";
	private static final String fileheaders = "symbol,date,close,high,low,open,volume";
	private static final String systemSeparator = File.separator;

	private static String buildPath(String fileName){
		if(fileName.equalsIgnoreCase("binancecoin_eur"))
			return ".."+systemSeparator+"historicaldata"+systemSeparator+"binancecoin_eur"+systemSeparator+"binanceCoin_eur.csv";
		else if(fileName.equalsIgnoreCase("bitcoin_eur"))
			return ".."+systemSeparator+"historicaldata"+systemSeparator+"bitcoin_eur"+systemSeparator+"bitcoin_eur.csv";
		else if(fileName.equalsIgnoreCase("ethereum_eur"))
			return ".."+systemSeparator+"historicaldata"+systemSeparator+"ethereum_eur"+systemSeparator+"ethereum_eur.csv";
		else if(fileName.equalsIgnoreCase("polkadot_eur"))
			return ".."+systemSeparator+"historicaldata"+systemSeparator+"polkadot_eur"+systemSeparator+"polkadot_eur.csv";
		else if(fileName.equalsIgnoreCase("cardano_usd"))
			return ".."+systemSeparator+"historicaldata"+systemSeparator+"cardano"+systemSeparator+"cardano_usd.csv";
		return error_pathname;
	}

	private static String[] splitString(String s){
		return s.split(",");
	}

	private static LocalDateTime parseTime(String time){
		String[] timeSplitted = time.split(" ");
		String ris = timeSplitted[0]+"T"+timeSplitted[1];
		return LocalDateTime.parse(ris);
	}

	/**
	 * Reads the first line from the file, and preps it to be read.
	 * needs to be tested
	 * @param f
	 */
	private static void prepareFileForReading(File f){
		System.out.println("preparing "+f.getName()+" for reading.");
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			StringBuffer buf = new StringBuffer();
			while((line = br.readLine()) != null){
				if(line.equals(fileheaders)){
					line = "";
					buf.append(line);
					buf.append("\n");
				}
				else{
					buf.append(line);
					buf.append("\n");
				}
			}
			br.close();
			FileOutputStream outFile = new FileOutputStream(f);
			outFile.write(buf.toString().getBytes());
			outFile.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static HashMap<LocalDateTime, Crypto> readData_hashmap(File f){
		System.out.println("Started reading from file: "+f.getName());
		HashMap<LocalDateTime, Crypto> ris = new HashMap<LocalDateTime, Crypto>();
		Crypto c;
		try{
			BufferedReader buf = new BufferedReader(new FileReader(f));
			String sletta;
			String[] sSplittata; //lunghezza di una riga splittata
			while((sletta = buf.readLine()) != null){
				System.out.println(sletta);
				if(!(sletta.equals("") || sletta.equals("\n"))){
					sSplittata = splitString(sletta);
					String cryptoName = sSplittata[0];
					String timeString = sSplittata[1];
					LocalDateTime time = parseTime(timeString);

					c = new Crypto(cryptoName, time);
					c.setParameters(sSplittata[2], sSplittata[3], sSplittata[4],
							sSplittata[5], sSplittata[6]);

					ris.put(time, c);
				}
				else
					continue;
			}

			buf.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return ris;
	}

	private static LinkedHashMap<LocalDateTime, Crypto> readData_lhashmap(File f){
		System.out.println("Started reading from file: "+f.getName());
		LinkedHashMap<LocalDateTime, Crypto> ris = new LinkedHashMap<LocalDateTime, Crypto>();
		Crypto c;

		try{
			BufferedReader buf = new BufferedReader(new FileReader(f));
			String sletta;
			String[] sSplittata;
			while((sletta = buf.readLine()) != null){
				if(!(sletta.equals(""))){
					sSplittata = splitString(sletta);
					String cryptoName = sSplittata[0];
					String timeString = sSplittata[1];
					LocalDateTime time = parseTime(timeString);
					c = new Crypto(cryptoName, time);
					c.setParameters(sSplittata[2], sSplittata[3], sSplittata[4],
							sSplittata[5], sSplittata[6]);

					ris.put(time, c);
				}
				else
					continue;
			}
			buf.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return ris;
	}

	public static void main(String[] args){
		/*
		if(args.length < 1)
			System.out.println("Inserisci input file");
		else{
			String fileName = args[0];
			String completePath = buildPath(fileName);
			System.out.println(completePath);
			//HashMap<LocalDateTime, Crypto> prova = new HashMap<LocalDateTime, Crypto>();
			LinkedHashMap<LocalDateTime, Crypto> prova = new LinkedHashMap<LocalDateTime, Crypto>();

			if(!(completePath.equals(error_pathname))){
				try{
					File dataFile = new File(completePath);
					prepareFileForReading(dataFile);
					prova = readData_lhashmap(dataFile);

				}catch(NullPointerException e){
					e.printStackTrace();
				}

			}
			else{
				System.out.println("Could not locate the file");
			}
		}
		*/

		IntervalDateTime intTmp = new IntervalDateTime();
		IntervalDateTime intTemp = new IntervalDateTime();

		LocalDateTime start = LocalDateTime.parse("2021-01-31T23:00:00");
		LocalDateTime end = LocalDateTime.parse("2021-01-31T23:20:00");
		



	}

}