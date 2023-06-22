package ordermanagement.utils;

import static java.lang.Double.NaN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author leyen
 */
public final class Util {

    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String IGNORE_CASE_PATTERN = "(?i)";

    private Util() {
    }

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please input an Integer");
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static String inputString(String message) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            inputString = sc.nextLine();
        } while (inputString.isBlank());
        return inputString;
    }

    public static String inputString(String message, boolean blank) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            inputString = sc.nextLine();
        } while (!blank);
        return inputString;
    }

    public static Date inputDateAllowEmty(String message) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        String checkEnter = "";
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                checkEnter = sc.nextLine();
                date = toDate(checkEnter);
            } catch (ParseException ex) {
                if (checkEnter.isBlank()) {
                    return null;
                } else {
                    System.out.println("Error input!");
                }
            }
        } while (date == null);
        return date;
    }

    public static Date toDate(String strDate) throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        df.setLenient(false);
        return df.parse(strDate);
    }

    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        return df.format(date);
    }

    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {
                System.out.println("Error input!");
            }
        } while (date == null);
        return date;
    }

    public static int inputInteger(String message, int minValue, int maxValue, boolean blank) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        String checkEnter = "";
        do {
            System.out.print(message + ": ");
            try {
                checkEnter = sc.nextLine();
                val = Integer.parseInt(checkEnter);
            } catch (NumberFormatException ex) {
                if (checkEnter.isBlank()) {
                    return (int) NaN;
                } else {
                    System.out.println("Please input an Integer");
                }

            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static boolean inputBoolean(String message) {
        System.out.print(message + "(" + Boolean.TRUE.toString() + "/" + Boolean.FALSE.toString() + "): ");
        Scanner sc = new Scanner(System.in);
        return Boolean.parseBoolean(sc.nextLine());
    }
    public static Boolean inputBooleanAllowEmpty(String message) {
        System.out.print(message + "(" + Boolean.TRUE.toString() + "/" + Boolean.FALSE.toString() + "): ");
        Scanner sc = new Scanner(System.in);
        String rs = sc.nextLine();
        if(rs.isBlank()){
            return null;
        }
        return Boolean.parseBoolean(sc.nextLine());
    }

    public static String inputAddress(String message) {
        System.out.println(message);

        return inputString("Province(or City)").toUpperCase();
    }

    public static boolean validateString(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Util.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    public static boolean validateDate(Date createDate, Date lastUpdateDate) {
        Date now = new Date();
        if (createDate == null) {
            return lastUpdateDate != null && !lastUpdateDate.after(now);
        } else if (lastUpdateDate == null) {
            return !createDate.after(now);
        }
        return !createDate.after(lastUpdateDate) && !lastUpdateDate.after(now);
    }

}
