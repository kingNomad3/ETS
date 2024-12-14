import java.util.List;

public class AirportScreen extends Observer {
  private final Airport airport;
  private final String screenName;
  private final ScreenDialog screenDialog;

  public AirportScreen(Airport airport, String screenName, ScreenDialog screenDialog) {
    this.airport = airport;
    this.screenName = screenName;
    this.screenDialog = screenDialog;
  }

  public void initialize() {
    airport.attach(this);
  }

  @Override
  public void update() {
    List<Flight> flights = airport.getFlights();
    displayFlights(flights, screenDialog);
  }
}
