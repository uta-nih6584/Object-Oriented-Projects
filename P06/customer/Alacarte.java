package customer;

import product.Media;

public class Alacarte extends Account {
    private int pointsRemaining;

    public Alacarte(int points) {
        super();
        this.pointsRemaining = points;
    }

    public int getPointsRemaining() {
        return pointsRemaining;
    }

    public void buyPoints(int points) {
        pointsRemaining += points;
    }

    @Override
    public String play(Media media) {
        if (pointsRemaining >= media.getPoints()) {
            pointsRemaining -= media.getPoints();
            return "Playing " + media.toString();
        } else {
            return "Not enough points";
        }
    }
}
