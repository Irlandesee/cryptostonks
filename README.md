# cryptostonks


#Warning
The yahoo finance api restricts the amount of one minute interval data to seven days per request
The data availability extends to 30 days. The following will allow the user to retrieve the last 30 days
of one miunute interval data, with the one caveat that 4 requests are made in 7 day ranges to retrieve the 
desired data:

tickers = Ticker('fb appl nflx', asynchrounous=True)
df = tickers.history(period='1mo', interval='1m')