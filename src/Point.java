import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Point {

    public String latSide;
    public double latDeg;
    public double latMin;
    public String longSide;
    public double longDeg;
    public double longMin;
    public double latCalculated;
    public double longCalculated;
    public double phi;
    public double lambda;

    public Point(String latSide, double latDeg, double latMin, String longSide, double longDeg, double longMin) {
        this.latSide = latSide;
        this.latDeg = latDeg;
        this.latMin = latMin;

        this.longSide = longSide;
        this.longDeg = longDeg;
        this.longMin = longMin;

        this.latCalculated = latDeg + latMin / 60;
        this.longCalculated = longDeg + longMin / 60;

        this.phi = latDeg + latMin / 60;
        if (latSide.equals("S")) {
            this.phi *= -1;
        }

        this.lambda = longDeg + longMin / 60;
        if (longSide.equals("W")) {
            this.lambda *= -1;
        }
    }

    public Point(double phi, double lambda) {

        this.phi = phi;
        this.latCalculated = Math.abs(phi);

        this.lambda = lambda;
        this.longCalculated = Math.abs(lambda);

        if (phi < 0) {
            this.latSide = "S";
        } else if (phi > 0) {
            this.latSide = "N";
        } else {
            this.latSide = "";
        }

        if (lambda < 0) {
            this.longSide = "W";
        } else if (lambda > 0) {
            this.longSide = "E";
        } else {
            this.longSide = "";
        }
    }

}
