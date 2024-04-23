package ru.test.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.calculator.models.CalculatorModel;
import ru.test.calculator.views.CalculatorView;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class CalculatorController {
    private final CalculatorView view;
    private final CalculatorModel model;
    private static final String FILTER_REGEX = "[^0-9*//+-.,()]"; // Possible characters

    @Autowired
    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;

        this.view.filterListener(new FilterListener());
        this.view.calculationListener(new CalculateListener());
        this.view.clearListener(new ClearListener());
        this.view.otherButtonsListener(new OtherButtonsListener());
    }

    class FilterListener extends DocumentFilter {

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            text = text.replaceAll(FILTER_REGEX, "");
            super.replace(fb, offset, length, text, attrs);
        }
    }

    class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                view.setDisplayValue(model.calculate(view.getDisplayValue()));
            } catch (Exception exception) {
                view.displayErrorMessage("Unsupported expression.");
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.setDisplayValue("");
        }
    }

    class OtherButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.setDisplayValue(view.getDisplayValue() + ((JButton) actionEvent.getSource()).getText());
        }
    }
}
