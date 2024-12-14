import java.util.ArrayList;
import java.util.List;

public class Terminal extends Subject{
  final private String terminalName;
  final private List<Flight> flights;

  public Terminal(String terminalName){
    this.terminalName = terminalName;
    this.flights = new ArrayList<>();
  }

  @Override
  public void addFlight(Flight flight){
    flights.add(flight);
    notifyObservers();
  }

  @Override
  public void removeFlight(Flight flight){
    flights.remove(flight);
    notifyObservers();
  }

  @Override
  public List<Flight> getFlights(){
    return flights;
  }

  public String getTerminalName(){
    return terminalName;
  }

}