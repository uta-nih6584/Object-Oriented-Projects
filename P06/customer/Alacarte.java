package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public class Alacarte extends Account {
    private int pointsRemaining;

    public Alacarte(int points) {
        super();
        this.pointsRemaining = points;
    }

    public Alacarte(BufferedReader br) throws IOException {
        super(br);
        this.pointsRemaining = Integer.parseInt(br.readLine());
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(Integer.toString(pointsRemaining) + '\n');
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
