package crypto;
 
import java.time.LocalDateTime;

public class Crypto{

	private String symbolName;
	private LocalDateTime dateTime;
	private double close;
	private double high;
	private double low;
	private double open;
	private double volume;

	public Crypto(String symbolName, LocalDateTime dateTime){
		this.symbolName = symbolName;
		this.dateTime = dateTime;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setParameters(String close, String high, String low, String open, String volume){
		this.setClose(Double.parseDouble(close));
		this.setHigh(Double.parseDouble(high));
		this.setLow(Double.parseDouble(low));
		this.setOpen(Double.parseDouble(open));
		this.setVolume(Double.parseDouble(volume));
	}

	/**
	 *
	 * @return
	 */
	public String toString(){
		return this.symbolName + this.dateTime;
	}

}