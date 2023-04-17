package com.project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class Calculator {
    private static final String MAX_NUM = "При операции вычисления, максимальное используемое число у двух операндов, должно быть не более 10. Получено: %s";
    private static int checkMaxValue(int operand, String romanNum) {
        if (operand > 10)
            throw new IllegalArgumentException(String.format(MAX_NUM, romanNum.equals("") ? operand : romanNum));
        return operand;
    }

    static class Roman {
        private static final String NUMBER_NOT_EXIST = "Операнд содержит несуществующее число: %s. Ожидается от I до X";
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
                } catch (NullPointerException e) {
                    throw new NoSuchElementException(String.format(NUMBER_NOT_EXIST, romanNum));
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
                } catch (NullPointerException e) {
                    throw new NoSuchElementException(String.format(NUMBER_NOT_EXIST, romanNum));
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
                } catch (NullPointerException e) {
                    throw new NoSuchElementException(String.format(NUMBER_NOT_EXIST, romanNum));
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
                } catch (NullPointerException e) {
                    throw new NoSuchElementException(String.format(NUMBER_NOT_EXIST, romanNum));
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
            return checkMaxValue(convertNum, romanNum);
        }

        private static String arabicToRoman(int arabicNum) {
            if (arabicNum < 1)
                throw new IllegalArgumentException("В римской системе счисления не может быть отрицательных чисел.");

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
        private static final String NUMBER_NOT_EXIST = "Операнд содержит несуществующее число: %s. Ожидается от 1 до 10.";

        public static String addition(List<String> expression) {
            try {
                int firstOperand = checkMaxValue(Integer.parseInt(expression.get(0)), "");
                int secondOperand = checkMaxValue(Integer.parseInt(expression.get(2)), "");

                return String.valueOf(firstOperand + secondOperand);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format(NUMBER_NOT_EXIST, e.getMessage()));
            }
        }

        public static String subtraction(List<String> expression) {
            try {
                int firstOperand = checkMaxValue(Integer.parseInt(expression.get(0)), "");
                int secondOperand = checkMaxValue(Integer.parseInt(expression.get(2)), "");

                return String.valueOf(firstOperand - secondOperand);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format(NUMBER_NOT_EXIST, e.getMessage()));
            }
        }

        public static String multiplication(List<String> expression) {
            try {
                int firstOperand = checkMaxValue(Integer.parseInt(expression.get(0)), "");
                int secondOperand = checkMaxValue(Integer.parseInt(expression.get(2)), "");

                return String.valueOf(firstOperand * secondOperand);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format(NUMBER_NOT_EXIST, e.getMessage()));
            }
        }

        public static String division(List<String> expression) {
            try {
                int firstOperand = checkMaxValue(Integer.parseInt(expression.get(0)), "");
                int secondOperand = checkMaxValue(Integer.parseInt(expression.get(2)), "");

                return String.valueOf(firstOperand / secondOperand);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format(NUMBER_NOT_EXIST, e.getMessage()));
            }
        }
    }
}
