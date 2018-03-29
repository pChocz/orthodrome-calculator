package calculationengine;

import gui.Controller;

import static util.Converter.toDegrees;

public class Loxodrome extends Controller {

    //input
    private Point aPoint;
    private Point bPoint;
    private Orthodrome orthodrome;
    private Controller.CASE caseType;

    //output
    public double lengthKm;
    public double lengthNm;
    public double orthodromeGainKm;
    public double orthodromeGainNm;
    public double bearing;

    public Loxodrome(Point aPoint, Point bPoint, Orthodrome orthodrome, Controller.CASE caseType) {
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.orthodrome = orthodrome;
        this.caseType = caseType;

        this.lengthNm = calculateLoxodrome();
        this.lengthKm = this.lengthNm * 1.852;

        this.orthodromeGainNm = this.lengthNm - orthodrome.getDistanceNm();
        this.orthodromeGainKm = this.orthodromeGainNm * 1.852;

        this.bearing = calculateBearing();
    }

    private double calculateLoxodrome() {
        double deltaPhiRadians = bPoint.getPhiRadians() - aPoint.getPhiRadians();
        double deltaLambdaRadians = bPoint.getLambdaRadians() - aPoint.getLambdaRadians();

        double deltaPsiRadians = Math.log(Math.tan(Math.PI/4 + bPoint.getPhiRadians()/2)/Math.tan(Math.PI/4 + aPoint.getPhiRadians()/2));
        double q = Math.abs(deltaPsiRadians) > 10e-12 ? deltaPhiRadians/deltaPsiRadians : Math.cos(aPoint.getPhiRadians());

        if (Math.abs(deltaLambdaRadians) > Math.PI) {
            deltaLambdaRadians = deltaLambdaRadians > 0 ? -(2*Math.PI - deltaLambdaRadians) : (2*Math.PI + deltaLambdaRadians);
        }

        if (caseType == Controller.CASE.GENERAL) {
            return Math.sqrt(deltaPhiRadians*deltaPhiRadians + q*q*deltaLambdaRadians*deltaLambdaRadians)*3440;
        } else {
            return orthodrome.getDistanceNm();
        }
    }

    private double calculateBearing() {
        double bearing;
        double deltaPsiRadians = Math.log(Math.tan(Math.PI/4 + bPoint.getPhiRadians()/2)/Math.tan(Math.PI/4 + aPoint.getPhiRadians()/2));
        double deltaLambdaRadians = bPoint.getLambdaRadians() - aPoint.getLambdaRadians();

        if (Math.abs(deltaLambdaRadians) > Math.PI) {
            deltaLambdaRadians = deltaLambdaRadians > 0 ? -(2*Math.PI - deltaLambdaRadians) : (2*Math.PI + deltaLambdaRadians);
        }
        bearing = toDegrees(Math.atan2(deltaLambdaRadians, deltaPsiRadians));
        bearing = (bearing<0) ? (360+bearing) : bearing;

        return bearing;
    }

}
