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
        a = 90 - bPoint.phi;
        b = 90 - aPoint.phi;

        C = calculateC();

        A = calculateA();
        B = calculateB();
        d = calculateOrthodrome();

        return true;
    }

    public double calculateC() {
        double C = Math.abs(bPoint.lambda - aPoint.lambda);

        return (C <= 180) ? (C) : (360 - C);
    }

    public double calculateA() {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        double orthodromeRadians = Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians));
        return (toDegrees(Math.acos((Math.cos(aRadians) - Math.cos(bRadians) * Math.cos(orthodromeRadians)) / (Math.sin(bRadians) * Math.sin(orthodromeRadians)))));
    }

    public double calculateB() {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        double orthodromeRadians = Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians));
        return toDegrees(Math.acos((Math.cos(bRadians) - Math.cos(aRadians) * Math.cos(orthodromeRadians)) / (Math.sin(aRadians) * Math.sin(orthodromeRadians))));
    }

    public double calculateOrthodrome() {
        double aRadians = toRadians(a);
        double bRadians = toRadians(b);
        double CRadians = toRadians(C);

        return toDegrees(Math.acos(Math.cos(aRadians) * Math.cos(bRadians) + Math.sin(aRadians) * Math.sin(bRadians) * Math.cos(CRadians)));
    }

}
