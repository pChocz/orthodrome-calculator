class Point extends Controller{

    String latSide;
    double latDeg;
    double latMin;
    String longSide;
    double longDeg;
    double longMin;
    double latCalculated;
    double longCalculated;
    double phi;
    double lambda;
    double phiRadians;
    double lambdaRadians;

    Point(String latSide, double latDeg, double latMin, String longSide, double longDeg, double longMin) {
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
        phiRadians = toRadians(this.phi);

        this.lambda = longDeg + longMin / 60;
        if (longSide.equals("W")) {
            this.lambda *= -1;
        }
        lambdaRadians = toRadians(this.lambda);

    }

    Point(double phi, double lambda) {

        this.phi = phi;
        this.phiRadians = toRadians(phi);
        this.latCalculated = Math.abs(phi);

        this.lambda = lambda;
        this.lambdaRadians = toRadians(lambda);
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
