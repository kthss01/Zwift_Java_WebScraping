package intellij_swing_practice.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Calculator {

    private double total1 = 0.0;
    private double total2 = 0.0;
    private char math_operator;

    private JPanel calculatorPanel;
    private JTextField textDisplay;
    private JButton btnNine;
    private JButton btnSix;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnFour;
    private JButton btnSeven;
    private JButton btnZero;
    private JButton btnThree;
    private JButton btnFive;
    private JButton btnEight;
    private JButton btnPoint;
    private JButton btnPlus;
    private JButton btnDivide;
    private JButton btnMinus;
    private JButton btnMultiply;
    private JButton btnEquals;
    private JButton btnClear;

    public Calculator() {
        ArrayList<JButton> btnNums = new ArrayList<>();
        btnNums.add(btnOne);
        btnNums.add(btnTwo);
        btnNums.add(btnThree);
        btnNums.add(btnFour);
        btnNums.add(btnFive);
        btnNums.add(btnSix);
        btnNums.add(btnSeven);
        btnNums.add(btnEight);
        btnNums.add(btnNine);
        btnNums.add(btnZero);

        ArrayList<JButton> btnOps = new ArrayList<>();
        btnOps.add(btnPlus);
        btnOps.add(btnMinus);
        btnOps.add(btnDivide);
        btnOps.add(btnMultiply);

        for (JButton btnNum : btnNums) {
            btnNum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String btnNumText = textDisplay.getText() + btnNum.getText();
                    textDisplay.setText(btnNumText);
                }
            });
        }

        for (JButton btnOp : btnOps) {
            btnOp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String btn_text = btnOp.getText();
                    getOperator(btn_text);
                }
            });
        }

        btnEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (math_operator) {
                    case '+':
                        total2 = total1 + Double.parseDouble(textDisplay.getText());
                        break;
                    case '-':
                        total2 = total1 - Double.parseDouble(textDisplay.getText());
                        break;
                    case '*':
                        total2 = total1 * Double.parseDouble(textDisplay.getText());
                        break;
                    case '/':
                        total2 = total1 / Double.parseDouble(textDisplay.getText());
                        break;
                }

                textDisplay.setText(Double.toString(total2));
                total1 = 0;
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total2 = 0;
                textDisplay.setText("");
            }
        });
        btnPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will make it neater - "0." insteadof "."
                if (textDisplay.getText().equals("")) {
                    textDisplay.setText("0.");
                }
                // This will help to avoid a bug,
                // `cause ".." as well as "1.." or ".8." is not acceptable
                // for our simple calculator, so we make it impossible
                else if (textDisplay.getText().contains(".")) {
                    btnPoint.setEnabled(false);
                } else {
                    String btnPointText = textDisplay.getText() + btnPoint.getText();
                    textDisplay.setText(btnPointText);
                }
                btnPoint.setEnabled(true);
            }
        });
    }

    private void getOperator(String btnText) {
        math_operator = btnText.charAt(0);
        total1 = total1 + Double.parseDouble(textDisplay.getText());
        textDisplay.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorPanel);
        frame.setLocationRelativeTo(null); // 프레임 위치 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
