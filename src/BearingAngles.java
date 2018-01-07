class BearingAngles extends Controller{

    //input
     SphericalTriangle sphericalTriangle;
     Point aPoint;
     Point bPoint;
     private CASE caseType;

     //output
     double initialBearing;
     double finalBearing;
     String direction;


    BearingAngles(SphericalTriangle sphericalTriangle, Point aPoint, Point bPoint, CASE caseType) {
        this.sphericalTriangle = sphericalTriangle;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.caseType = caseType;

        calculateBearingAngles();
    }

    private void calculateBearingAngles() {
        double sumPhi = this.bPoint.phi + this.aPoint.phi;
        double deltaLambda = this.bPoint.lambda - this.aPoint.lambda;

        if ((deltaLambda > 0 && deltaLambda < 180) || (deltaLambda < -180 && deltaLambda > -360)) {
            this.direction = "W->E";
        } else if ((deltaLambda < 0 && deltaLambda > -180) || (deltaLambda > 180 && deltaLambda < 360)) {
            this.direction = "E->W";
        } else if ((deltaLambda == 180 || deltaLambda == -180) && (sumPhi == 0)) {
            this.direction = "neutral";
        } else {
            this.direction = "WRONG";
        }

        if (direction.equals("W->E")) {
            this.initialBearing = this.sphericalTriangle.A;
            this.finalBearing = 180 - this.sphericalTriangle.B;
        } else {
            this.initialBearing = 360 - this.sphericalTriangle.A;
            this.finalBearing = 180 + this.sphericalTriangle.B;
        }

        verifySpecialCasesBearingAngles();
    }

    private void verifySpecialCasesBearingAngles() {
        if (caseType == Controller.CASE.EQUATOR_SAIL) {
            if (aPoint.lambda > bPoint.lambda) {
                this.initialBearing = 270;
                this.finalBearing = 270;

            } else {
                this.initialBearing = 90;
                this.finalBearing = 90;
            }

        } else if (caseType == Controller.CASE.MERIDIAN_SAIL) {
            if (aPoint.phi > bPoint.phi) {
                this.initialBearing = 180;
                this.finalBearing = 180;

            } else {
                this.initialBearing = 0;
                this.finalBearing = 0;
            }

        } else if (caseType == Controller.CASE.OPPOSITE_POINTS) {
            this.initialBearing = 999;
            this.finalBearing = 999;
        }
    }

}

