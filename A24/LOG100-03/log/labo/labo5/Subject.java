import java.util.LinkedList;
import java.util.List;

//ne pas changer
public abstract class Subject {
  
  final private List<Observer> listObservers = new LinkedList<>();
  
  public void attach(Observer o) {
    listObservers.add(o);
  }
  
  public void detach(Observer o) {
    listObservers.remove(o);
  }
  
  public void notifyObservers() {
    for (Observer obv : listObservers) {
      obv.update();
    }
  }
  
  public abstract void addFlight(Flight flight);
  public abstract void removeFlight(Flight flight);
  public abstract List<Flight> getFlights();
}
