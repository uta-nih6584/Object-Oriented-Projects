public class Coin {
	private final Denomination denomination;
	private final int year;

	public Coin(Denomination denomination, int year) {
        this.denomination = denomination; 
        this.year = year;

}

public double getValue() {
        return denomination.getValue();
 
   }

    public int getYear() {
        return year;

    }
