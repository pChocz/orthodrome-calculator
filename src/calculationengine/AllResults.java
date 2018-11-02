package calculationengine;

import gui.Controller;

public class AllResults extends Controller {

    public Point aPoint;
    public Point bPoint;
    public Orthodrome orthodrome;
    public Loxodrome loxodrome;
    public SphericalTriangle sphericalTriangle;
    public BearingAngles bearingAngles;
    public Point firstOrthodromeVertex;
    public Point secondOrthodromeVertex;
    public Controller.CASE caseType;

    /**
     * It summarizes all results needed for printing out
     * the results for easier access by other methods
     *
     */
    public AllResults(Orthodrome orthodrome, Loxodrome loxodrome, BearingAngles bearingAngles) {
        this.setOrthodrome(orthodrome);
        this.setLoxodrome(loxodrome);
        this.setBearingAngles(bearingAngles);
        this.setFirstOrthodromeVertex(orthodrome.getFirstOrthodromeVertex());
        this.setSecondOrthodromeVertex(orthodrome.getSecondOrthodromeVertex());
        this.setaPoint(bearingAngles.getaPoint());
        this.setbPoint(bearingAngles.getbPoint());
        this.setSphericalTriangle(bearingAngles.getSphericalTriangle());
        this.setCaseType(verifySpecialCases(getaPoint(), getbPoint()));
    }

    public AllResults(Point aPoint, Point bPoint) {
        this.setCaseType(verifySpecialCases(aPoint, bPoint));
        this.setSphericalTriangle(new SphericalTriangle(aPoint, bPoint, getCaseType()));
        this.setOrthodrome(new Orthodrome(getSphericalTriangle(), aPoint, bPoint, getCaseType()));
        this.setFirstOrthodromeVertex(this.getOrthodrome().getFirstOrthodromeVertex());
        this.setSecondOrthodromeVertex(this.getOrthodrome().getSecondOrthodromeVertex());
        this.setBearingAngles(new BearingAngles(getSphericalTriangle(), aPoint, bPoint, getCaseType()));
        this.setLoxodrome(new Loxodrome(aPoint, bPoint, getOrthodrome(), getCaseType()));
    }

    private Point getaPoint() {
        return aPoint;
    }

    private void setaPoint(Point aPoint) {
        this.aPoint = aPoint;
    }

    private Point getbPoint() {
        return bPoint;
    }

    private void setbPoint(Point bPoint) {
        this.bPoint = bPoint;
    }

    private Orthodrome getOrthodrome() {
        return orthodrome;
    }

    private void setOrthodrome(Orthodrome orthodrome) {
        this.orthodrome = orthodrome;
    }

    private void setLoxodrome(Loxodrome loxodrome) {
        this.loxodrome = loxodrome;
    }

    private SphericalTriangle getSphericalTriangle() {
        return sphericalTriangle;
    }

    private void setSphericalTriangle(SphericalTriangle sphericalTriangle) {
        this.sphericalTriangle = sphericalTriangle;
    }

    private void setBearingAngles(BearingAngles bearingAngles) {
        this.bearingAngles = bearingAngles;
    }

    private void setFirstOrthodromeVertex(Point firstOrthodromeVertex) {
        this.firstOrthodromeVertex = firstOrthodromeVertex;
    }

    private void setSecondOrthodromeVertex(Point secondOrthodromeVertex) {
        this.secondOrthodromeVertex = secondOrthodromeVertex;
    }

    private CASE getCaseType() {
        return caseType;
    }

    private void setCaseType(CASE caseType) {
        this.caseType = caseType;
    }
}
