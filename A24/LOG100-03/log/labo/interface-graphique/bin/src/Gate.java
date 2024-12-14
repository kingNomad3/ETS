import java.util.ArrayList;
import java.util.List;

public class Gate extends Subject{
  final private String gateName;
  final private List<Flight> flights;

  public Gate(String gateName){
    this.gateName = gateName;
    this.flights = new ArrayList<>();
  }

  @Override
  public void addFlight(Flight flight) {
    flights.clear(); 
    flights.add(flight);
    notifyObservers(); 

  }

  @Override
  public void removeFlight(Flight flight){
    flights.remove(flight);
    notifyObservers();
  }

  public String getGateName(){
    return gateName;
  }

  @Override
  public List<Flight> getFlights(){
    return flights;
  }




}