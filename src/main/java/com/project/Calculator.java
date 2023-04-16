package com.project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Calculator {
    static class Roman {
        private static final String NUMBER_NOT_EXIST = "Операнд содержит несуществующее число: %s";
        private static final Map<String, Integer> romanNumerals = new LinkedHashMap<>();

        static {
            romanNumerals.put("M", 1000);
            romanNumerals.put("CM", 900);
            romanNumerals.put("D", 500);
            romanNumerals.put("CD", 400);
            romanNumerals.put("C", 100);
            romanNumerals.put("XC", 90);
            romanNumerals.put("L", 50);
            romanNumerals.put("XL", 40);
            romanNumerals.put("X", 10);
            romanNumerals.put("IX", 9);
            romanNumerals.put("V", 5);
            romanNumerals.put("IV", 4);
            romanNumerals.put("I", 1);
        }

        public static String addition(List<String> expression) {
            int result = 0;
            for (int i = 0; i < expression.size(); i += 2) {
                String romanNum = expression.get(i);
                try {
                    int firstNum = romanNumerals.get(String.valueOf(romanNum.charAt(romanNum.length() - 1)));
                    result += romanToArabic(romanNum, firstNum);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(String.format(NUMBER_NOT_EXIST, romanNum));
                }
            }
            return arabicToRoman(result);
        }

        public static String subtraction(List<String> expression) {
            int result = 0;
            for (int i = 0; i < expression.size(); i += 2) {
                String romanNum = expression.get(i);
                try {
                    int firstNum = romanNumerals.get(String.valueOf(romanNum.charAt(romanNum.length() - 1)));
                    if (i == 0)
                        result += romanToArabic(romanNum, firstNum);
                    else
                        result -= romanToArabic(romanNum, firstNum);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(String.format(NUMBER_NOT_EXIST, romanNum));
                }
            }
            return arabicToRoman(result);
        }

        public static String multiplication(List<String> expression) {
            int result = 0;
            for (int i = 0; i < expression.size(); i += 2) {
                String romanNum = expression.get(i);
                try {
                    int firstNum = romanNumerals.get(String.valueOf(romanNum.charAt(romanNum.length() - 1)));
                    if (i == 0)
                        result += romanToArabic(romanNum, firstNum);
                    else
                        result *= romanToArabic(romanNum, firstNum);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(String.format(NUMBER_NOT_EXIST, romanNum));
                }
            }
            return arabicToRoman(result);
        }

        public static String division(List<String> expression) {
            int result = 0;
            for (int i = 0; i < expression.size(); i += 2) {
                String romanNum = expression.get(i);
                try {
                    int firstNum = romanNumerals.get(String.valueOf(romanNum.charAt(romanNum.length() - 1)));
                    if (i == 0)
                        result += romanToArabic(romanNum, firstNum);
                    else
                        result /= romanToArabic(romanNum, firstNum);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(String.format(NUMBER_NOT_EXIST, romanNum));
                }
            }
            return arabicToRoman(result);
        }

        private static int romanToArabic(String romanNum, int firstNum) {
            int convertNum = firstNum;
            for (int j = romanNum.length() - 2; j >= 0; j--) {
                if (romanNumerals.get(String.valueOf(romanNum.charAt(j))) < romanNumerals.get(String.valueOf(romanNum.charAt(j + 1))))
                    convertNum -= romanNumerals.get(String.valueOf(romanNum.charAt(j)));
                else
                    convertNum += romanNumerals.get(String.valueOf(romanNum.charAt(j)));
            }
            return convertNum;
        }

        private static String arabicToRoman(int arabicNum) {
            StringBuilder romanNum = new StringBuilder();
            for (Map.Entry<String, Integer> entry : romanNumerals.entrySet()) {
                while (romanNumerals.get(entry.getKey()) <= arabicNum) {
                    romanNum.append(entry.getKey());
                    arabicNum -= romanNumerals.get(entry.getKey());
                }
            }
            return romanNum.toString();
        }

        public static Map<String, Integer> getRomanNumerals() {
            return romanNumerals;
        }
    }

    static class Arabic {
        private final int maxInt = 10;

        public static String addition(List<String> expression) {
            return null;
        }

        public static String subtraction(List<String> expression) {
            return null;
        }

        public static String multiplication(List<String> expression) {
            return null;
        }

        public static String division(List<String> expression) {
            return null;
        }
    }
}
