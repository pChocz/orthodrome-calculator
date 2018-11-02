package util;

public class Converter {

    private Converter(){
        // private constructor to prevent initializing
    }

    public static double toRadians(double value) {
        return value * Math.PI / 180;
    }

    public static double toDegrees(double value) {
        return value / Math.PI * 180;
    }

    public static double dmToDdDouble(double degree, double minute) {
        return degree + minute/60;
    }

    // decimal degree (dd) to degree minute (dm)
    // ex: 00,00 to  00°00,0′ when type == lat
    //     00,00 to 000°00,0′ when type == long
    public static String ddToDmString(String type, double decimalDegreeValue) {
        return ddToDmString(type, decimalDegreeValue, "");
    }

    public static String ddToDmString(String type, double decimalDegreeValue, String side) {
        double valueDeg = Math.floor(Math.abs(decimalDegreeValue));
        double valueMin = (Math.abs(decimalDegreeValue) - valueDeg) * 60;

        String degPrefix = "";
        String minPrefix = "";
        String returnValue = "";

        if (Math.round(valueMin) == 60) {
            valueMin = 0;
            valueDeg++;
        }

        if (type.equals("phi") || type.equals("lambda")) returnValue += " ";

        returnValue += decimalDegreeValue < 0 ? "-" : " ";

        if ((type.equals("long") || type.equals("lambda")) && valueDeg < 100) degPrefix += "0";

        if (valueDeg < 10) degPrefix += "0";

        if (valueMin < 10) minPrefix += "0";

        returnValue +=  degPrefix + String.valueOf(String.format("%.0f", valueDeg)) + "°" +
                        minPrefix + String.valueOf(String.format("%.1f", valueMin)) + "'" + side;

        return returnValue;
    }

}
