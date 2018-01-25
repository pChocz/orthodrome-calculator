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

    private List<TextField> allNumberInputFields = new ArrayList<>();

    @FXML
    private Button calculateBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button aLatSideBtn;
    @FXML
    private Button aLongSideBtn;
    @FXML
    private Button bLatSideBtn;
    @FXML
    private Button bLongSideBtn;

    @FXML
    private TextArea primaryTextArea;
    @FXML
    private TextArea secondaryTextArea;

    @FXML
    private ChoiceBox<String> languageCb;

    private int languageCode;
    private final int PL_LANGUAGE_CODE = 0;

    private CASE caseType;
    public enum CASE {
        SAME_POINT,
        MERIDIAN_SAIL,
        EQUATOR_SAIL,
        OPPOSITE_POINTS,
        GENERAL
        }

    private static final String PROGRAM_VERSION = " v1.06";

    private static final String[] INPUT_PARAMETERS_STRING = {
            "Parametry wejściowe",
            "Input parameters"};

    private static final String[] GENERAL_CASES_STRING = {
            "Przypadki ogólne",
            "General cases"};

    private static final String[] SPECIAL_CASES_STRING = {
            "Przypadki szczególne",
            "Special cases"};

    private static final String[] CALCULATE_STRING = {
            "Oblicz",
            "Calculate"};

    private static final String[] CLEAR_INFO_STRING = {
            "Wyczyść/Info",
            "Clear/Info"};

    private static final String[] PROGRAM_NAME = {
            "kalkulator ortodromy",
            "orthodrome calculator"};

    /**
     * Initialization and GUI-handling methods
     */
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
        languageCb.getSelectionModel().selectedItemProperty().
                addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> changeLanguage());

        languageCode = PL_LANGUAGE_CODE;
        clearParameters();
    }

    private static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();
            if (string.length() > length) {
                textField.setText(string.substring(0, length));
                textField.positionCaret(string.length());
            }
        });
    }

    public void changeLanguage() {
        int EN_LANGUAGE_CODE = 1;
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

    private boolean checkIfFieldsHaveNonZeroValue() {
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


    /**
     * MAIN METHOD
     *
     * method called always when new input
     * data is entered in GUI
     */
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

        calculationProcedure(aPoint, bPoint);
        return true;
    }

    private void fillEmptyValuesWithZeros() {
        for (TextField textField : allNumberInputFields) {
            if (textField.getText().trim().equals("")) {
                textField.setText("0");
            }
        }
    }

    private boolean validateInputFormat() {
        for (TextField inputField : allNumberInputFields) {
            if (!inputField.getText().matches("[0-9]{1,13}(\\.[0-9]*)?")) {
                printInstructionsInvalidData();
                return false;
            }
        }
        return true;
    }

    private boolean validateInputValues(Point aPoint, Point bPoint) {
        Point[] points = {aPoint, bPoint};
        for (Point point : points) {
            if (!validateLatitude(point) || !validateLongitude(point)) {
                printInstructionsInvalidData();
                return false;
            }
        }
        return true;
    }

    private boolean validateLatitude(Point point) {
        if ((point.latCalculated > 90) || (point.latMin < 0 || point.latMin >= 60) || (point.latDeg < 0 || point.latDeg > 90)) {
            printInstructionsInvalidData();
            return false;
        }
        return true;
    }

    private boolean validateLongitude(Point point) {
        if ((point.longCalculated > 180) || (point.longMin < 0 || point.longMin >= 60) || (point.longDeg < 0 || point.longDeg > 180)) {
            printInstructionsInvalidData();
            return false;
        }
        return true;
    }


    /**
     * MAIN CALCULATION PROCEDURE
     *
     * Constructors used in this procedure are also responsible
     * for calculating all numeric results
     */
    @FXML
    private void calculationProcedure(Point aPoint, Point bPoint) {
        caseType = verifySpecialCases(aPoint, bPoint);

        SphericalTriangle sphericalTriangle = new SphericalTriangle(aPoint, bPoint, caseType);
        Orthodrome orthodrome = new Orthodrome(sphericalTriangle, aPoint, bPoint, caseType);
        BearingAngles bearingAngles = new BearingAngles(sphericalTriangle, aPoint, bPoint, caseType);
        Loxodrome loxodrome = new Loxodrome(aPoint, bPoint, orthodrome, caseType);

        AllResults allResults = new AllResults(orthodrome, loxodrome, bearingAngles);
        printResults(allResults);
    }

    CASE verifySpecialCases(Point aPoint, Point bPoint) {
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


    /**
     * RESULTS HANDLING
     *
     * Methods responsible for data results
     * preparation to be viewed in GUI
     */
    private void printResults(AllResults allResults) {
        PrimaryTextCreator primaryText = new PrimaryTextCreator(allResults, languageCode, caseType);
        primaryTextArea.setText(primaryText.printResultsValues());

        SecondaryTextCreator secondaryText = new SecondaryTextCreator(allResults, languageCode, caseType);
        secondaryTextArea.setText(secondaryText.printHelpInformation());
    }

    private void printInstructions() {
        primaryTextArea.setText(PrimaryTextCreator.PRIMARY_INSTRUCTION_STRING[languageCode]);
        secondaryTextArea.setText(SecondaryTextCreator.SECONDARY_INSTRUCTION_STRING[languageCode]);
    }

    private void printInstructionsInvalidData() {
        primaryTextArea.setText(PrimaryTextCreator.PRIMARY_INVALID_DATA_INSTRUCTION_STRING[languageCode]);
        secondaryTextArea.setText(SecondaryTextCreator.SECONDARY_INVALID_DATA_INSTRUCTION_STRING[languageCode]);
    }


    /**
     * Methods responsible for switching latitudes/longitudes of given Points
     * with implemented key events handlers for ENTER/SPACE button
     */
    @FXML
    public void aChangeLatOnSpaceOrEnter(KeyEvent keyEvent) {
        if (isSpaceOrEnterPressed(keyEvent)) {
            aChangeLat();
            inputProcedure();
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
        if (isSpaceOrEnterPressed(keyEvent)) {
            aChangeLong();
            inputProcedure();
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
        if (isSpaceOrEnterPressed(keyEvent)) {
            bChangeLat();
            inputProcedure();
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
        if (isSpaceOrEnterPressed(keyEvent)) {
            bChangeLong();
            inputProcedure();
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


    /**
     * Specific examples implemented on button pressed in GUI with
     * implemented key events handlers for ENTER/SPACE button
     */
    //general cases//
    //---A(10°N;050°E) -> B(20°N;170°E)---//
    public void generalCase01onSpaceOrEnter(KeyEvent keyEvent) {
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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
        if (isSpaceOrEnterPressed(keyEvent)) {
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


    /**
     * KEY-EVENTS HANDLERS
     * <p>
     * choosing proper actions on ENTER/SPACE key pressed
     */
    @FXML
    public void inputProcedureOnSpaceOrEnter(KeyEvent keyEvent) {
        if (isSpaceOrEnterPressed(keyEvent)) {
            inputProcedure();
        }
    }

    @FXML
    public void clearProcedureOnSpaceOrEnter(KeyEvent keyEvent) {
        if (isSpaceOrEnterPressed(keyEvent)) {
            clearParameters();
        }
    }

    @FXML
    public void inputProcedureOnEnter_aLatDegTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                aLongDegTxt.requestFocus();
                break;
            case "RIGHT":
                aLatMinTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_aLatMinTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                aLongMinTxt.requestFocus();
                break;
            case "RIGHT":
                aLatSideTxt.requestFocus();
                break;
            case "LEFT":
                aLatDegTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_aLatSideTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "RIGHT":
            case "ENTER":
                inputProcedure();
                aLatSideBtn.requestFocus();
                break;
            case "LEFT":
                aLatMinTxt.requestFocus();
                break;
            case "DOWN":
                aLongSideTxt.requestFocus();
                break;
        }
    }


    @FXML
    public void inputProcedureOnEnter_aLongDegTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                bLatDegTxt.requestFocus();
                break;
            case "UP":
                aLatDegTxt.requestFocus();
                break;
            case "RIGHT":
                aLongMinTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_aLongMinTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                bLatMinTxt.requestFocus();
                break;
            case "UP":
                aLatMinTxt.requestFocus();
                break;
            case "RIGHT":
                aLongSideTxt.requestFocus();
                break;
            case "LEFT":
                aLongDegTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_aLongSideTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "RIGHT":
            case "ENTER":
                inputProcedure();
                aLongSideBtn.requestFocus();
                break;
            case "LEFT":
                aLongMinTxt.requestFocus();
                break;
            case "DOWN":
                bLatSideTxt.requestFocus();
                break;
            case "UP":
                aLatSideTxt.requestFocus();
                break;
        }
    }


    @FXML
    public void inputProcedureOnEnter_bLatDegTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "UP":
                aLongDegTxt.requestFocus();
                break;
            case "DOWN":
                bLongDegTxt.requestFocus();
                break;
            case "RIGHT":
                bLatMinTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_bLatMinTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "UP":
                aLongMinTxt.requestFocus();
                break;
            case "DOWN":
                bLongMinTxt.requestFocus();
                break;
            case "RIGHT":
                bLatSideTxt.requestFocus();
                break;
            case "LEFT":
                bLatDegTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_bLatSideTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "RIGHT":
            case "ENTER":
                inputProcedure();
                bLatSideBtn.requestFocus();
                break;
            case "UP":
                aLongSideTxt.requestFocus();
                break;
            case "LEFT":
                bLatMinTxt.requestFocus();
                break;
            case "DOWN":
                bLongSideTxt.requestFocus();
                break;
        }
    }


    @FXML
    public void inputProcedureOnEnter_bLongDegTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                clearBtn.requestFocus();
                break;
            case "UP":
                bLatDegTxt.requestFocus();
                break;
            case "RIGHT":
                bLongMinTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_bLongMinTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "ENTER":
                inputProcedure();
                break;
            case "DOWN":
                clearBtn.requestFocus();
                break;
            case "UP":
                bLatMinTxt.requestFocus();
                break;
            case "RIGHT":
                bLongSideTxt.requestFocus();
                break;
            case "LEFT":
                bLongDegTxt.requestFocus();
                break;
        }
    }

    @FXML
    public void inputProcedureOnEnter_bLongSideTxt(KeyEvent keyEvent) {
        switch (keyEvent.getCode().toString()) {
            case "RIGHT":
            case "ENTER":
                bLongSideBtn.requestFocus();
                break;
            case "LEFT":
                bLongMinTxt.requestFocus();
                break;
            case "DOWN":
                calculateBtn.requestFocus();
                break;
            case "UP":
                bLatSideTxt.requestFocus();
                break;
        }
        inputProcedure();
    }

    @FXML
    public void primaryTextAreaNavigation(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("RIGHT")) {
            secondaryTextArea.requestFocus();
        } else if (keyEvent.getCode().toString().equals("LEFT")) {
            calculateBtn.requestFocus();
        }

    }

    @FXML
    public void secondaryTextAreaNavigation(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("LEFT")) {
            primaryTextArea.requestFocus();
        } else if (keyEvent.getCode().toString().equals("RIGHT")) {
            aLatDegTxt.requestFocus();
        }
    }

    private boolean isSpaceOrEnterPressed(KeyEvent keyEvent) {
        return (keyEvent.getCode().toString().equals("ENTER") || keyEvent.getCode().toString().equals("SPACE"));
    }

}