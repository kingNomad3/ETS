import java.util.List;

public class GateScreen extends Observer {
  private final Gate gate;
  private final ScreenDialog screenDialog;

  public GateScreen(Gate gate, ScreenDialog screenDialog) {
    this.gate = gate;
    this.screenDialog = screenDialog;
  }

  public void initialize() {
    gate.attach(this);
  }

  @Override
  public void update() {
    List<Flight> flights = gate.getFlights();
    displayFlights(flights, screenDialog);
  }
}
