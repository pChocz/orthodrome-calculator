public class AllResults {

    public Point aPoint;
    public Point bPoint;
    public Orthodrome orthodrome;
    public double loxodrome;
    public SphericalTriangle sphericalTriangle;
    public CourseAngles courseAngles;
    public Point firstOrthodromeVertex;
    public Point secondOrthodromeVertex;

    public AllResults(Orthodrome orthodrome, double loxodrome, CourseAngles courseAngles, Point firstOrthodromeVertex, Point secondOrthodromeVertex) {
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
