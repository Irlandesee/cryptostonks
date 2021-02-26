import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Iterator;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import crypto.Crypto;
import analyzer.Analyzer;

public class Main{

	private static final String error_pathname = "Could not build pathname";
	private static final String fileheaders = "symbol,date,high,open,volume,low,close";
	private static String buildPath(String fileName){
		if(fileName.equalsIgnoreCase("binancecoin_eur.txt"))
			return "../historicaldata/binancecoin_eur/binanceCoin_eur.txt";
		else if(fileName.equalsIgnoreCase("bitcoin_eur.txt"))
			return "../historicaldata/bitcoin_eur/bitcoin_eur.txt";
		else if(fileName.equalsIgnoreCase("ethereum_eur.txt"))
			return "../historicaldata/ethereum_eur/ethereum_eur.txt";
		else if(fileName.equalsIgnoreCase("polkadot_eur.txt"))
			return "../historicaldata/polkadot_eur/polkadot_eur.txt";
		else if(fileName.equalsIgnoreCase("cardano_usd.csv"))
			return "../historicaldata/cardano/cardano_usd.csv";
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

	private static HashMap<LocalDateTime, Crypto> readData_hashmap(File f){
		System.out.println("Started reading from file: "+f.getName());
		HashMap<LocalDateTime, Crypto> ris = new HashMap<LocalDateTime, Crypto>();
		Crypto c;
		try{
			BufferedReader buf = new BufferedReader(new FileReader(f));
			String sletta;
			String[] sSplittata; //lunghezza di una riga splittata
			while((sletta = buf.readLine()) != null){
				sSplittata = splitString(sletta);
				String cryptoName = sSplittata[0];
				String timeString = sSplittata[1];
				LocalDateTime time = parseTime(timeString);

				c = new Crypto(cryptoName, time);
				c.setParameters(sSplittata[2], sSplittata[3], sSplittata[4],
						sSplittata[5], sSplittata[6]);

				ris.put(time, c);
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
				sSplittata = splitString(sletta);
				String cryptoName = sSplittata[0];
				String timeString = sSplittata[1];
				LocalDateTime time = parseTime(timeString);
				c = new Crypto(cryptoName, time);
				c.setParameters(sSplittata[2], sSplittata[3], sSplittata[4],
						sSplittata[5], sSplittata[6]);

				ris.put(time, c);
			}
			buf.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return ris;
	}

	public static void main(String[] args){

		if(args.length < 1)
			System.out.println("Inserisci input file");
		else{
			String fileName = args[0];
			String completePath = buildPath(fileName);
			//HashMap<LocalDateTime, Crypto> prova = new HashMap<LocalDateTime, Crypto>();
			LinkedHashMap<LocalDateTime, Crypto> prova = new LinkedHashMap<LocalDateTime, Crypto>();

			if(!(completePath.equals(error_pathname))){
				try{
					File dataFile = new File(completePath);
					prova = readData_lhashmap(dataFile);

				}catch(NullPointerException e){
					e.printStackTrace();
				}
				/*
				Iterator it = prova.entrySet().iterator();
				while(it.hasNext()) {

					HashMap.Entry pair = (HashMap.Entry) it.next();
					Crypto c = (Crypto) pair.getValue();
					System.out.println("[DATETIME] " + pair.getKey() + " : "
							+ " [SYMBOL] " + c.getSymbolName() + " [HIGH] : " + c.getHigh()
							+ " [LOW] : " + c.getLow() + " [VOLUME] : " + c.getVolume());

					it.remove();
				}*/
				LocalDateTime start = LocalDateTime.parse("2021-01-26T10:00:00");
				LocalDateTime end = LocalDateTime.parse("2021-02-26T10:22:10");
				Analyzer a = new Analyzer(prova);
				double movingAverage = a.movingAverage(start, end);
				//double weightedMovingAverage = a.weightedMovingAverage(start, end);
				double exponentialMovingAverage = a.exponentialMovingAverage(start, end);

				System.out.println("START: "+start.toString() + " END: "+end.toString());
				System.out.println("[MA] : "+movingAverage);
				//System.out.println("[WMA] : "+weightedMovingAverage); //media mobile pesatea non funziona
				System.out.println("[EXPMA] : "+exponentialMovingAverage);

			}
			else{
				System.out.println("Could not locate the file");
			}
		}

	}

}