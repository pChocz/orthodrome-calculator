class AllResults extends Controller{

    Point aPoint;
    Point bPoint;
    Orthodrome orthodrome;
    Loxodrome loxodrome;
    SphericalTriangle sphericalTriangle;
    BearingAngles bearingAngles;
    Point firstOrthodromeVertex;
    Point secondOrthodromeVertex;
    CASE caseType;

    /**
     * It summarizes all results needed for printing out
     * the results for easier access by other methods
     *
     */
    AllResults(Orthodrome orthodrome, Loxodrome loxodrome, BearingAngles bearingAngles) {
        this.orthodrome = orthodrome;
        this.loxodrome = loxodrome;
        this.bearingAngles = bearingAngles;
        this.firstOrthodromeVertex = orthodrome.firstOrthodromeVertex;
        this.secondOrthodromeVertex = orthodrome.secondOrthodromeVertex;
        this.aPoint = bearingAngles.aPoint;
        this.bPoint = bearingAngles.bPoint;
        this.sphericalTriangle = bearingAngles.sphericalTriangle;
        this.caseType = verifySpecialCases(aPoint, bPoint);
    }

    AllResults(Point aPoint, Point bPoint) {
        this.caseType = verifySpecialCases(aPoint, bPoint);
        this.sphericalTriangle = new SphericalTriangle(aPoint, bPoint, caseType);
        this.orthodrome = new Orthodrome(sphericalTriangle, aPoint, bPoint, caseType);
        this.firstOrthodromeVertex = this.orthodrome.firstOrthodromeVertex;
        this.secondOrthodromeVertex = this.orthodrome.secondOrthodromeVertex;
        this.bearingAngles = new BearingAngles(sphericalTriangle, aPoint, bPoint, caseType);
        this.loxodrome = new Loxodrome(aPoint, bPoint, orthodrome, caseType);
    }

}
