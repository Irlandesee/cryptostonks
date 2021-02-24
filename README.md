# cryptostonks

#DESCRIPTION:

#NOTES:

#TODO:
	[fetcher.py]
	1. Aggiornare la cartella di scrittura in maniera tale che corrisponda alla propria cartella. Il nome 
		della cartella sara il semplicemente il nome della criptovaluta in minuscolo 
		- Se piu di un file esiste con lo stesso nome, il suo nome sara allora
		bitcoin_eur_XXXX_XX_XX dove le X corrispondono ad ANNO_MESE_GIORNO
		- Se ancora piu di file esiste con lo stesso nome, nonostante l'aggiunta
		della data, allora:
		bitcoid_eur_XXXX_XX_XX_Y dove X corrispondono ad ANNO_MESE_GIORNO e Y sara n+=1
		(n numero naturale, partendo da 1)
		-Inoltre, la scrittura dovra rispettare il sistema operativo:
		es:
		Se su windows si utilizzera \
		Se su mac o linux /
	2.Implementare controllo librerie necessarie: yahooquery, requests
	3.Implementare funzione di richiesta ticker singoli o multipli da linea di comando


	[Main.java]
	1.Scrivere documentazione relativa a metodi
	2.Implementare eliminazione della prima riga del datafile prima della lettura del file
	3.Implementare costruzione percorsi relativamente al sistema operativo in uso



#PROBLEMS:

#Warning
The yahoo finance api restricts the amount of one minute interval data to seven days per request
The data availability extends to 30 days. The following will allow the user to retrieve the last 30 days
of one miunute interval data, with the one caveat that 4 requests are made in 7 day ranges to retrieve the 
desired data:

tickers = Ticker('fb appl nflx', asynchrounous=True)
df = tickers.history(period='1mo', interval='1m')


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