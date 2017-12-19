package sample;

public class CourseAngles {

    //input
     SphericalTriangle sphericalTriangle;
     Point aPoint;
     Point bPoint;

    //output
     double initialCourse;
     double finalCourse;
     String direction;

    public CourseAngles(SphericalTriangle sphericalTriangle, Point aPoint, Point bPoint) {
        this.sphericalTriangle = sphericalTriangle;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
    }

    public double getInitialCourse() {
        return initialCourse;
    }

    public double getFinalCourse() {
        return finalCourse;
    }

    public String getDirection() {
        return direction;
    }

    public void calculateCourseAngles() {
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

        if (direction == "W->E") {
            this.initialCourse = this.sphericalTriangle.A;
            this.finalCourse = 180 - this.sphericalTriangle.B;
        } else {
            this.initialCourse = 360 - this.sphericalTriangle.A;
            this.finalCourse = 180 + this.sphericalTriangle.B;
        }

    }



}
