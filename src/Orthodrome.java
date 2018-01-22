class Orthodrome extends Controller {

    //input
    private SphericalTriangle sphericalTriangle;
    private Point aPoint;
    private Point bPoint;
    private CASE caseType;

    //output
    double distanceAngles;
    double distanceNm;
    double distanceKm;
    double height1;
    double height2;
    Point firstOrthodromeVertex;
    Point secondOrthodromeVertex;


    Orthodrome(SphericalTriangle sphericalTriangle, Point aPoint, Point bPoint, CASE caseType) {
        this.sphericalTriangle = sphericalTriangle;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.caseType = caseType;

        this.distanceAngles = sphericalTriangle.d;
        this.distanceNm = this.distanceAngles * 60;
        this.distanceKm = this.distanceNm * 1.852;

        this.firstOrthodromeVertex = calculateFirstOrthodromeVertex();
        this.secondOrthodromeVertex = calculateSecondOrthodromeVertex();
    }

    Point calculateFirstOrthodromeVertex() {
        double numerator =
                Math.tan(aPoint.phiRadians) * Math.sin(bPoint.lambdaRadians)
                        - Math.tan(bPoint.phiRadians) * Math.sin(aPoint.lambdaRadians);

        double denominator =
                Math.tan(aPoint.phiRadians) * Math.cos(bPoint.lambdaRadians)
                        - Math.tan(bPoint.phiRadians) * Math.cos(aPoint.lambdaRadians);

        double lambdaR = toDegrees(Math.atan(numerator/denominator));
        double lambdaVertex = 90 + lambdaR;

        double phiVertex;
        if (bPoint.phi == 0) {
            phiVertex = toDegrees(Math.atan(Math.tan(aPoint.phiRadians) / Math.sin(toRadians(aPoint.lambda - lambdaR))));
        } else {
            phiVertex = toDegrees(Math.atan(Math.tan(bPoint.phiRadians) / Math.sin(toRadians(bPoint.lambda - lambdaR))));
        }

        this.height1 = 90 - Math.abs(phiVertex);
        this.height2 = 180 - this.height1;

        Point firstOrthodromeVertex = new Point(phiVertex, lambdaVertex);

        if (!caseType.equals(Controller.CASE.GENERAL)) {
            verifySpecialCasesHeight();
            firstOrthodromeVertex = verifySpecialCasesFirstOrthodromeVertex();
        }

        return firstOrthodromeVertex;
    }

    private void verifySpecialCasesHeight() {
        if (caseType == Controller.CASE.EQUATOR_SAIL) {
            this.height1 = 90;
            this.height2 = 999;

        } else {
            this.height1 = 999;
            this.height2 = 999;

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

    private Point calculateSecondOrthodromeVertex() {
        return new Point(this.firstOrthodromeVertex.phi*(-1), this.firstOrthodromeVertex.lambda - 180);
    }

}










