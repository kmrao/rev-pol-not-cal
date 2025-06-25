package com.pol.rev.service;

import java.util.ArrayDeque;
import java.util.Deque;

public class RpnCalculator {

    private RpnCalculator() {
    }

    public static double evaluate(String expression) {
        Deque<Double> stack = new ArrayDeque<>();
        //Split the expression into Tokens by whitespaces
        for (String token : expression.trim().split("\\s+")) {
            //Check the Token is operator or not. If operator then process the operator
            if (isOperator(token)) {
                processOperator(stack, token);
            } else if (token.equals("sqrt")) {  //If token is sqrt then process sqrt
                processSqrt(stack);
            } else {
                processNumber(stack, token); //Process the number
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Not Reverse Polish Notation try backwards");
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static void processOperator(Deque<Double> stack, String operator) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Not Reverse Polish Notation try backwards");
        }
        double b = stack.pop();
        double a = stack.pop();
        /**
         *  if operator  equal to  + then return a+b
         *  if operator  equal to  + then return a-b
         *  if operator  equal to  * then return a*b
         *  if operator  equal to  / then return a/b
         */
        double result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new IllegalArgumentException("Division by zero");
                yield a / b;
            }
            default -> throw new IllegalStateException("Unknown operator: " + operator);
        };
        //Push the result to stack.
        stack.push(result);
    }

    private static void processSqrt(Deque<Double> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Not Reverse Polish Notation try backwards");
        }
        stack.push(Math.sqrt(stack.pop()));
    }

    private static void processNumber(Deque<Double> stack, String token) {
        try {
            stack.push(Double.parseDouble(token));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not Reverse Polish Notation try backwards");
        }
    }
}
