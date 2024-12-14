import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeGateDialog extends JDialog {
    private JTextField companyField, flightNumberField, terminalField, gateNumberField;
    private JButton okButton, cancelButton;

    public ChangeGateDialog(GuiApp app) {
        super(app, "Change Gate", true);

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Company:"));
        companyField = new JTextField();
        add(companyField);

        add(new JLabel("Flight Number:"));
        flightNumberField = new JTextField();
        add(flightNumberField);

        add(new JLabel("Terminal:"));
        terminalField = new JTextField();
        add(terminalField);

        add(new JLabel("Gate Number:"));
        gateNumberField = new JTextField();
        add(gateNumberField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        okButton.addActionListener(e -> {
            String company = companyField.getText();
            int flightNumber = Integer.parseInt(flightNumberField.getText());
            String terminal = terminalField.getText();
            int gateNumber = Integer.parseInt(gateNumberField.getText());

            boolean success = app.changeGate(company, flightNumber, terminal, gateNumber);
            if (success) {
                JOptionPane.showMessageDialog(this, "Gate changed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Flight not found or gate not available.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
        });

        cancelButton.addActionListener(e -> setVisible(false));

        setSize(350, 200);
        setLocationRelativeTo(app);
    }
}
