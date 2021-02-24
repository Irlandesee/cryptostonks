import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import crypto.Crypto;

public class Main{

	private static String buildPath(String fileName){
		if(fileName.toLowerCase().equals("binancecoin_eur.txt"))
			return "../historicaldata/binancecoin_eur/binanceCoin_eur.txt";
		else if(fileName.toLowerCase().equals("bitcoin_eur.txt"))
			return "../historicaldata/bitcoin_eur/bitcoin_eur.txt";
		else if(fileName.toLowerCase().equals("ethereum_eur.txt"))
			return "../historicaldata/ethereum_eur/ethereum_eur.txt";
		else if(fileName.toLowerCase().equals("polkadot_eur.txt"))
			return "../historicaldata/ethereum_eur/polkadot_eur.txt";
		return "Could not build pathname";
	}

	private static String[] splitString(String s){
		return s.split(",");
	}

	private static LocalDateTime parseTime(String time){
		String[] timeSplitted = time.split(" ");
		String ris = timeSplitted[0]+"T"+timeSplitted[1];
		return LocalDateTime.parse(ris);
	}

	private static HashMap<LocalDateTime, Crypto> readData(File f){
		System.out.println("Started reading from file: "+f.getName());
		HashMap<LocalDateTime, Crypto> ris = new HashMap<LocalDateTime, Crypto>();
		Crypto c;
		try{
			BufferedReader buf = new BufferedReader(new FileReader(f));
			String stringaLetta = "";
			String[] stringaSplittata = new String[7]; //lunghezza di una riga splittata
			while((stringaLetta = buf.readLine()) != null){
				stringaSplittata = splitString(stringaLetta);
				String cryptoName = stringaSplittata[0];
				String timeString = stringaSplittata[1];
				LocalDateTime time = parseTime(timeString);

				c = new Crypto(cryptoName, time);
				c.setParameters(stringaSplittata[2], stringaSplittata[3], stringaSplittata[4],
						stringaSplittata[5], stringaSplittata[6]);

				ris.put(time, c);
			}

			buf.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return ris;
	}


	public static void main(String[] args){
		/**
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Now :"+now);
		String cryptoName = "BTC-EUR";


		Crypto c = new Crypto(cryptoName, now);
		//System.out.println(c.toString());

		HashMap<LocalDateTime, Crypto> hMap = new HashMap<LocalDateTime, Crypto>();
		hMap.put(now, c);

		//System.out.println(hMap.toString());
		Crypto prova = hMap.get(now);
		System.out.println("Printing prova: ");
		System.out.println(prova.toString());
		**/

		/**
		String time = "2021-01-25 23:00:00";

		LocalDateTime prova = parseTime(time);

		System.out.println("prova: "+prova);
		**/

		if(args.length < 1)
			System.out.println("Inserisci input file");
		else{
			String fileName = args[0];
			try{


			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}

	}

}