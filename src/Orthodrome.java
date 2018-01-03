public class Orthodrome extends Converter {

    //input
    private SphericalTriangle sphericalTriangle;
    Controller.CASE caseType;
    Point aPoint;
    Point bPoint;

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


    public Orthodrome(SphericalTriangle sphericalTriangle, Controller.CASE caseType, Point aPoint, Point bPoint) {
        this.sphericalTriangle = sphericalTriangle;
        this.distance = calculateDistance(sphericalTriangle);
        this.caseType = caseType;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
    }

    private double calculateDistance(SphericalTriangle sphericalTriangle) {
        return sphericalTriangle.d;
    }

    public double getDistance() {
        return distance;
    }

    public String getDistanceNmString() {
        return String.valueOf(String.format("%.2f", this.distance * 60)) + " Mm";
    }

    public double getDistanceNm() {
        return this.distance * 60;
    }

    public Point calculateFirstOrthodromeVertex() {
        double numerator =
                Math.tan(toRadians(aPoint.phi)) * Math.sin(toRadians(bPoint.lambda))
                        - Math.tan(toRadians(bPoint.phi)) * Math.sin(toRadians(aPoint.lambda));

        double denominator =
                Math.tan(toRadians(aPoint.phi)) * Math.cos(toRadians(bPoint.lambda))
                        - Math.tan(toRadians(bPoint.phi)) * Math.cos(toRadians(aPoint.lambda));

        double lambdaR = toDegrees(Math.atan(numerator/denominator));
        double lambdaVertex = 90 + lambdaR;

        double phiVertex;
        if (bPoint.phi == 0) {
            phiVertex = toDegrees(Math.atan(Math.tan(toRadians(aPoint.phi)) / Math.sin(toRadians(aPoint.lambda - lambdaR))));
        } else {
            phiVertex = toDegrees(Math.atan(Math.tan(toRadians(bPoint.phi)) / Math.sin(toRadians(bPoint.lambda - lambdaR))));
        }

        this.setHeight1(90 - Math.abs(phiVertex));
        this.setHeight2(180 - this.getHeight1());



        Point firstOrthodromeVertex = new Point(phiVertex, lambdaVertex);

        if (!caseType.equals(Controller.CASE.GENERAL)) {
            verifySpecialCasesHeight();
            firstOrthodromeVertex = verifySpecialCasesFirstOrthodromeVertex();
        }


        return firstOrthodromeVertex;
    }

    public Point calculateSecondOrthodromeVertex(Point firstOrthodromeVertex) {
        Point secondOrthodromeVertex = new Point(firstOrthodromeVertex.phi*(-1), firstOrthodromeVertex.lambda - 180);
        return secondOrthodromeVertex;
    }

    private void verifySpecialCasesHeight() {
        if (caseType == Controller.CASE.EQUATOR_SAIL) {
            this.setHeight1(90);
            this.setHeight2(999);

        } else {
            this.setHeight1(999);
            this.setHeight2(999);

        }
    }

    private Point verifySpecialCasesFirstOrthodromeVertex() {
        if (caseType == Controller.CASE.EQUATOR_SAIL) {
            return new Point(0,999);

        } else if (caseType == Controller.CASE.MERIDIAN_SAIL) {
            return new Point(90, 999);

        } else {
            return new Point(999,999);

        }
    }


}










