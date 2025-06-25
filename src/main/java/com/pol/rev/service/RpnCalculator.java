package com.pol.rev.service;

import java.util.ArrayDeque;
import java.util.Deque;

public class RpnCalculator {

    private RpnCalculator() {
    }

    public static double evaluate(String expression) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String token : expression.trim().split("\\s+")) {
            if (isOperator(token)) {
                processOperator(stack, token);
            } else if (token.equals("sqrt")) {
                processSqrt(stack);
            } else {
                processNumber(stack, token);
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
