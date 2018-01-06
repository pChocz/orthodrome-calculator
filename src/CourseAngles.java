class CourseAngles extends Controller{

    //input
     SphericalTriangle sphericalTriangle;
     Point aPoint;
     Point bPoint;
     private CASE caseType;

     //output
     double initialCourse;
     double finalCourse;
     String direction;


    CourseAngles(SphericalTriangle sphericalTriangle, Point aPoint, Point bPoint, CASE caseType) {
        this.sphericalTriangle = sphericalTriangle;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.caseType = caseType;

        calculateCourseAngles();
    }

    private void calculateCourseAngles() {
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
            this.initialCourse = this.sphericalTriangle.A;
            this.finalCourse = 180 - this.sphericalTriangle.B;
        } else {
            this.initialCourse = 360 - this.sphericalTriangle.A;
            this.finalCourse = 180 + this.sphericalTriangle.B;
        }

        verifySpecialCasesCourseAngles();
    }

    private void verifySpecialCasesCourseAngles() {
        if (caseType == Controller.CASE.EQUATOR_SAIL) {
            if (aPoint.lambda > bPoint.lambda) {
                this.initialCourse = 270;
                this.finalCourse = 270;

            } else {
                this.initialCourse = 90;
                this.finalCourse = 90;
            }

        } else if (caseType == Controller.CASE.MERIDIAN_SAIL) {
            if (aPoint.phi > bPoint.phi) {
                this.initialCourse = 180;
                this.finalCourse = 180;

            } else {
                this.initialCourse = 0;
                this.finalCourse = 0;
            }

        } else if (caseType == Controller.CASE.OPPOSITE_POINTS) {
            this.initialCourse = 999;
            this.finalCourse = 999;
        }
    }

}

