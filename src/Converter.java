public class Converter {

    public double toRadians(double value) {
        return value * Math.PI / 180;
    }

    public double toDegrees(double value) {
        return value / Math.PI * 180;
    }

    //decimal degree (dd) to degree minute (dm)
    // ex: 00,00 to  00°00,0′ when type == lat
    //     00,00 to 000°00,0′ when type == long
    public String ddToDmString(String type, double decimalDegreeValue) {
        return ddToDmString(type, decimalDegreeValue, "");
    }

    public String ddToDmString(String type, double decimalDegreeValue, String side) {
        double valueDeg = Math.floor(Math.abs(decimalDegreeValue));
        double valueMin = (Math.abs(decimalDegreeValue) - valueDeg) * 60;

        String degPrefix = "";
        String minPrefix = "";
        String returnValue = "";

        if (Math.round(valueMin) == 60) {
            valueMin = 0;
            valueDeg++;
        }

        if (decimalDegreeValue < 0) {
            returnValue += "-";
        } else {
            returnValue += " ";
        }

        if (type.equals("long") && valueDeg < 100) {
            degPrefix += "0";
        }

        if (valueDeg < 10) {
            degPrefix += "0";
        }

        if (valueMin < 10) {
            minPrefix += "0";
        }

        returnValue +=
                degPrefix + String.valueOf(String.format("%.0f", valueDeg)) + "°" +
                        minPrefix + String.valueOf(String.format("%.1f", valueMin)) + "'" +
                        side;

        return returnValue;
    }

}
