package com.example.g130ministore.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void sum() {
        int result = calculator.sum(10, 20);
        Assertions.assertEquals(30, result);
    }

    @Test
    void isPositive() {
        boolean result = calculator.isPositive(10);
        Assertions.assertTrue(result);
    }

    @Test
    void divide_success() {
        double result = calculator.divide(20, 2);
        Assertions.assertEquals(10.0, result);
    }

    @Test
    void divide_fail() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.divide(20, 0));
    }
}
