package com.pol.rev.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RpnCalculatorTest {

    @Test
    void testAddition_1_0_2_0_Plus() {
        assertEquals(3.0, RpnCalculator.evaluate("1.0 2.0 +"), "1.0 2.0 + should equal 3.0");
    }

    @Test
    void testMultiplication_3_4_Star() {
        assertEquals(12.0, RpnCalculator.evaluate("3 4 *"), "3 4 * should equal 12.0");
    }

    @Test
    void testComplexExpression_6_3_Star_2_Minus_Sqrt() {
        assertEquals(4.0, RpnCalculator.evaluate("6 3 * 2 - sqrt"), "6 3 * 2 - sqrt should equal 4.0");
    }

    @Test
    void testInvalidExpression_1_Plus_1() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate("1 + 1"));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage(),
                "1 + 1 should throw 'Not Reverse Polish Notation try backwards'");
    }

    @Test
    void testSubtractionAndMultiplication_4_2_Minus_2_Minus_1000_Star() {
        assertEquals(0.0, RpnCalculator.evaluate("4 2 - 2 - 1000 *"),
                "4 2 - 2 - 1000 * should equal 0.0");
    }
    @Test
    void testSingleNumber() {
        assertEquals(5.0, RpnCalculator.evaluate("5"), "Single number 5 should return 5.0");
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-1.0, RpnCalculator.evaluate("-2 1 +"), "-2 + 1 should equal -1.0");
    }

    @Test
    void testDecimalNumbers() {
        assertEquals(0.5, RpnCalculator.evaluate("1.5 1.0 -"), "1.5 - 1.0 should equal 0.5");
    }

    @Test
    void testSqrtOfZero() {
        assertEquals(0.0, RpnCalculator.evaluate("0 sqrt"), "sqrt(0) should equal 0.0");
    }

    @Test
    void testInvalidExpression() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate("1 + 1"));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage());
    }


    @Test
    void testInvalidToken() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate("1 abc +"));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage());
    }


    @Test
    void testDivisionByZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate("4 0 /"));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testSqrtWithEmptyStack() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> RpnCalculator.evaluate("sqrt"));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage(),
                "sqrt with empty stack should throw 'Not Reverse Polish Notation try backwards'");
    }

    @Test
    void testMultipleOperands_5_10_15_20() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RpnCalculator.evaluate("5 10 15 20"));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage(),
                "5 10 15 20 should throw 'Not Reverse Polish Notation try backwards' due to multiple operands");
    }
    @Test
    void testEmptyInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RpnCalculator.evaluate(""));
        assertEquals("Not Reverse Polish Notation try backwards", exception.getMessage());
    }
}
