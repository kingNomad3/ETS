import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelFlightDialog extends JDialog {
    private JTextField companyField, flightNumberField;
    private JButton okButton, cancelButton;

    public CancelFlightDialog(GuiApp app) {
        super(app, "Cancel Flight", true);

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Company:"));
        companyField = new JTextField();
        add(companyField);

        add(new JLabel("Flight Number:"));
        flightNumberField = new JTextField();
        add(flightNumberField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        okButton.addActionListener(e -> {
            String company = companyField.getText();
            int flightNumber = Integer.parseInt(flightNumberField.getText());

            boolean success = app.cancelFlight(company, flightNumber);
            if (success) {
                JOptionPane.showMessageDialog(this, "Flight cancelled successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Flight not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
        });

        cancelButton.addActionListener(e -> setVisible(false));

        setSize(300, 150);
        setLocationRelativeTo(app);
    }
}
