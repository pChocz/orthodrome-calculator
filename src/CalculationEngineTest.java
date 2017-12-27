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

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
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

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }

    @Test
    public void generalExample_03() {
        //input
        Point aPoint = new Point(
                "N", 10, 24,
                "E", 49, 46);
        Point bPoint = new Point(
                "N", 15, 30,
                "E", 176, 58);

        //expected results
        double expectedOrthodrome = 7299;
        double expectedInitialCourse = dmToDdDouble(64, 23);
        double expectedFinalCourse = dmToDdDouble(113, 1.2);
        Point expectedOrthodromeVertex = new Point(
                "N", 27, 30.7,
                "E", 119, 8.2);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }


    @Test
    public void generalExample_04() {
        //input
        Point aPoint = new Point(
                "S", 42, 16,
                "W", 95, 40);
        Point bPoint = new Point(
                "N", 0, 0,
                "W", 132, 24);

        //expected results
        double expectedOrthodrome = 3217.5;
        double expectedInitialCourse = dmToDdDouble(312, 0.16);
        double expectedFinalCourse = dmToDdDouble(326, 39.2);
        Point expectedOrthodromeVertex = new Point(
                "N", 56, 39.2,
                "E", 137, 36);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }

    //TODO: failing for 'orthodromeVertex.phi' assert when initial phi of one point is 0
    @Test
    public void generalExample_05() {
        //input
        Point aPoint = new Point(
                "N", 0, 0,
                "E", 74, 46);
        Point bPoint = new Point(
                "S", 24, 52,
                "W", 120, 10);

        //expected results
        double expectedOrthodrome = 9074.5;
        double expectedInitialCourse = dmToDdDouble(150, 55.5);
        double expectedFinalCourse = dmToDdDouble(32, 23.1);
        Point expectedOrthodromeVertex = new Point(
                "N", 60, 55.5,
                "E", 164, 46);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }

    //TODO: check if it returns proper value - different answer comparing to book
    @Test
    public void generalExample_06() {
        //input
        Point aPoint = new Point(
                "S", 25, 10,
                "W", 101, 22);
        Point bPoint = new Point(
                "N", 32, 6,
                "W", 162, 8);

        //expected results
        double expectedOrthodrome = 4887.9;
        double expectedInitialCourse = dmToDdDouble(311, 37.5);
        double expectedFinalCourse = dmToDdDouble(306, 59.9);
        Point expectedOrthodromeVertex = new Point(
                "S", 47, 25.5,
                "E", 36, 56.4);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.2);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.2);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.2);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.2);
    }

    @Test
    public void generalExample_07() {
        //input
        Point aPoint = new Point(
                "S", 30, 0,
                "E", 60, 0);
        Point bPoint = new Point(
                "S", 39, 50,
                "E", 140, 10);

        //expected results
        double expectedOrthodrome = 3857.3;
        double expectedInitialCourse = dmToDdDouble(122, 53);
        double expectedFinalCourse = dmToDdDouble(71, 16.6);
        Point expectedOrthodromeVertex = new Point(
                "S", 43, 20.6,
                "E", 112, 17);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.2);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.2);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.2);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.2);
    }

    @Test
    public void generalExample_08() {
        //input
        Point aPoint = new Point(
                "N", 51, 26,
                "W", 172, 44);
        Point bPoint = new Point(
                "S", 15, 10,
                "W", 100, 50);

        //expected results
        double expectedOrthodrome = 5460.6;
        double expectedInitialCourse = dmToDdDouble(113, 25.8);
        double expectedFinalCourse = dmToDdDouble(143, 39.2);
        Point expectedOrthodromeVertex = new Point(
                "N", 55, 6.5,
                "E", 158, 16.1);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.2);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.2);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.2);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.2);
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
    public void specialExample_meridianSail_02() {
        //input
        Point aPoint = new Point(
                "N", 90, 0,
                "W", 60, 0);
        Point bPoint = new Point(
                "S", 90, 0,
                "W", 90, 0);

        double expectedOrthodrome = 10800;
        double expectedInitialCourse = dmToDdDouble(180, 0);
        double expectedFinalCourse = dmToDdDouble(180, 0);
        Point expectedOrthodromeVertex = new Point(
                "N", 90, 0,
                "E", 0, 0);

        AllResults allResults = calculationProcedure(aPoint, bPoint);

        assertEquals(CASE.MERIDIAN_SAIL, caseType);
        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), 1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.2);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.2);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.2);
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
    public void specialExample_samePoint_02() {
        //input
        Point aPoint = new Point(
                "N", 90, 0,
                "W", 45, 0);
        Point bPoint = new Point(
                "N", 90, 0,
                "E", 92, 0);

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