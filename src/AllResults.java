class AllResults {

    Point aPoint;
    Point bPoint;
    Orthodrome orthodrome;
    double loxodrome;
    SphericalTriangle sphericalTriangle;
    CourseAngles courseAngles;
    Point firstOrthodromeVertex;
    Point secondOrthodromeVertex;

    /**
     * It summarizes all results needed for printing out
     * the results for easier access by other methods
     *
     */
    AllResults(Orthodrome orthodrome, double loxodrome, CourseAngles courseAngles, Point firstOrthodromeVertex, Point secondOrthodromeVertex) {
        this.orthodrome = orthodrome;
        this.loxodrome = loxodrome;
        this.courseAngles = courseAngles;
        this.firstOrthodromeVertex = firstOrthodromeVertex;
        this.secondOrthodromeVertex = secondOrthodromeVertex;

        this.aPoint = courseAngles.aPoint;
        this.bPoint = courseAngles.bPoint;
        this.sphericalTriangle = courseAngles.sphericalTriangle;
    }

}
