import time
import pandas
import csv
import sys
import argparse
import requests
import os
from yahooquery import Ticker


#Length of time
periods = ['1d', '5d', '7d', '60d', '1mo', '3mo', '6mo', '1y', '2y', '5y', '10y', 'ytd', 'max']
#Time between data points
interval = ['1m', '2m', '5m', '15m', '30m', '60m', '90m', '1h', '1d', '5d', '1wk', '1mo', '3mo']

yahoo_url = "https://it.finance.yahoo.com/quote/"

symbol_list_file = "./symbols_list.txt"
storagePath = "./historicaldata/"
dirnames = ["binancecoin_eur", "bitcoin_eur", "cardano", "ethereum_eur", "polkadot_eur"]

def get_os_separator():
	return os.path.sep

def wait(seconds):
	print("waiting {} seconds".format(seconds))
	time.sleep(int(seconds))

def log_toFile(tickername, dataFrame):
	filename = tickername + ".csv"
	fullpath = ""
	separator = get_os_separator()
	for name in dirnames:
		if name == tickername:
			fullpath = "{}{}{}{}".format(storagePath, name, separator, filename)

	print("Logging ticker {} to file in: {}".format(tickername, fullpath))
	dataFrame.to_csv(fullpath)

def fetch_ticker(period, interval, secs_towait, dictofSymbols = {}):
	for symbol in dictofSymbols:
		print("Fetching {}".format(dictofSymbols[symbol]))
		ticker = Ticker(dictofSymbols[symbol])
		dataFrame = ticker.history(period, interval) #define period and interval		
		log_toFile(symbol, dataFrame)
		wait(secs_towait) #define secs_towait 

"""
#keeps adding symbols to list until the user writes done
#return list of symbols
"""
def input_symbols():
	print("Please input symbols to fetch, enter done to finish entering symbols.\n ")
	l = []
	sym = ""
	while sym != "done":
		sym = input()
		if sym.lower() != "done":
			l.append(sym.upper())
			print("Added {} to list".format(sym))

	return l

"""
#sets up argument parser and returns command line options as a dictionary
"""
def get_options():
	parser = argparse.ArgumentParser()
	parser.add_argument("-p", "--period", help="Length of time", required=True)
	parser.add_argument("-i", "--interval", help="Interval between data points", required=True)
	parser.add_argument("-s", "--seconds", help="Seconds to wait between each request", required=True)
	parser.add_argument("-v", "--verbose", help="Better output in the terminal",
		action="store_true")
	args = parser.parse_args()
	args = vars(args) #convert to dictionary
	return args


"""
#reads from saved file and returns a dictionary containing symbols
"""
def get_symbols():
	f = open(symbol_list_file, "r")
	symlist = []
	ris = {}

	for line in f:
		symlist.append(line.split(" : "))
		#print("{} {}".format(symlist[0], symlist[1]))
	
	for i in range(len(symlist)):
		l = symlist[i]
		k = l[0]
		v = l[1].rstrip("\n")
		ris[k] = v

	return ris

	
#cross checks for errors
#def check_symbols(symbols = {}, symbols_requested = []):

if __name__ == "__main__":
	
	args = get_options()
	
	period = args["period"]
	interval = args["interval"]
	seconds = args["seconds"]

	symbols = get_symbols()
	fetch_ticker(period, interval, seconds, symbols)