public enum Denomination {
	Penny(0.01),
	Nickel(0.05),
	Dime(0.10),
	Quarter(0.25);

	private final double value;

	private Denomination(double value) {
        this.value = value;
}    

	public double getValue() {
		return value;

}

	@Override
	public String toString() {
		return name();

	}

}
