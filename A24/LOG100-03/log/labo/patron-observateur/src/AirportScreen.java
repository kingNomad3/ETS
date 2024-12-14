public class AirportScreen extends Observer {
  final private Airport airport;
  final private String screenName;

  public AirportScreen(Airport airport, String screenName) {
    this.airport = airport;
    this.screenName = screenName;
  }

  public void initialize() {
    airport.attach(this);
  }

  @Override
  public void update() {
    System.out.println(screenName);

    for (Flight flight : airport.getFlights()) {
      System.out.println(flight);
    }
  }
}
