class AllResults {

    Point aPoint;
    Point bPoint;
    Orthodrome orthodrome;
    Loxodrome loxodrome;
    SphericalTriangle sphericalTriangle;
    BearingAngles bearingAngles;
    Point firstOrthodromeVertex;
    Point secondOrthodromeVertex;

    /**
     * It summarizes all results needed for printing out
     * the results for easier access by other methods
     *
     */
    AllResults(Orthodrome orthodrome, Loxodrome loxodrome, BearingAngles bearingAngles, Point firstOrthodromeVertex, Point secondOrthodromeVertex) {
        this.orthodrome = orthodrome;
        this.loxodrome = loxodrome;
        this.bearingAngles = bearingAngles;
        this.firstOrthodromeVertex = firstOrthodromeVertex;
        this.secondOrthodromeVertex = secondOrthodromeVertex;

        this.aPoint = bearingAngles.aPoint;
        this.bPoint = bearingAngles.bPoint;
        this.sphericalTriangle = bearingAngles.sphericalTriangle;
    }

}
