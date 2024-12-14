import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddFlightDialog extends JDialog {

  public static final int WIDTH = 300;
  public static final int HEIGHT = 350;

  private GuiApp app;
  private String currentState = null;

  private JButton okButton, cancelButton;
  private JPanel controlPanel;
  private JTextField companyField, flightNumberField, destinationField;
  private JTextField departureTimeField, terminalField, gateNumberField;
  private JComboBox<String> statusBox;

  public AddFlightDialog(GuiApp app) {

    super(app, "Add Flight");
    this.app = app;

    // Create buttons and add their listeners
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          addFlight();
          JOptionPane.showMessageDialog(AddFlightDialog.this, "Flight added successfully.");
          setVisible(false);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(AddFlightDialog.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(
                AddFlightDialog.this,
                "Do you want to cancel?",
                "Cancel",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
          setVisible(false);
        }
      }
    });

    // Create combo box for statuses
    String[] statuses = { Flight.ONTIME, Flight.CANCELLED, Flight.BOARDING, Flight.DELAYED };
    statusBox = new JComboBox<>(statuses);
    statusBox.setSelectedItem(Flight.ONTIME);
    statusBox.setEditable(false);
    statusBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        currentState = (String) statusBox.getSelectedItem();
      }
    });

    // Panels for input fields
    JPanel companyPanel = createInputPanel("Company: ", companyField = new JTextField(10));
    JPanel flightNumberPanel = createInputPanel("Flight Number: ", flightNumberField = new JTextField(10));
    JPanel destinationPanel = createInputPanel("Destination: ", destinationField = new JTextField(10));
    JPanel departureTimePanel = createInputPanel("Departure Time (0000): ", departureTimeField = new JTextField(10));
    JPanel terminalPanel = createInputPanel("Terminal: ", terminalField = new JTextField(10));
    JPanel gateNumberPanel = createInputPanel("Gate Number: ", gateNumberField = new JTextField(10));
    JPanel flightStatusPanel = new JPanel();
    flightStatusPanel.add(new JLabel("Flight Status: "));
    flightStatusPanel.add(statusBox);

    // Control panel for buttons
    controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
    controlPanel.add(okButton);
    controlPanel.add(cancelButton);

    // Set up the dialog layout
    Container c = getContentPane();
    c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
    c.add(companyPanel);
    c.add(flightNumberPanel);
    c.add(destinationPanel);
    c.add(departureTimePanel);
    c.add(terminalPanel);
    c.add(gateNumberPanel);
    c.add(flightStatusPanel);
    c.add(controlPanel);

    setSize(new Dimension(WIDTH, HEIGHT));
    setLocationRelativeTo(app);
  }

  // Helper method to create input panels
  private JPanel createInputPanel(String labelText, JTextField textField) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(labelText));
    panel.add(textField);
    return panel;
  }

  // Method to add flight with input validation
  public void addFlight() {
    // Validate and parse inputs
    String company = companyField.getText().trim();
    if (company.isEmpty()) throw new IllegalArgumentException("Company cannot be empty.");

    int flightNumber;
    try {
      flightNumber = Integer.parseInt(flightNumberField.getText().trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Flight Number must be a valid integer.");
    }

    String destination = destinationField.getText().trim();
    if (destination.isEmpty()) throw new IllegalArgumentException("Destination cannot be empty.");

    int departureTime;
    try {
      departureTime = Integer.parseInt(departureTimeField.getText().trim());
      if (departureTime < 0 || departureTime > 2359) {
        throw new IllegalArgumentException("Departure Time must be between 0000 and 2359.");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Departure Time must be a valid integer.");
    }

    String terminal = terminalField.getText().trim();
    if (!terminal.matches("[ABC]")) throw new IllegalArgumentException("Terminal must be A, B, or C.");

    int gateNumber;
    try {
      gateNumber = Integer.parseInt(gateNumberField.getText().trim());
      if (gateNumber <= 0) {
        throw new IllegalArgumentException("Gate Number must be positive.");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Gate Number must be a valid integer.");
    }

    if (currentState == null) {
      currentState = Flight.ONTIME;
    }
    String statusStr = currentState;

    // Create flight and add to system
    Flight flight = new Flight(
            company, flightNumber, destination, departureTime,
            terminal + "-" + gateNumber, statusStr);

    app.addFlightToAirport(flight);
    app.addFlightToTerminal(flight, terminal);
    app.addFlightToGate(flight, terminal, gateNumber);

    app.appendToDisplayArea("Flight added:\n" + flight);
  }
}
