import java.util.ArrayList;
import java.util.List;


public class Airport extends Subject{
  final private List<Flight> flights;

  public Airport(){
    flights = new ArrayList<>();
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
}