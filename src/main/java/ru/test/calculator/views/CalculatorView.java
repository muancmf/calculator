package ru.test.calculator.views;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CalculatorView extends JFrame {
    private final JTextField display = new JTextField(12);
    private final JButton clearBtn = new JButton("C");
    private final JButton calculateBtn = new JButton("=");
    private final Collection<JButton> otherBtns = new ArrayList<>();

    private static final String[] buttonNames = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "+", "-", "0",
            "*", "/", ",",
            "(", ")"};

    public CalculatorView() {
        JPanel calcPanel = new JPanel();

        this.setSize(300, 280);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcPanel.add(display);
        calcPanel.add(clearBtn);

        for (String btnName : buttonNames) {
            JButton btn = new JButton(btnName);
            this.otherBtns.add(btn);
            calcPanel.add(btn);
        }

        calcPanel.add(calculateBtn);

        this.add(calcPanel);
        this.setVisible(true);
    }

    public String getDisplayValue() {
        return display.getText();
    }

    public void setDisplayValue(String value) {
        display.setText(value);
    }

    public void filterListener(DocumentFilter filter) {
        ((AbstractDocument) this.display.getDocument()).setDocumentFilter(filter);
    }

    public void calculationListener(ActionListener listenerForCalcButton) {
        this.calculateBtn.addActionListener(listenerForCalcButton);
    }

    public void clearListener(ActionListener listenerForClearButton) {
        this.clearBtn.addActionListener(listenerForClearButton);
    }

    public void otherButtonsListener(ActionListener listenerForOtherButtons) {
        for (JButton btn : this.otherBtns) {
            btn.addActionListener(listenerForOtherButtons);
        }
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
