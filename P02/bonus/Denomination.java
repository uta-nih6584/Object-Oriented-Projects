public enum Denomination {
    Penny(0.01),
    Nickel(0.05),
    Dime(0.10),
    Quarter(0.25);

    private final double value;

    // adding the constructor to my pre-exisitng code
    private Denomination(double value) {
        this.value = value;
    
