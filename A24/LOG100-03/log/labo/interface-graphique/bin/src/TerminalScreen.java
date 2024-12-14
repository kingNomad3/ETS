public class TerminalScreen extends Observer {
  private final Terminal terminal;
  private final String screenName;

  public TerminalScreen(Terminal terminal, String screenName, ScreenDialog termAScreen1Dialog) {
    this.terminal = terminal;
    this.screenName = screenName;
  }

  public void initialize() {
    terminal.attach(this);
  }

  @Override
  public void update() {
    System.out.println(screenName);

    for (Flight flight : terminal.getFlights()) {
      System.out.println(flight);
    }
  }
}
