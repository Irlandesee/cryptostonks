package analyzer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import crypto.Crypto;

public class Analyzer {

    private LinkedHashMap<LocalDateTime, Crypto> historicalData;
    //private HashMap<LocalDateTime, Crypto> datiStorici;

    public Analyzer(LinkedHashMap<LocalDateTime, Crypto> historicalData){
        this.historicalData = historicalData;
    }

    public LinkedHashMap<LocalDateTime, Crypto> getHistoricalData(){
        return this.historicalData;
    }

    public void setHistoricalData(LinkedHashMap<LocalDateTime, Crypto> historicalData){
        this.historicalData = historicalData;
    };

    public double calcPercentage(double a, double b){
        double ris = a * b;
        return ris/100;
    }

    public boolean isNewDay(LocalDateTime x, LocalDateTime y){
        LocalDate tmp = x.toLocalDate();
        LocalDate temp = x.toLocalDate();

        if(tmp.isEqual(temp))
            return false;
        else if(temp.isAfter(tmp))
            return true;
        return false;
    }

    /**
     * Stupid method to get fast a period
     * @return
     */
    public int getPeriod(LinkedHashMap<LocalDateTime, Crypto> m){
        return m.size();
    }

    /**
     * Return the crypto object if it exists in the map
     * else it returns null
     * @param dayTime
     * @return
     */
    public Crypto getCryptoAt(LocalDateTime dayTime){
        if(historicalData.containsKey(dayTime))
            return historicalData.get(dayTime);
        else
            return null;
    }

    /**
     * Copies values from historicalData into a new lHashMap
     * in the range (start, end)
     * @param start
     * @param end
     * @return
     */
    public LinkedHashMap<LocalDateTime, Crypto> getCryptoFromTo(LocalDateTime start, LocalDateTime end){
        LinkedHashMap<LocalDateTime, Crypto> ris = new LinkedHashMap<LocalDateTime, Crypto>();
        Iterator it = historicalData.entrySet().iterator();

        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry) it.next();
            LocalDateTime tmp = (LocalDateTime) pair.getKey();
            if(tmp.isEqual(start) || tmp.isEqual(end))
                ris.put((LocalDateTime) pair.getKey(), (Crypto) pair.getValue());
            else if(tmp.isAfter(start) && tmp.isBefore(end))
                ris.put((LocalDateTime) pair.getKey(), (Crypto) pair.getValue());
        }
        return ris;
    }

    /**
     * Simple Arithmetic Mean
     * @return
     */
    public double movingAverage(LocalDateTime start, LocalDateTime end){
        LinkedHashMap<LocalDateTime, Crypto> data = getCryptoFromTo(start, end);
        int period =  getPeriod(data);
        Iterator it = data.entrySet().iterator();
        Crypto c;
        double movingAverage = 0;

        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry) it.next();
            c = (Crypto) pair.getValue();
            movingAverage += c.getClose();
            //System.out.println(c.getDateTime().toString() + " : [MA] -> " + movingAverage/period);
        }

        return (double) movingAverage / period;
    }

    /**
     * Simple Arithmetic mean, but every day has a different weight
     * Days in far past have less weight than days in the recent past
     * @return
     */
    public double weightedMovingAverage(LocalDateTime start, LocalDateTime end){
        LinkedHashMap<LocalDateTime, Crypto> data = getCryptoFromTo(start, end);

        Iterator it = data.entrySet().iterator();

        Crypto c;
        double weightedMovingAverage = 0;
        double weight = 0;
        int period = getPeriod(data);
        System.out.println(period);

        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry) it.next();
            c = (Crypto) pair.getValue();
            weight += 1 / period;
            weightedMovingAverage += c.getClose() * weight;
            //System.out.println(c.getDateTime().toString() + " [WMA] ->" + weightedMovingAverage);
        }

        return (double) weightedMovingAverage;
    }

    public double getMultiplier(int numberOfObservations){
        return (double)2/(numberOfObservations + 1);
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public double exponentialMovingAverage(LocalDateTime start, LocalDateTime end){
        //formula: EMA = Closing price x multiplier + EMA (previous day) x (1-multiplier)
        //determine the SMA for the period
        //calculate multiplier = 2 / (number of period + 1)
        //closing_price * multiplier + EMA_previousDay * (1-multiplier)

        LinkedHashMap<LocalDateTime, Crypto> data = getCryptoFromTo(start, end);
        Iterator it = data.entrySet().iterator();

        Crypto c;

        double exponentialMovingAverage_yesterday = 0, exponentialMovingAverage_today = 0;

        double closingPrice = 0;
        double multiplier = 0;
        int period = 0;
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry) it.next();
            c = (Crypto) pair.getValue();
            closingPrice = c.getClose();
            period += 1;

            multiplier = getMultiplier(period);

            exponentialMovingAverage_today = closingPrice * multiplier
                    + exponentialMovingAverage_yesterday + (1 - multiplier);
            //System.out.println(c.getDateTime().toString() + " : [EXPMA] -> " +exponentialMovingAverage_today);
        }

        return exponentialMovingAverage_today;
    }

}
