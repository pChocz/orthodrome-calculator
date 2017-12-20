public class Orthodrome extends Converter {

    //input
    private SphericalTriangle sphericalTriangle;

    //output
    private double distance;
    private double height1;
    private double height2;


    public double getHeight1() {
        return height1;
    }

    public void setHeight1(double height1) {
        this.height1 = height1;
    }

    public double getHeight2() {
        return height2;
    }

    public void setHeight2(double height2) {
        this.height2 = height2;
    }


    public Orthodrome(SphericalTriangle sphericalTriangle) {
        this.sphericalTriangle = sphericalTriangle;
        this.distance = calculateDistance(sphericalTriangle);
    }

    private double calculateDistance(SphericalTriangle sphericalTriangle) {
        return sphericalTriangle.d;
    }

    public double getDistance() {
        return distance;
    }

    public String getDistanceNm() {
        return String.valueOf(String.format("%.2f", this.distance * 60)) + " Mm";
    }

    public Point calculateFirstOrthodromeVertex(Point aPoint, Point bPoint) {
        double numerator =
                Math.tan(toRadians(aPoint.phi)) * Math.sin(toRadians(bPoint.lambda))
                        - Math.tan(toRadians(bPoint.phi)) * Math.sin(toRadians(aPoint.lambda));

        double denominator =
                Math.tan(toRadians(aPoint.phi)) * Math.cos(toRadians(bPoint.lambda))
                        - Math.tan(toRadians(bPoint.phi)) * Math.cos(toRadians(aPoint.lambda));

        double lambdaR = toDegrees(Math.atan(numerator/denominator));
        double lambdaVertex = 90 + lambdaR;

        double phiVertex;
        if (aPoint.phi == 0) {
            phiVertex = toDegrees(Math.atan(Math.tan(toRadians(aPoint.phi)) / Math.sin(toRadians(aPoint.lambda - lambdaR))));
        } else {
            phiVertex = toDegrees(Math.atan(Math.tan(toRadians(bPoint.phi)) / Math.sin(toRadians(bPoint.lambda - lambdaR))));
        }

        this.setHeight1(Math.abs(phiVertex));
        this.setHeight2(180 - this.getHeight1());
        Point firstOrthodromeVertex = new Point(phiVertex, lambdaVertex);

        return firstOrthodromeVertex;
    }

    public Point calculateSecondOrthodromeVertex(Point firstOrthodromeVertex) {
        Point secondOrthodromeVertex = new Point(firstOrthodromeVertex.phi*(-1), firstOrthodromeVertex.lambda - 180);
        return secondOrthodromeVertex;
    }
}