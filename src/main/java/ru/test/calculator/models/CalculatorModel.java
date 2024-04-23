package ru.test.calculator.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.calculator.api.Calculator;

@Component
public class CalculatorModel {
    private final Calculator calculator;

    @Autowired
    public CalculatorModel(Calculator calculator) {
        this.calculator = calculator;
    }

    public String calculate(String displayValue) {
        return calculator.calculate(displayValue).replaceAll("\\.?0*$", "");
    }
}
