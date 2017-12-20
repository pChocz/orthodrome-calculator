public class SphericalTriangle extends Converter {

    //input
    private Point aPoint;
    private Point bPoint;

    //output
    double a;
    double b;
    double d;
    double A;
    double B;
    double C;

    public SphericalTriangle(Point startPoint, Point endPoint) {
        this.aPoint = startPoint;
        this.bPoint = endPoint;
    }

    public boolean calculateSphericalTriangle() {
        this.a = 90 - this.bPoint.phi;
        this.b = 90 - this.aPoint.phi;

        this.C = calculateC(this.aPoint, this.bPoint);

        this.A = calculateA(a, b, C);
        this.B = calculateB(a, b, C);
        this.d = calculateOrthodrome(a, b, C);

        return true;
    }

    public double calculateC(Point aPoint, Point bPoint) {
        double C = Math.abs(bPoint.lambda - aPoint.lambda);
        if (C <= 180) {
            return C;
        } else {
            return 360 - C;
        }
    }

    public double calculateA(double a, double b, double C) {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        double orthodromeRadians = Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians));
        return (toDegrees(Math.acos((Math.cos(aRadians) - Math.cos(bRadians) * Math.cos(orthodromeRadians)) / (Math.sin(bRadians) * Math.sin(orthodromeRadians)))));
    }

    public double calculateB(double a, double b, double C) {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        double orthodromeRadians = Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians));
        return toDegrees(Math.acos((Math.cos(bRadians) - Math.cos(aRadians) * Math.cos(orthodromeRadians)) / (Math.sin(aRadians) * Math.sin(orthodromeRadians))));
    }

    public double calculateOrthodrome(double a, double b, double C) {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        return toDegrees(Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians)));
    }

}
