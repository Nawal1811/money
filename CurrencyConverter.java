/**
 * @author Nawal Shahid
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

public class CurrencyConverter {
    private JFrame frame;
    private JLabel resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JButton convertButton;

    private static final String[] currencies = {"USD", "INR"}; 
    private static final double[] exchangeRates = {1.0, 82.96}; 
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CurrencyConverter() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 2));

        resultLabel = new JLabel("Converted Amount: ");
        amountField = new JTextField();
        fromCurrencyBox = new JComboBox<>(currencies);
        toCurrencyBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.add(new JLabel("Amount: "));
        frame.add(amountField);
        frame.add(new JLabel("From Currency: "));
        frame.add(fromCurrencyBox);
        frame.add(new JLabel("To Currency: "));
        frame.add(toCurrencyBox);
        frame.add(new JLabel()); // Empty label for layout purposes
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amountUSD = Double.parseDouble(amountField.getText());
            int fromCurrencyIndex = fromCurrencyBox.getSelectedIndex();
            int toCurrencyIndex = toCurrencyBox.getSelectedIndex();
            double convertedAmount = amountUSD * (exchangeRates[toCurrencyIndex] / exchangeRates[fromCurrencyIndex]);
            resultLabel.setText("Converted Amount: " + decimalFormat.format(convertedAmount));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}

