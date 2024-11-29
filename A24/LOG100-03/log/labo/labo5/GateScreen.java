public class GateScreen extends Observer {
  final private Gate gate;

  public GateScreen(Gate gate) {
    this.gate = gate;
  }

  public void initialize() {
    gate.attach(this);
  }

  @Override
  public void update() {
    System.out.println("GATE " + gate.getGateName());

    for (Flight flight : gate.getFlights()) {
      System.out.println(flight);
    }
  }
}
