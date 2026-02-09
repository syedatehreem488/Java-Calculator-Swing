import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber, secondNumber, result;
    private char operator;

    public Calculator() {
        setTitle("Hand-Written Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Display Field =====
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 22));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ((cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') || cmd.equals(".")) {
            display.setText(display.getText() + cmd);
        }
        else if (cmd.equals("C")) {
            display.setText("");
            firstNumber = secondNumber = result = 0;
            operator = '\0';
        }
        else if (cmd.equals("=")) {
            try {
                secondNumber = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = firstNumber + secondNumber; break;
                    case '-': result = firstNumber - secondNumber; break;
                    case '*': result = firstNumber * secondNumber; break;
                    case '/': result = (secondNumber != 0) ? firstNumber / secondNumber : Double.NaN; break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else { // operator
            try {
                firstNumber = Double.parseDouble(display.getText());
                operator = cmd.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
