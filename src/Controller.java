import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller extends Converter {

    @FXML
    private Label titleLabel;
    @FXML
    private Label generalCasesLabel;
    @FXML
    private Label specialCasesLabel;
    @FXML
    private Label bottomInfoLabel;

    @FXML
    public TextField aLatSideTxt;
    @FXML
    public TextField aLongSideTxt;
    @FXML
    public TextField aLatDegTxt;
    @FXML
    public TextField aLatMinTxt;
    @FXML
    public TextField aLongDegTxt;
    @FXML
    public TextField aLongMinTxt;

    @FXML
    public TextField bLatSideTxt;
    @FXML
    public TextField bLongSideTxt;
    @FXML
    public TextField bLatDegTxt;
    @FXML
    public TextField bLatMinTxt;
    @FXML
    public TextField bLongDegTxt;
    @FXML
    public TextField bLongMinTxt;

    public List<TextField> allNumberInputFields = new ArrayList<>();

    @FXML
    private Button calculateBtn;
    @FXML
    private Button clearBtn;


    @FXML
    private TextArea primaryTextArea;
    @FXML
    private TextArea secondaryTextArea;

    @FXML
    private ChoiceBox<String> languageCb;


    public int languageCode;
    private final int PL_LANGUAGE_CODE = 0;
    private final int EN_LANGUAGE_CODE = 1;

    public enum CASE {
        SAME_POINT,
        MERIDIAN_SAIL,
        EQUATOR_SAIL,
        OPPOSITE_POINTS,
        GENERAL
    }
    public CASE caseType;

    private final String[] INPUT_PARAMETERS_STRING = {
            "Parametry wejściowe",
            "Input parameters"};

    private final String[] GENERAL_CASES_STRING = {
            "Przypadki ogólne",
            "General cases"};

    private final String[] SPECIAL_CASES_STRING = {
            "Przypadki szczególne",
            "Special cases"};

    private final String[] CALCULATE_STRING = {
            "Oblicz",
            "Calculate"};

    private final String[] CLEAR_INFO_STRING = {
            "Wyczyść/Info",
            "Clear/Info"};

    private final String[] PROGRAM_NAME = {
            "kalkulator ortodromy",
            "orthodrome calculator"};

    private final String PROGRAM_VERSION = " v1.06";


    private final String SEPARATOR_LINE = "---------------------------------------------------------";
    private final String SEPARATOR_DASH = " - ";

    private final String[] CORRECT_VALUES_STRING = {
            " ------------poprawne dane",
            " -------------correct values"};

    private final String[] GENERAL_CASE_STRING = {
            "przypadek ogólny------------",
            "general case--------------"};

    private final String[] SPECIAL_CASE_STRING = {
            "przypadek szczególny--------",
            "special case--------------"};

    private final String[] SPECIAL_CASE_OPPOSITE_STRING = {
            " ----------------punkty naprzeciwko siebie---------------",
            " ---------------------opposite points--------------------"};

    private final String[] SPECIAL_CASE_EQUATOR_SAILING_STRING = {
            "-----------------poruszamy się po równiku----------------",
            "----------------------equator sailing--------------------"};

    private final String[] SPECIAL_CASE_MERIDIAN_SAILING_STRING = {
            "----------------poruszamy się po południku---------------",
            "----------------------meridian sailing-------------------"};

    private final String[] VALUES_LAT_LONG_STRING = {
            " ----przeliczone szerokości/długości punktów A oraz B----",
            " ----calculated latitudes/longitudes of points A and B---"};

    private final String[] VALUES_SPHERICAL_TRIANGLE_STRING = {
            "--------podstawowe długości oraz kąty w trójkącie--------",
            "-----values of sides and angles in spherical triangle----"};

    private final String[] VALUE_ORTHODROME_STRING = {
            "-----------------odległość ortodromiczna-----------------",
            "-----------orthodromic (great circle) distance-----------"};

    private final String[] VALUE_LOXODROME_STRING = {
            "-----------------odległość loksodromiczna----------------",
            "-------------------loxodromic distance-------------------"};

    private final String[] VALUES_COURSE_ANGLES_STRING = {
            "-----------------------kąty drogi------------------------",
            "----------------------course angles----------------------"};

    private final String[] VALUES_ORTHODROMIC_VERTICES_STRING = {
            "------------------wierzchołki ortodromy------------------",
            "-----------orthodromic (great circle) vertices-----------"};

    private final String[] PRIMARY_INSTRUCTION_STRING = {
            "\n" +
                    "  -------------------Kalkulator Ortodromy-----------------" + "\n\n" +
                    "  -----------------------O PROGRAMIE----------------------" + "\n\n" +
                    "  Program wyznaczający podstawowe parametry ortodromy" + "\n" +
                    "  mając dane współrzędne geograficzne dwóch punktów A,B:" + "\n\n" +
                    "  - odległość ortodromiczną (w stopniach oraz Mm)," + "\n" +
                    "  - początkowy oraz końcowy kąt drogi," + "\n" +
                    "  - współrzędne wierzchołków ortodromy." + "\n\n" +
                    "  Dodatkowo program oblicza i wypisuje wszystkie parametry " + "\n" +
                    "  wymagane do obliczeń.",
            "\n" +
                    "  -------------------Orthodrome calculator----------------" + "\n\n" +
                    "  ---------------------ABOUT APPLICATION------------------" + "\n\n" +
                    "  Application calculates basic parameters of orthodrome" + "\n" +
                    "  having geographical coordinates of two points A,B:" + "\n\n" +
                    "  - orthodromic distance (given in degrees and NM)," + "\n" +
                    "  - initial and final course," + "\n" +
                    "  - vertices of the orthodrome." + "\n\n" +
                    "  Additionally application is calculating all parameters" + "\n" +
                    "  that are needed during the calculation."};

    private final String[] SECONDARY_INSTRUCTION_STRING = {
            "\n\n\n" +
                    "  -----------------------INSTRUKCJA-----------------------" + "\n\n" +
                    "  Akceptowalny zakres danych wejściowych to:" + "\n\n" +
                    "  szerokość od 0°00.0'' do  90°00.0'' " + "\n" +
                    "  długość   od 0°00.0'' do 180°00.0'' " + "\n\n" +
                    "  Kąty podajemy jako liczby całkowite od 0   do 180  " + "\n" +
                    "  Minuty jako liczby dziesiętne       od 0.0 do  59.9",
            "\n\n\n" +
                    "  ----------------------INSTRUCTION----------------------" + "\n\n" +
                    "  Acceptable range of input parameters are:" + "\n\n" +
                    "  latitude  from 0°00.0'' to  90°00.0'' " + "\n" +
                    "  longitude from 0°00.0'' to 180°00.0'' " + "\n\n" +
                    "  Degrees are to be given as integers from 0   to 180  " + "\n" +
                    "  Minutes as floating numbers         from 0.0 do  59.9"};

    private final String[] PRIMARY_INVALID_DATA_INSTRUCTION_STRING = {
            "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ----- niepoprawne dane -----  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                -------- nie liczymy -------  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ----------------------------  ",
            "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                --- not valid input data ---  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ------- incalculable -------  " + "\n" +
                    "                ----------------------------  " + "\n" +
                    "                ----------------------------  "};

    private final String[] SECONDARY_INVALID_DATA_INSTRUCTION_STRING = {
            "\n" +
                    "  --------------------danie niepoprawne-------------------" + "\n\n" +
                    "  Proszę o podanie poprawnych danych" + "\n\n" +
                    "  Akceptowalny zakres danych wejściowych to:" + "\n\n" +
                    "  szerokość od 0°00.0'' do  90°00.0'' " + "\n" +
                    "  długość   od 0°00.0'' do 180°00.0'' " + "\n\n" +
                    "  Kąty podajemy jako liczby całkowite od 0   do 180  " + "\n" +
                    "  Minuty jako liczby dziesiętne       od 0.0 do  59.9",
            "\n" +
                    "  ---------------------not valid data---------------------" + "\n\n" +
                    "  Please enter proper input data" + "\n\n" +
                    "  Acceptable range of input parameters are:" + "\n\n" +
                    "  latitude  from 0°00.0'' to  90°00.0'' " + "\n" +
                    "  longitude from 0°00.0'' to 180°00.0'' " + "\n\n" +
                    "  Degrees are to be given as integers from 0   to 180  " + "\n" +
                    "  Minutes as floating numbers         from 0.0 do  59.9"};

    private final String[] INHOMOGENEOUS_ANGLES_STRING = {
            " - Kąty A,B są niejednorodne, więc wysokość leży na \n   zewnątrz trójkąta",
            " - A, B angles are inhomogeneous, so height of the \n   triangle lies outside"};

    private final String[] HOMOGENEOUS_ANGLES_STRING = {
            " - Kąty A,B są jednorodne, więc wysokość leży wewnątrz \n   trójkąta",
            " - A, B angles are homogeneous, so height of the triangle \n   lies inside"};

    private final String[] RIGHT_ANGLED_TRIANGLE = {
            " - Jeden z kątów jest prosty, więc jeden bok jest \n   wysokością trójkąta",
            " - One of the angle is right, so one of the sides is \n   height of the triangle"};

    private final String[] ORTHODROME_GAIN_STRING = {
            " - zysk z płynięcia po ortodromie wynosi ",
            " - orthodromic gain is equal to "};

    private final String[] ORTHODROME_GAIN_SPECIAL_CASE_STRING = {
            " - w przypadkach szczególnych ortodroma jest \n   równa loksodromie",
            " - in all special cases orthodrome is equal \n   to loxodrome"};

    private final String[] SPECIAL_CASE_OPPOSITE_INFO_STRING = {
            "\n" +
                    " - Podane punkty są dokładnie po przeciwległej stronie" + "\n" +
                    "   kuli ziemskiej. Do przebycia jest połowa obwodu," + "\n" +
                    "   a do wyboru jest nieskończenie wiele ortodrom" + "\n\n\n\n\n\n\n" +
                    " - A, B oraz h nie są wyznaczane" + "\n\n\n\n\n\n\n" +
                    " - Odległość ortodromiczna to połowa obwodu ziemi" + "\n\n\n\n\n\n" +
                    " - Brak możliwości określenia zysku ortodromicznego" + "\n\n\n\n\n\n" +
                    " - Początkowy kąt drogi może być przyjęty dowolnie" + "\n\n\n\n\n\n\n" +
                    " - Wierzchołki ortodromy mogą być dowolne i zależą od" + "\n" +
                    "   przyjętej ortodromy.",
            "\n" +
                    " - Given points are exactly on opposite sides of the" + "\n" +
                    "   globe.There is exactly half of circumference to go," + "\n" +
                    "   and infinitely many possibilities of orthodromes to" + "\n" +
                    "   choose" + "\n\n\n\n\n\n" +
                    " - A, B and h are not calculated" + "\n\n\n\n\n\n\n" +
                    " - Orthodromic distance is equal to half circumference" + "\n" +
                    "   of the globe" + "\n\n\n\n\n" +
                    " - It's not possible to calculate orthodromic gain" + "\n\n\n\n\n\n" +
                    " - Initial course can be chosen freely" + "\n\n\n\n\n\n\n" +
                    " - Orthodromic vertexes are not able to predict as" + "\n" +
                    "   they depends on chosen orthodrome."};

    private final String[] SPECIAL_CASE_MERIDIAN_SAIL_INFO_STRING_BEGIN = {
            "\n" +
                    " - W przypadku poruszania się po południku trójkąt" + "\n" +
                    "   sferyczny abdABC nie istnieje, więc nie liczymy\n" +
                    "   kątów A, B oraz h",
            "\n" +
                    " - In case of meridian sailing, spherical triangle" + "\n" +
                    "   abdABC doesn't exist, so angles A, B and h are" + "\n" +
                    "   not being calculated"};

    private final String[] SPECIAL_CASE_MERIDIAN_SAIL_INFO_STRING_ENDING = {
            "\n" +
                    " - Wierzchołkami ortodromy są odpowiednio biegun" + "\n" +
                    "   północny oraz południowy",
            "\n" +
                    " - North Pole and South Pole are orthodrome" + "\n" +
                    "   vertices"};

    private final String[] SPECIAL_CASE_EQUATOR_SAIL_INFO_STRING_BEGIN = {
            "\n" +
                    " - W przypadku poruszania się po równiku trójkąt" + "\n" +
                    "   sferyczny abdABC posiada dwa kąty proste" + "\n",
            "\n" +
                    " - In case of equator sailing, spherical triangle" + "\n" +
                    "   abdABC has two right angles" + "\n"};

    private final String[] SPECIAL_CASE_EQUATOR_SAIL_INFO_STRING_ENDING = {
            "\n" +
                    " - Wierzchołkami ortodromy są wszystkie punkty" + "\n" +
                    "   leżące na równiku",
            "\n" +
                    " - All points on the equator are orthodrome vertices"};


    private final String[] SPECIAL_CASE_SAME_POINT_INFO_STRING = {
            "\n" +
                    " - Podano takie same punkty. Kalkulacja nie jest" + "\n" +
                    "   przeprowadzana. Proszę wprowadzić poprawne dane",
            "\n" +
                    " - Identical points are given. Calculation is not" + "\n" +
                    "   performed. Please type proper data"};

    private final String[] SAIL_DIRECTION_STRING = {
            " - kierunek drogi to ",
            " - sail direction is "};

    private final String[] NOT_CALCULABLE_STRING = {
            "nie liczymy",
            "not calculable"};

    private final String[] FREE_TO_CHOOSE_STRING = {
            "dowolny",
            "free to choose"};

    private final String[] ANY_POINT_ON_EQUATOR_STRING = {
            "każdy punkt leżący na równiku",
            "any point on the equator"};

    private final String[] NORTH_POLE_STRING = {
            "biegun północny",
            "north pole"};

    private final String[] SOUTH_POLE_STRING = {
            "biegun południowy",
            "south pole"};

    private final String LENGTH_UNIT_km = "km";

    @FXML
    private final String[] LENGTH_UNIT_NM = {
            "Nm",
            "Mm"};


    public void initialize() {
        allNumberInputFields.addAll(Arrays.asList(aLatDegTxt, aLongDegTxt, bLatDegTxt, bLongDegTxt));
        allNumberInputFields.addAll(Arrays.asList(aLatMinTxt, aLongMinTxt, bLatMinTxt, bLongMinTxt));

        for (TextField field : Arrays.asList(aLatDegTxt, aLongDegTxt, bLatDegTxt, bLongDegTxt)) {
            setTextLimit(field, 3);
        }

        for (TextField field : Arrays.asList(aLatMinTxt, aLongMinTxt, bLatMinTxt, bLongMinTxt)) {
            setTextLimit(field, 4);
        }

        languageCb.getItems().add("PL");
        languageCb.getItems().add("EN");
        languageCb.setValue("PL");
        languageCb.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> changeLanguage());

        languageCode = PL_LANGUAGE_CODE;
        clearParameters();
    }

    public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();
            if (string.length() > length) {
                textField.setText(string.substring(0, length));
                textField.positionCaret(string.length());
            }
        });
    }

    public void changeLanguage() {
        languageCode = languageCb.getValue().equals("PL") ? PL_LANGUAGE_CODE : EN_LANGUAGE_CODE;

        titleLabel.setText(INPUT_PARAMETERS_STRING[languageCode]);
        generalCasesLabel.setText(GENERAL_CASES_STRING[languageCode]);
        specialCasesLabel.setText(SPECIAL_CASES_STRING[languageCode]);
        calculateBtn.setText(CALCULATE_STRING[languageCode]);
        clearBtn.setText(CLEAR_INFO_STRING[languageCode]);
        bottomInfoLabel.setText(PROGRAM_NAME[languageCode] + PROGRAM_VERSION);

        if (checkIfFieldsHaveNonZeroValue()) {
            inputProcedure();
        } else {
            printInstructions();
        }
    }

    public boolean checkIfFieldsHaveNonZeroValue() {
        for (TextField field : allNumberInputFields) {
            String inputFieldValue = field.getText().trim();
            if (!inputFieldValue.equals("0") && !inputFieldValue.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void clearParameters() {
        for (TextField textField : allNumberInputFields) {
            textField.clear();
        }
        aLatSideTxt.setText("N");
        aLongSideTxt.setText("E");
        bLatSideTxt.setText("N");
        bLongSideTxt.setText("E");
        printInstructions();
    }

    @FXML
    public boolean inputProcedure() {

        fillEmptyValuesWithZeros();

        if (!validateInputFormat()) {
            return false;
        }

        Point aPoint =
                new Point(
                        aLatSideTxt.getText(),
                        Double.parseDouble(aLatDegTxt.getText()),
                        Double.parseDouble(aLatMinTxt.getText()),
                        aLongSideTxt.getText(),
                        Double.parseDouble(aLongDegTxt.getText()),
                        Double.parseDouble(aLongMinTxt.getText())
                );

        Point bPoint =
                new Point(
                        bLatSideTxt.getText(),
                        Double.parseDouble(bLatDegTxt.getText()),
                        Double.parseDouble(bLatMinTxt.getText()),
                        bLongSideTxt.getText(),
                        Double.parseDouble(bLongDegTxt.getText()),
                        Double.parseDouble(bLongMinTxt.getText())
                );

        if (!validateInputValues(aPoint, bPoint)) {
            return false;
        }

        AllResults allResults = calculationProcedure(aPoint, bPoint);
        printResults(allResults);
        return true;
    }

    public void fillEmptyValuesWithZeros() {
        for (TextField textField : allNumberInputFields) {
            if (textField.getText().trim().equals("")) {
                textField.setText("0");
            }
        }
    }

    public boolean validateInputFormat() {
        for (TextField inputField : allNumberInputFields) {
            if (!inputField.getText().matches("[0-9]{1,13}(\\.[0-9]*)?")) {
                printInstructionsInvalidData();
                return false;
            }
        }
        return true;
    }

    public boolean validateInputValues(Point aPoint, Point bPoint) {
        Point[] points = {aPoint, bPoint};
        for (Point point : points) {
            if (!validateLatitude(point) || !validateLongitude(point)) {
                printInstructionsInvalidData();
                return false;
            }
        }
        return true;
    }

    @FXML
    public AllResults calculationProcedure(Point aPoint, Point bPoint) {
        caseType = verifySpecialCases(aPoint, bPoint);

        SphericalTriangle sphericalTriangle = new SphericalTriangle(aPoint, bPoint, caseType);
        Orthodrome orthodrome = new Orthodrome(sphericalTriangle, aPoint, bPoint, caseType);
        CourseAngles courseAngles = new CourseAngles(sphericalTriangle, aPoint, bPoint, caseType);
        Loxodrome loxodromePROPER = new Loxodrome(aPoint, bPoint, orthodrome, caseType);

        return new AllResults(orthodrome, loxodromePROPER.lengthNm, courseAngles, orthodrome.firstOrthodromeVertex, orthodrome.secondOrthodromeVertex);
    }


    public void printResults(AllResults allResults) {
        primaryTextArea.setText(printResultsValues(allResults));
        secondaryTextArea.setText(printHelpInformation(allResults));
    }


    public void printInstructions() {
        primaryTextArea.setText(PRIMARY_INSTRUCTION_STRING[languageCode]);
        secondaryTextArea.setText(SECONDARY_INSTRUCTION_STRING[languageCode]);
    }


    public void printInstructionsInvalidData() {
        primaryTextArea.setText(PRIMARY_INVALID_DATA_INSTRUCTION_STRING[languageCode]);
        secondaryTextArea.setText(SECONDARY_INVALID_DATA_INSTRUCTION_STRING[languageCode]);
    }

    public boolean validateLatitude(Point point) {
        if ((point.latCalculated > 90) || (point.latMin < 0 || point.latMin >= 60) || (point.latDeg < 0 || point.latDeg > 90)) {
            printInstructionsInvalidData();
            return false;
        }
        return true;
    }

    public boolean validateLongitude(Point point) {
        if ((point.longCalculated > 180) || (point.longMin < 0 || point.longMin >= 60) || (point.longDeg < 0 || point.longDeg > 180)) {
            printInstructionsInvalidData();
            return false;
        }
        return true;
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Methods responsible for switching latitudes/longitudes of given
     * Points by pressing mouse button or ENTER/SPACE key
     *
     */
    @FXML
    public void aChangeLatOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            aChangeLat();
        }
    }
    @FXML
    public void aChangeLat() {
        String side = aLatSideTxt.getText().trim();
        if (side.equals("N")) {
            aLatSideTxt.setText("S");
        } else if (side.equals("S")) {
            aLatSideTxt.setText("N");
        }
    }

    @FXML
    public void aChangeLongOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            aChangeLong();
        }
    }
    @FXML
    public void aChangeLong() {
        String side = aLongSideTxt.getText().trim();
        if (side.equals("E")) {
            aLongSideTxt.setText("W");
        } else if (side.equals("W")) {
            aLongSideTxt.setText("E");
        }
    }

    @FXML
    public void bChangeLatOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            bChangeLat();
        }
    }
    @FXML
    public void bChangeLat() {
        String side = bLatSideTxt.getText().trim();
        if (side.equals("N")) {
            bLatSideTxt.setText("S");
        } else if (side.equals("S")) {
            bLatSideTxt.setText("N");
        }
    }

    @FXML
    public void bChangeLongOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            bChangeLong();
        }
    }
    @FXML
    public void bChangeLong() {
        String side = bLongSideTxt.getText().trim();
        if (side.equals("E")) {
            bLongSideTxt.setText("W");
        } else if (side.equals("W")) {
            bLongSideTxt.setText("E");
        }
    }



    @FXML
    public void inputProcedureOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            inputProcedure();
        }
    }

    @FXML
    public void clearProcedureOnSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            clearParameters();
        }
    }

    @FXML
    public void inputProcedureOnEnter(KeyEvent keyEvent) {
        if(isEnterPressed(keyEvent)) {
            inputProcedure();
        }
    }

    public boolean isSpaceOrEnterPressed(KeyEvent keyEvent) {
        return (keyEvent.getCode().toString().equals("ENTER") || keyEvent.getCode().toString().equals("SPACE"));
    }

    public boolean isEnterPressed(KeyEvent keyEvent) {
        return (keyEvent.getCode().toString().equals("ENTER"));
    }








    public CASE verifySpecialCases(Point aPoint, Point bPoint) {
        double difLambda = Math.abs(aPoint.lambda - bPoint.lambda);
        double sumPhi = aPoint.phi + bPoint.phi;

        if (((aPoint.lambda == bPoint.lambda) && (aPoint.phi == bPoint.phi)) || (aPoint.phi*bPoint.phi == 8100)) {
            return CASE.SAME_POINT;

        } else if (((difLambda == 180) && (sumPhi == 0)) || ((aPoint.lambda == 0) && (bPoint.lambda == 0) && (sumPhi == 0))) {
            return CASE.OPPOSITE_POINTS;

        } else if ((aPoint.phi == 0) && (bPoint.phi == 0)) {
            return CASE.EQUATOR_SAIL;

        } else if ((aPoint.lambda == bPoint.lambda) || (difLambda == 180) || (aPoint.phi*bPoint.phi == -8100) || (Math.abs(aPoint.phi) == 90 || Math.abs(bPoint.phi) == 90)) {
            return CASE.MERIDIAN_SAIL;

        } else {
            return CASE.GENERAL;

        }
    }



    public String printHelpInformation(AllResults allResults) {

        if (caseType == CASE.OPPOSITE_POINTS) {
            return "\n" +
                    CORRECT_VALUES_STRING[languageCode] + SEPARATOR_DASH + SPECIAL_CASE_STRING[languageCode] + "\n" +
                    SPECIAL_CASE_OPPOSITE_STRING[languageCode] + "\n\n" +
                    SPECIAL_CASE_OPPOSITE_INFO_STRING[languageCode];

        } else if (caseType == CASE.MERIDIAN_SAIL) {
            return "\n" +
                    CORRECT_VALUES_STRING[languageCode] + SEPARATOR_DASH + SPECIAL_CASE_STRING[languageCode] + "\n" +
                    SPECIAL_CASE_MERIDIAN_SAILING_STRING[languageCode] + "\n\n\n\n\n\n\n\n\n\n\n" +
                    SPECIAL_CASE_MERIDIAN_SAIL_INFO_STRING_BEGIN[languageCode] + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    meridianSailCourseAnglesInfo(allResults.aPoint, allResults.bPoint)[languageCode] + "\n\n\n\n\n\n" +
                    SPECIAL_CASE_MERIDIAN_SAIL_INFO_STRING_ENDING[languageCode];

        } else if (caseType == CASE.EQUATOR_SAIL) {
            return "\n" +
                    CORRECT_VALUES_STRING[languageCode] + SEPARATOR_DASH + SPECIAL_CASE_STRING[languageCode] + "\n" +
                    SPECIAL_CASE_EQUATOR_SAILING_STRING[languageCode] + "\n\n\n\n\n\n\n\n\n\n\n" +
                    SPECIAL_CASE_EQUATOR_SAIL_INFO_STRING_BEGIN[languageCode] + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    equatorSailCourseAnglesInfo(allResults.aPoint, allResults.bPoint)[languageCode] + "\n\n\n\n\n\n" +
                    SPECIAL_CASE_EQUATOR_SAIL_INFO_STRING_ENDING[languageCode];

        } else if (caseType == CASE.SAME_POINT) {
            return "\n\n" +
                    SPECIAL_CASE_SAME_POINT_INFO_STRING[languageCode];

        } else {
            return "\n" +
                    CORRECT_VALUES_STRING[languageCode] + SEPARATOR_DASH + GENERAL_CASE_STRING[languageCode] + "\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    checkHomogeneousAngles(languageCode, allResults.sphericalTriangle) + "\n\n\n\n\n\n\n\n\n\n\n\n" +
                    ORTHODROME_GAIN_STRING[languageCode] + checkOrthodromeGain(allResults.orthodrome, allResults.loxodrome) + "\n\n\n\n\n\n" +
                    SAIL_DIRECTION_STRING[languageCode] + allResults.courseAngles.direction;
        }
    }

    public String[] meridianSailCourseAnglesInfo(Point aPoint, Point bPoint) {
        if (aPoint.phi > bPoint.phi) {
            return new String[] {
                    " - Kąt drogi wynosi stale 180°00.0' (prosto na południe)",
                    " - Angle of the course is 180°00.0' (straight south)"};
        } else {
            return new String[] {
                    " - Kąt drogi wynosi stale   0°00.0' (prosto na północ)",
                    " - Angle of the course is   0°00.0' (straight north)"};
        }
    }

    public String[] equatorSailCourseAnglesInfo(Point aPoint, Point bPoint) {
        if (aPoint.lambda > bPoint.lambda) {
            return new String[] {
                    " - Kąt drogi wynosi stale 270°00.0' (prosto na zachód)",
                    " - Angle of the course is 270°00.0' (straight west)"};
        } else {
            return new String[] {
                    " - Kąt drogi wynosi stale  90°00.0' (prosto na wschód)",
                    " - Angle of the course is  90°00.0' (straight east)"};
        }
    }

    //TODO: deprecate this, move to Loxodrome class
    public String checkOrthodromeGain(Orthodrome orthodrome, double loxodrome) {
        double gain = loxodrome - orthodrome.distanceAngles * 60;
        return String.valueOf(String.format("%.2f", gain)) + " " + LENGTH_UNIT_NM[languageCode] + ".";
    }

    public String checkHomogeneousAngles(int languageCode, SphericalTriangle sphericalTriangle) {
        if ((sphericalTriangle.A > 90 && sphericalTriangle.B < 90) || (sphericalTriangle.B > 90 && sphericalTriangle.A < 90)) {
            return INHOMOGENEOUS_ANGLES_STRING[languageCode];

        } else if ((sphericalTriangle.A < 90 && sphericalTriangle.B < 90) || (sphericalTriangle.B > 90 && sphericalTriangle.A > 90)) {
            return HOMOGENEOUS_ANGLES_STRING[languageCode];

        } else {
            return RIGHT_ANGLED_TRIANGLE[languageCode];
        }
    }

    public String printResultsValues(AllResults allResults) {
        if (caseType == CASE.SAME_POINT) {
            return "\n" +
                    VALUES_LAT_LONG_STRING[languageCode] + "\n" +
                    printPhiAndLambdaValues(allResults.aPoint, allResults.bPoint) +
                    SEPARATOR_LINE;

        } else {
            return "\n" +
                    VALUES_LAT_LONG_STRING[languageCode] + "\n" +
                    printPhiAndLambdaValues(allResults.aPoint, allResults.bPoint) +
                    SEPARATOR_LINE + "\n\n" +

                    VALUES_SPHERICAL_TRIANGLE_STRING[languageCode] + "\n" +
                    printSphericalTriangleValues(allResults.sphericalTriangle, allResults.orthodrome) +
                    SEPARATOR_LINE + "\n\n" +

                    VALUE_ORTHODROME_STRING[languageCode] + "\n" +
                    printOrthodromeValue(allResults.orthodrome) +
                    SEPARATOR_LINE + "\n\n" +

                    VALUE_LOXODROME_STRING[languageCode] + "\n" +
                    printLoxodromeValue(allResults.loxodrome) +
                    SEPARATOR_LINE + "\n\n" +

                    VALUES_COURSE_ANGLES_STRING[languageCode] + "\n" +
                    printCourseAngles(allResults.courseAngles) +
                    SEPARATOR_LINE + "\n\n" +

                    VALUES_ORTHODROMIC_VERTICES_STRING[languageCode] + "\n" +
                    printOrthodromeVertices(allResults.firstOrthodromeVertex, allResults.secondOrthodromeVertex) +
                    SEPARATOR_LINE;

        }
    }


    public String printPhiAndLambdaValues(Point aPoint, Point bPoint) {
        return "\n" +
                "  φ_A =" + ddToDmString("lat", aPoint.phi) + "\t\t" +
                "  λ_A =" + ddToDmString("long", aPoint.lambda) +
                "\n" +
                "  φ_B =" + ddToDmString("lat", bPoint.phi) + "\t\t" +
                "  λ_B =" + ddToDmString("long", bPoint.lambda) +
                "\n\n";
    }

    public String printSphericalTriangleValues(SphericalTriangle sphericalTriangle, Orthodrome orthodrome) {
        if (caseType == CASE.GENERAL) {
            return "\n" +
                    "  a = " + ddToDmString("long", sphericalTriangle.a) +
                    "\n" +
                    "  b = " + ddToDmString("long", sphericalTriangle.b) +
                    "\n\n" +
                    "  C = " + ddToDmString("long", sphericalTriangle.C) +
                    "\n" +
                    "  A = " + ddToDmString("long", sphericalTriangle.A) + "\t" +
                    "  h = " + ddToDmString("long", orthodrome.height1) + "  v " + ddToDmString("long", orthodrome.height2) +
                    "\n" +
                    "  B = " + ddToDmString("long", sphericalTriangle.B) +
                    "\n\n";

        } else if (caseType == CASE.MERIDIAN_SAIL || caseType == CASE.OPPOSITE_POINTS) {
            return "\n" +
                    "  a = " + ddToDmString("long", sphericalTriangle.a) +
                    "\n" +
                    "  b = " + ddToDmString("long", sphericalTriangle.b) +
                    "\n\n" +
                    "  C = " + ddToDmString("long", sphericalTriangle.C) +
                    "\n" +
                    "  A =  " + NOT_CALCULABLE_STRING[languageCode] + "\t" +
                    "  h =  " + NOT_CALCULABLE_STRING[languageCode] +
                    "\n" +
                    "  B =  " + NOT_CALCULABLE_STRING[languageCode] +
                    "\n\n";

        } else if (caseType == CASE.EQUATOR_SAIL) {
            return "\n" +
                    "  a = " + ddToDmString("long", sphericalTriangle.a) +
                    "\n" +
                    "  b = " + ddToDmString("long", sphericalTriangle.b) +
                    "\n\n" +
                    "  C = " + ddToDmString("long", sphericalTriangle.C) +
                    "\n" +
                    "  A = " + ddToDmString("long", sphericalTriangle.A) + "\t" +
                    "  h = " + ddToDmString("long", orthodrome.height1) +
                    "\n" +
                    "  B = " + ddToDmString("long", sphericalTriangle.B) +
                    "\n\n";

        } else {
            return "ERROR";

        }

    }

    public String printOrthodromeValue(Orthodrome orthodrome) {
        return "\n" +
                "  d = " + ddToDmString("long", orthodrome.distanceAngles) + " = " + String.valueOf(String.format("%.2f", orthodrome.distanceNm)) + " " + LENGTH_UNIT_NM[languageCode] +
                "\n\n";
    }

    public String printLoxodromeValue(double loxodrome) {
        return "\n" +
                "  s =              " + String.valueOf(String.format("%.2f", loxodrome)) + " " + LENGTH_UNIT_NM[languageCode] +
                "\n\n";
    }

    public String printCourseAngles(CourseAngles courseAngles) {
        if (caseType == CASE.OPPOSITE_POINTS) {
            return "\n" +
                    "  α =  " + FREE_TO_CHOOSE_STRING[languageCode] +
                    "\n" +
                    "  β =  180° - α" +
                    "\n\n";
        } else {
            return "\n" +
                    "  α = " + ddToDmString("long", courseAngles.initialCourse) +
                    "\n" +
                    "  β = " + ddToDmString("long", courseAngles.finalCourse) +
                    "\n\n";

        }
    }

    public String printOrthodromeVertices(Point firstOrthodromeVertex, Point secondOrthodromeVertex) {
        if (caseType == CASE.EQUATOR_SAIL) {
            return "\n" +
                    "  W1  (" + ddToDmString("lat", firstOrthodromeVertex.latCalculated, firstOrthodromeVertex.latSide) +
                    "  ; xxx°xx,x' E/W )" +
                    "\n" +
                    "     " + ANY_POINT_ON_EQUATOR_STRING[languageCode] +
                    "\n\n";

        } else if (caseType == CASE.MERIDIAN_SAIL) {
            return "\n" +
                    "  W1  (" + ddToDmString("lat", firstOrthodromeVertex.latCalculated, firstOrthodromeVertex.latSide) +
                    " ) - " + NORTH_POLE_STRING[languageCode] +
                    "\n" +
                    "  W2  (" + ddToDmString("lat", firstOrthodromeVertex.latCalculated, firstOrthodromeVertex.latSide) +
                    " ) - " + SOUTH_POLE_STRING[languageCode] +
                    "\n\n";

        } else if (caseType == CASE.OPPOSITE_POINTS) {
            return "\n" +
                    "  W1  ( xx°xx,x' N/S ; xxx°xx.x' E/W )" +
                    "\n" +
                    "  W2  ( xx°xx,x' N/S ; xxx°xx.x' E/W )" +
                    "\n\n";

        } else {
            return "\n" +
                    "  W1  (" + ddToDmString("lat", firstOrthodromeVertex.latCalculated, firstOrthodromeVertex.latSide) +
                    "  ," + ddToDmString("long", firstOrthodromeVertex.longCalculated, firstOrthodromeVertex.longSide) + "  )" +
                    "\n" +
                    "  W2  (" + ddToDmString("lat", secondOrthodromeVertex.latCalculated, secondOrthodromeVertex.latSide) +
                    "  ," + ddToDmString("long", secondOrthodromeVertex.longCalculated, secondOrthodromeVertex.longSide) + "  )" +
                    "\n\n";

        }
    }


    /**
     * Specific examples implemented on button pressed in GUI
     */
    //general cases//
    //---A(10°N;050°E) -> B(20°N;170°E)---//
    public void generalCase01onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase01();
        }
    }
    public void generalCase01() {
        clearParameters();

        aLatDegTxt.setText("10");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("50");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("20");
        bLatSideTxt.setText("N");
        bLongDegTxt.setText("170");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(50°N;020°W) -> B(50°N;090°E)---//
    public void generalCase02onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase02();
        }
    }
    public void generalCase02() {
        clearParameters();

        aLatDegTxt.setText("50");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("20");
        aLongSideTxt.setText("W");
        //-----------------------
        bLatDegTxt.setText("50");
        bLatSideTxt.setText("N");
        bLongDegTxt.setText("90");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(50°N;030°E) -> B(30°N;060°E)---//
    public void generalCase03onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase03();
        }
    }
    public void generalCase03() {
        clearParameters();

        aLatDegTxt.setText("50");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("30");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("30");
        bLatSideTxt.setText("N");
        bLongDegTxt.setText("60");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(50°N;170°W) -> B(10°S;100°W)---//
    public void generalCase04onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase04();
        }
    }
    public void generalCase04() {
        clearParameters();

        aLatDegTxt.setText("50");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("170");
        aLongSideTxt.setText("W");
        //-----------------------
        bLatDegTxt.setText("10");
        bLatSideTxt.setText("S");
        bLongDegTxt.setText("100");
        bLongSideTxt.setText("W");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(30°S;060°E) -> B(40°S;140°E)---//
    public void generalCase05onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase05();
        }
    }
    public void generalCase05() {
        clearParameters();

        aLatDegTxt.setText("30");
        aLatSideTxt.setText("S");
        aLongDegTxt.setText("60");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("40");
        bLatSideTxt.setText("S");
        bLongDegTxt.setText("140");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(20°S;100°W) -> B(30°N;160°W)---//
    public void generalCase06onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            generalCase06();
        }
    }
    public void generalCase06() {
        clearParameters();

        aLatDegTxt.setText("20");
        aLatSideTxt.setText("S");
        aLongDegTxt.setText("100");
        aLongSideTxt.setText("W");
        //-----------------------
        bLatDegTxt.setText("30");
        bLatSideTxt.setText("N");
        bLongDegTxt.setText("160");
        bLongSideTxt.setText("W");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //special cases//
    //---A(90°N      ) -> B(90°S      )---//
    public void specialCase01onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            specialCase01();
        }
    }
    public void specialCase01() {
        clearParameters();

        aLatDegTxt.setText("90");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("0");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("90");
        bLatSideTxt.setText("S");
        bLongDegTxt.setText("0");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(40°N;030°E) -> B(40°S;150°W)---//
    public void specialCase02onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            specialCase02();
        }
    }
    public void specialCase02() {
        clearParameters();

        aLatDegTxt.setText("40");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("30");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("40");
        bLatSideTxt.setText("S");
        bLongDegTxt.setText("150");
        bLongSideTxt.setText("W");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A(20°N;030°E) -> B(40°S;030°E)---//
    public void specialCase03onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            specialCase03();
        }
    }
    public void specialCase03() {
        clearParameters();

        aLatDegTxt.setText("20");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("30");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("40");
        bLatSideTxt.setText("S");
        bLongDegTxt.setText("30");
        bLongSideTxt.setText("E");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

    //---A( 0° ;010°E) -> B( 0° ;050°W)---//
    public void specialCase04onSpaceOrEnter(KeyEvent keyEvent) {
        if(isSpaceOrEnterPressed(keyEvent)) {
            specialCase04();
        }
    }
    public void specialCase04() {
        clearParameters();

        aLatDegTxt.setText("0");
        aLatSideTxt.setText("N");
        aLongDegTxt.setText("10");
        aLongSideTxt.setText("E");
        //-----------------------
        bLatDegTxt.setText("0");
        bLatSideTxt.setText("N");
        bLongDegTxt.setText("50");
        bLongSideTxt.setText("W");

        fillEmptyValuesWithZeros();
        inputProcedure();
    }

}
