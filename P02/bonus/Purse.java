public class Purse {
	public static void main(String[] args) {
		Coin[] coins = {
 	
		new Coin(Denomination.Penny, 2015),	
		new Coin(Denomination.Nickel, 2014),
		new Coin(Denomination.Dime, 1990),	
		new Coin(Denomination.Quarter, 2008),
		new Coin(Denomination.Dime, 1982)
	};
  double totalValue = 0;
        int earliestYear = Integer.MAX_VALUE;
        int latestYear = Integer.MIN_VALUE;

        for (Coin coin : coins) {
            totalValue += coin.getValue();
            if (coin.getYear() < earliestYear) {
                earliestYear = coin.getYear();
            }
            if (coin.getYear() > latestYear) {
                latestYear = coin.getYear();
            }
        }

        System.out.printf("You have $%.2f in coins between %d and %d%n", totalValue, earliestYear, latestYear);
        System.out.println("Coins:");
        for (Coin coin : coins) {
            System.out.println(coin); 
        }
    }
}
