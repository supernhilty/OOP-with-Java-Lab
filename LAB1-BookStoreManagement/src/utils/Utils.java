/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author leyen
 */
public class Utils {

    private static Scanner sc = new Scanner(System.in);

    private static final String IGNORE_CASE_PATTERN = "(?i)";

    public static String inputString(String message) {
        System.out.print(message + ": ");
        String str = sc.nextLine();
        return str;
    }


    public static boolean validateStringPattern(String str, String pattern) {
        pattern = Utils.IGNORE_CASE_PATTERN + pattern;
        if (str.matches(pattern)) {
            return true;
        }
        return false;
    }

    public static int inputInt(String message, int minValue, int maxValue) {
        int val = minValue - 1;

        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                ex.getMessage();
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static double InputDouble(String msg, double min, double max) {
        double r;

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
