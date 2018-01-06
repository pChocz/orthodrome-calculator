class Loxodrome extends Controller{

    //input
    private Point aPoint;
    private Point bPoint;
    private Orthodrome orthodrome;
    private CASE caseType;

    //output
    double lengthKm;
    double lengthNm;
    double orthodromeGainKm;
    double orthodromeGainNm;

    Loxodrome(Point aPoint, Point bPoint, Orthodrome orthodrome, CASE caseType) {
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.orthodrome = orthodrome;
        this.caseType = caseType;

        this.lengthNm = calculateLoxodrome();
        this.lengthKm = this.lengthNm * 1.852;

        this.orthodromeGainNm = this.lengthNm - orthodrome.distanceNm;
        this.orthodromeGainKm = this.orthodromeGainNm * 1.852;
    }

    private double calculateLoxodrome() {
        double deltaPhiRadians = bPoint.phiRadians - aPoint.phiRadians;
        double deltaLambdaRadians = bPoint.lambdaRadians - aPoint.lambdaRadians;

        double deltaPsi = Math.log(Math.tan(Math.PI / 4 + bPoint.phiRadians / 2) / Math.tan(Math.PI / 4 + aPoint.phiRadians / 2));
        double q = Math.abs(deltaPsi) > 10e-12 ? deltaPhiRadians / deltaPsi : Math.cos(aPoint.phiRadians);

        if (Math.abs(deltaLambdaRadians) > Math.PI) {
            deltaLambdaRadians = deltaLambdaRadians > 0 ? -(2 * Math.PI - deltaLambdaRadians) : (2 * Math.PI + deltaLambdaRadians);
        }

        if (caseType == CASE.GENERAL) {
            return Math.sqrt(deltaPhiRadians * deltaPhiRadians + q * q * deltaLambdaRadians * deltaLambdaRadians) * 3440;
        } else {
            return orthodrome.distanceNm;
        }
    }

}
