package petmanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author leyen
 */
public final class Util {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String IGNORE_CASE_PATTERN = "(?i)";

    private Util() {
    }

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = Integer.MIN_VALUE;
        message = message + " in the range [" + minValue + ", " + maxValue + "]: ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
//                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static String inputString(String message, boolean allowBlankOrEmpty) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        if (allowBlankOrEmpty) {
            message += ": ";
        } else {
            message += " (not blank or empty): ";
        }
        do {
            System.out.print(message);
            inputString = sc.nextLine();
        } while (!allowBlankOrEmpty && inputString.isBlank());
        return inputString;
    }

    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
//                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, exU001);
            }
        } while (date == null);
        return date;
    }

    public static boolean inputBoolean(String message) {
        System.out.print(message + "(" + Boolean.TRUE.toString() + "/" + Boolean.FALSE.toString() + "): ");
        Scanner sc = new Scanner(System.in);
        return Boolean.parseBoolean(sc.nextLine());
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

    public static boolean validateStringPattern(String str, String regex, boolean ignoreCase) {
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
    public static double InputDouble(String msg, double min, double max) {
        double r;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg + ": ");
                r = Double.parseDouble(sc.nextLine());
                if (min < r || max > r) {
                    break;
                }
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
        return r;
    }

}
