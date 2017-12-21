import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class CalculationTest extends Converter{

    @Test
    void calculationScenario_example1() {
        //input
        Point aPoint = new Point(
                "N", 10, 0,
                "E", 50, 0);
        Point bPoint = new Point(
                "N", 20, 0,
                "E", 170, 0);

        //expected results
        Point expectedOrthodromeVertex = new Point(
                "N", 28, 51.4,
                "E", 121, 20.3);
        double expectedOrthodrome = 6827.14;
        double expectedInitialCourse = dmToDdDouble(62, 47.4);
        double expectedFinalCourse = dmToDdDouble(111, 14.7);

        Controller controller = new Controller();
        AllResults allResults = controller.calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(),0.1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }


    @Test
    void calculationScenario_example2() {
        //input
        Point aPoint = new Point(
                "N", 50, 0,
                "W", 170, 0);
        Point bPoint = new Point(
                "S", 10, 0,
                "W", 100, 0);

        //expected results
        Point expectedOrthodromeVertex = new Point(
                "N", 53, 21,
                "E", 162, 27.7);
        double expectedOrthodrome = 5112.67;
        double expectedInitialCourse = dmToDdDouble(111, 46.4);
        double expectedFinalCourse = dmToDdDouble(142, 41.4);

        Controller controller = new Controller();
        AllResults allResults = controller.calculationProcedure(aPoint, bPoint);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(),0.1);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, 0.1);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, 0.1);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, 0.1);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, 0.1);
    }


}