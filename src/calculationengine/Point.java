package calculationengine;

import static util.Converter.toRadians;

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
    public double phiRadians;
    public double lambdaRadians;

    public Point(String latSide, double latDeg, double latMin, String longSide, double longDeg, double longMin) {
        this.setLatSide(latSide);
        this.setLatDeg(latDeg);
        this.setLatMin(latMin);

        this.setLongSide(longSide);
        this.setLongDeg(longDeg);
        this.setLongMin(longMin);

        this.setLatCalculated(latDeg + latMin / 60);
        this.setLongCalculated(longDeg + longMin / 60);

        this.setPhi(latDeg + latMin / 60);
        if (latSide.equals("S")) {
            this.setPhi(this.getPhi()*-1);
        }
        setPhiRadians(toRadians(this.getPhi()));

        this.setLambda(longDeg + longMin / 60);
        if (longSide.equals("W")) {
            this.setLambda(this.getLambda()*-1);
        }
        setLambdaRadians(toRadians(this.getLambda()));
    }

    public Point(double phi, double lambda) {
        this.setPhi(phi);
        this.setPhiRadians(toRadians(phi));
        this.setLatCalculated(Math.abs(phi));

        this.setLambda(lambda);
        this.setLambdaRadians(toRadians(lambda));
        this.setLongCalculated(Math.abs(lambda));

        if (phi < 0) {
            this.setLatSide("S");
        } else if (phi > 0) {
            this.setLatSide("N");
        } else {
            this.setLatSide("");
        }

        if (lambda < 0) {
            this.setLongSide("W");
        } else if (lambda > 0) {
            this.setLongSide("E");
        } else {
            this.setLongSide("");
        }
    }

    public String getLatSide() {
        return latSide;
    }

    private void setLatSide(String latSide) {
        this.latSide = latSide;
    }

    public double getLatDeg() {
        return latDeg;
    }

    private void setLatDeg(double latDeg) {
        this.latDeg = latDeg;
    }

    public double getLatMin() {
        return latMin;
    }

    private void setLatMin(double latMin) {
        this.latMin = latMin;
    }

    public String getLongSide() {
        return longSide;
    }

    private void setLongSide(String longSide) {
        this.longSide = longSide;
    }

    public double getLongDeg() {
        return longDeg;
    }

    private void setLongDeg(double longDeg) {
        this.longDeg = longDeg;
    }

    public double getLongMin() {
        return longMin;
    }

    private void setLongMin(double longMin) {
        this.longMin = longMin;
    }

    public double getLatCalculated() {
        return latCalculated;
    }

    private void setLatCalculated(double latCalculated) {
        this.latCalculated = latCalculated;
    }

    public double getLongCalculated() {
        return longCalculated;
    }

    private void setLongCalculated(double longCalculated) {
        this.longCalculated = longCalculated;
    }

    public double getPhi() {
        return phi;
    }

    private void setPhi(double phi) {
        this.phi = phi;
    }

    public double getLambda() {
        return lambda;
    }

    private void setLambda(double lambda) {
        this.lambda = lambda;
    }

    double getPhiRadians() {
        return phiRadians;
    }

    private void setPhiRadians(double phiRadians) {
        this.phiRadians = phiRadians;
    }

    double getLambdaRadians() {
        return lambdaRadians;
    }

    private void setLambdaRadians(double lambdaRadians) {
        this.lambdaRadians = lambdaRadians;
    }
}
