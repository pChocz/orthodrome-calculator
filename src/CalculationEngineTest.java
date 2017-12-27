import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculationEngineTest extends Controller {


    @Test
    public void generalExample_01() {
        //input
        Point aPoint = new Point(
                "N", 10, 0,
                "E", 50, 0);
        Point bPoint = new Point(
                "N", 20, 0,
                "E", 170, 0);

        //expected results
        double expectedOrthodrome = 6827.14;
        double expectedInitialCourse = dmToDdDouble(62, 47.4);
        double expectedFinalCourse = dmToDdDouble(111, 14.7);
        Point expectedOrthodromeVertex = new Point(
                "N", 28, 51.4,
                "E", 121, 20.3);

        Controller controller = new Controller();
        AllResults allResults = controller.calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 0.1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }


    @Test
    public void generalExample_02() {
        //input
        Point aPoint = new Point(
                "N", 50, 0,
                "W", 170, 0);
        Point bPoint = new Point(
                "S", 10, 0,
                "W", 100, 0);

        //expected results
        double expectedOrthodrome = 5112.67;
        double expectedInitialCourse = dmToDdDouble(111, 46.4);
        double expectedFinalCourse = dmToDdDouble(142, 41.4);
        Point expectedOrthodromeVertex = new Point(
                "N", 53, 21,
                "E", 162, 27.7);

        Controller controller = new Controller();
        AllResults allResults = controller.calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 0.1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }


    @Test
    public void specialExample_meridianSail_01() {
        //input
        Point aPoint = new Point(
                "N", 50, 0,
                "W", 170, 0);
        Point bPoint = new Point(
                "S", 10, 0,
                "W", 170, 0);

        calculationProcedure(aPoint, bPoint);
        assertEquals(CASE.MERIDIAN_SAIL, caseType);
    }


    @Test
    public void specialExample_equatorSail_01() {
        //input
        Point aPoint = new Point(
                "N", 0, 0,
                "W", 90, 0);
        Point bPoint = new Point(
                "N", 0, 0,
                "W", 170, 0);

        calculationProcedure(aPoint, bPoint);
        assertEquals(CASE.EQUATOR_SAIL, caseType);
    }


    @Test
    public void specialExample_samePoint_01() {
        //input
        Point aPoint = new Point(
                "N", 20, 0,
                "W", 90, 0);
        Point bPoint = new Point(
                "N", 20, 0,
                "W", 90, 0);

        calculationProcedure(aPoint, bPoint);
        assertEquals(CASE.SAME_POINT, caseType);
    }


    @Test
    public void specialExample_oppositePoints_01() {
        //input
        Point aPoint = new Point(
                "N", 20, 0,
                "E", 120, 0);
        Point bPoint = new Point(
                "S", 20, 0,
                "W", 60, 0);

        calculationProcedure(aPoint, bPoint);
        assertEquals(CASE.OPPOSITE_POINTS, caseType);
    }


}