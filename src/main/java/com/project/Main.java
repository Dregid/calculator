package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Main {
    private final static String ILLEGAL_FORMAT = "Получен неверный формат выражения. Пример верного выражения: 1 + 1 или IV + V. Получено: %s\n" +
            "Причина: у математического выражения должно быть только два операнда и один оператор.";
    private final static String DIFFERENT_NUMBER_SYSTEMS = "В выражении используются разные системы счисления: %s. " +
            "Калькулятор умеет работать только с арабскими или римскими цифрами одновременно";
    private final static String OPERATOR_NOT_EXIST = "Передан не допущенный оператор: %s";

    public static void main( String[] args ) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (!(input = reader.readLine()).equals("")) {
                System.out.println(calc(input));
            }
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String calc(String input) {
        List<String> expression = Arrays.asList(input.split(" "));
        boolean isRoman = errorCheckExpressionAndIsRoman(expression, input);
        String operator = expression.get(1);

        return switch (operator) {
            case "+" -> isRoman ? Calculator.Roman.addition(expression) : Calculator.Arabic.addition(expression);
            case "-" -> isRoman ? Calculator.Roman.subtraction(expression) : Calculator.Arabic.subtraction(expression);
            case "*" -> isRoman ? Calculator.Roman.multiplication(expression) : Calculator.Arabic.multiplication(expression);
            case "/" -> isRoman ? Calculator.Roman.division(expression) : Calculator.Arabic.division(expression);
            default -> throw new IllegalArgumentException(String.format(OPERATOR_NOT_EXIST, operator));
        };
    }

    private static boolean errorCheckExpressionAndIsRoman(List<String> expression, String input) {
        if (expression.size() != 3)
            throw new IllegalArgumentException(String.format(ILLEGAL_FORMAT, input));

        Map<String, Integer> romanNumerals = Calculator.Roman.getRomanNumerals();
        String firstNum = expression.get(0);
        String secondNum = expression.get(2);

        if ((romanNumerals.containsKey(String.valueOf(firstNum.charAt(0))) && !romanNumerals.containsKey(String.valueOf(secondNum.charAt(0)))) ||
                (!romanNumerals.containsKey(String.valueOf(firstNum.charAt(0))) && romanNumerals.containsKey(String.valueOf(secondNum.charAt(0)))))
            throw new IllegalArgumentException(String.format(DIFFERENT_NUMBER_SYSTEMS, input));

        return romanNumerals.containsKey(String.valueOf(firstNum.charAt(0)));
    }
}
