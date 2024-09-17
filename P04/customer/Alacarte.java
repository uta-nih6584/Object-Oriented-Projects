package customer;

import product.Media;  

public class Alacarte extends Account {
    private int pointsRemaining;

    public Alacarte(int initialPoints) {
        this.pointsRemaining = initialPoints;
    }

    public void buyPoints(int points) {
        this.pointsRemaining += points;
    }

    public int getPointsRemaining() {
        return pointsRemaining;
    }

    @Override
    public String play(Media media) {
        if (pointsRemaining >= media.getPoints()) {
            pointsRemaining -= media.getPoints();
            return "Playing " + media;
        } else {
            return "Buy more points: Requires " + media.getPoints() + " points, you have " + pointsRemaining;
        }
    }
}
