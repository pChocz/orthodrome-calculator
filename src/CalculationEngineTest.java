import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculationEngineTest extends Controller {

    private static double DELTA_LENGHT = 1;
    private static double DELTA_ANGLE = 0.1;

        /* GENERAL CASES */

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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4a
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4h
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4g
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4f
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4e
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


    /**
     *  trygonometria sferyczna, Krzysztof Piskórz 1999
     *  zad. 4d
     *  str. 55
     */
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
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }




    /**
     *  skrypt dla studentów
     *  zad. 4.31
     *  str. 81
     */
    @Test
    public void generalExample_09() {
        //input
        Point aPoint = new Point(
                "N", 21, 24,
                "E", 42, 46);
        Point bPoint = new Point(
                "N", 10, 30,
                "E", 170, 58);

        //expected results
        double expectedOrthodrome = 7198.56;
        double expectedInitialCourse = dmToDdDouble(63, 6.68);
        double expectedFinalCourse = dmToDdDouble(122, 21.8);
        Point expectedOrthodromeVertex = new Point(
                "N", 33, 50.87,
                "E", 97, 00.57);

        AllResults allResults = calculationProcedure(aPoint, bPoint);
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }

    @Test
    public void generalExample_allParameters_01() {
        //input
        Point aPoint = new Point(
                "N", 26, 46,
                "E", 22, 18);
        Point bPoint = new Point(
                "S", 32, 0,
                "E", 175, 50);

        //expected results
        double expected_a = dmToDdDouble(122, 0);
        double expected_b = dmToDdDouble(63, 14);
        double expected_C = dmToDdDouble(153, 32);
        double expected_A = dmToDdDouble(109, 8.9);
        double expected_B = dmToDdDouble(95, 58.3);
        double expected_h1 = dmToDdDouble(57, 30.4);
        double expected_h2 = dmToDdDouble(122, 29.6);
        double expectedOrthodrome = 9384.96;
        double expectedInitialCourse = dmToDdDouble(109, 8.9);
        double expectedFinalCourse = dmToDdDouble(84, 1.7);
        Point expectedOrthodromeVertex = new Point(
                "S", 32, 29.6,
                "E", 164, 40);

        AllResults allResults = calculationProcedure(aPoint, bPoint);
        printAllResultsForDebug(allResults);

        assertEquals(expected_a, allResults.sphericalTriangle.a, DELTA_ANGLE);
        assertEquals(expected_b, allResults.sphericalTriangle.b, DELTA_ANGLE);
        assertEquals(expected_C, allResults.sphericalTriangle.C, DELTA_ANGLE);
        assertEquals(expected_A, allResults.sphericalTriangle.A, DELTA_ANGLE);
        assertEquals(expected_B, allResults.sphericalTriangle.B, DELTA_ANGLE);
        assertEquals(expected_h1, allResults.orthodrome.getHeight1(), DELTA_ANGLE);
        assertEquals(expected_h2, allResults.orthodrome.getHeight2(), DELTA_ANGLE);
        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
    }


//    /**
//     *  skrypt dla studentów
//     *  zad. 4.32
//     *  str. 81
//     */
//    //TODO: verify - it fails all asserts - it seems like answer in the book is not valid
//    @Test
//    public void generalExample_11() {
//        //input
//        Point aPoint = new Point(
//                "N", 0, 0,
//                "E", 60, 46);
//        Point bPoint = new Point(
//                "S", 23, 52,
//                "E", 130, 10);
//
//        //expected results
//        double expectedOrthodrome = 9232.93;
//        double expectedInitialCourse = dmToDdDouble(156, 47.77);
//        double expectedFinalCourse = dmToDdDouble(25, 31.28);
//        Point expectedOrthodromeVertex = new Point(
//                "S", 66, 47.77,
//                "E", 150, 46);
//
//        AllResults allResults = calculationProcedure(aPoint, bPoint);
//        printAllResultsForDebug(allResults);
//
//        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
//        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
//        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
//        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
//        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
//    }


    /* SPECIAL CASES */ //TODO: prepare more asserts for special cases

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
        printAllResultsForDebug(allResults);

        assertEquals(CASE.MERIDIAN_SAIL, caseType);
        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
    }


    @Test
    public void specialExample_meridianSail_03() {
        //input
        Point aPoint = new Point(
                "N", 90, 0,
                "W", 0, 0);
        Point bPoint = new Point(
                "S", 15, 10,
                "E", 100, 50);

        //expected results
        double expectedOrthodrome = 6310;
        double expectedInitialCourse = dmToDdDouble(180, 0);
        double expectedFinalCourse = dmToDdDouble(180, 0);
        Point expectedOrthodromeVertex = new Point(
                "N", 90, 0,
                "E", 999, 0);

        AllResults allResults = calculationProcedure(aPoint, bPoint);
        printAllResultsForDebug(allResults);

        assertEquals(expectedOrthodrome, allResults.orthodrome.getDistanceNm(), DELTA_LENGHT);
        assertEquals(expectedInitialCourse, allResults.courseAngles.initialCourse, DELTA_ANGLE);
        assertEquals(expectedFinalCourse, allResults.courseAngles.finalCourse, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.phi, allResults.firstOrthodromeVertex.phi, DELTA_ANGLE);
        assertEquals(expectedOrthodromeVertex.lambda, allResults.firstOrthodromeVertex.lambda, DELTA_ANGLE);
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



    private void printAllResultsForDebug(AllResults allResults) {
        System.out.println("case = " + caseType);
        System.out.println("A = " + allResults.sphericalTriangle.A);
        System.out.println("B = " + allResults.sphericalTriangle.B);
        System.out.println("C = " + allResults.sphericalTriangle.C);
        System.out.println("a = " + allResults.sphericalTriangle.a);
        System.out.println("b = " + allResults.sphericalTriangle.b);
        System.out.println("d = " + allResults.sphericalTriangle.d + " = " + allResults.orthodrome.getDistanceNmString());
        System.out.println("α = " + allResults.courseAngles.initialCourse);
        System.out.println("β = " + allResults.courseAngles.finalCourse);
        System.out.println(allResults.courseAngles.direction);
        System.out.println("W1.phi    = " + allResults.orthodrome.calculateFirstOrthodromeVertex().phi +
                " =  " +
                ddToDmString("lat", allResults.orthodrome.calculateFirstOrthodromeVertex().latCalculated) +
                " " + allResults.orthodrome.calculateFirstOrthodromeVertex().latSide);
        System.out.println("W2.phi    = " + allResults.orthodrome.calculateFirstOrthodromeVertex().lambda +
                " = " +
                ddToDmString("long", allResults.orthodrome.calculateFirstOrthodromeVertex().longCalculated) +
                " " + allResults.orthodrome.calculateFirstOrthodromeVertex().longSide);
    }


}