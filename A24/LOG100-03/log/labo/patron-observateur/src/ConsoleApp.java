import java.util.Scanner;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ConsoleApp {
  
  /*
   * Simulation attributes
   * DO NOT EDIT
   */
  
  private Scanner scan = new Scanner(System.in);
  
	private Airport airport = new Airport();
	private Terminal termA = new Terminal("TERMINAL A");
	private Terminal termB = new Terminal("TERMINAL B");
	private Terminal termC = new Terminal("TERMINAL C");
	private Gate[] gatesA = new Gate[3];
	private Gate[] gatesB = new Gate[7];
	private Gate[] gatesC = new Gate[5];
  
  /**
   * Creates the objects used for the simulation.
   * DO NOT EDIT
   */
  public void createObjects() {
    
    // Airport screens
    
    AirportScreen airportScreen1 = new AirportScreen(airport, "AIRPORT (1)");
    AirportScreen airportScreen2 = new AirportScreen(airport, "AIRPORT (2)");
    airport.attach(airportScreen1);
    airport.attach(airportScreen2);
    
    // Terminal screens (three each)
    
    TerminalScreen termAScreen1 = new TerminalScreen(termA, "TERMINAL A (1)");
    TerminalScreen termAScreen2 = new TerminalScreen(termA, "TERMINAL A (2)");
    TerminalScreen termAScreen3 = new TerminalScreen(termA, "TERMINAL A (3)");
    termA.attach(termAScreen1);
    termA.attach(termAScreen2);
    termA.attach(termAScreen3);
    
    TerminalScreen termBScreen1 = new TerminalScreen(termB, "TERMINAL B (1)");
    TerminalScreen termBScreen2 = new TerminalScreen(termB, "TERMINAL B (2)");
    TerminalScreen termBScreen3 = new TerminalScreen(termB, "TERMINAL B (3)");
    termB.attach(termBScreen1);
    termB.attach(termBScreen2);
    termB.attach(termBScreen3);
    
    TerminalScreen termCScreen1 = new TerminalScreen(termC, "TERMINAL C (1)");
    TerminalScreen termCScreen2 = new TerminalScreen(termC, "TERMINAL C (2)");
    TerminalScreen termCScreen3 = new TerminalScreen(termC, "TERMINAL C (3)");
    termC.attach(termCScreen1);
    termC.attach(termCScreen2);
    termC.attach(termCScreen3);
    
    // Gates and gate screens
    
    // Terminal A
    for(int i = 0; i < gatesA.length; ++i) {
      gatesA[i] = new Gate("A-" + (i + 1));
      gatesA[i].attach(new GateScreen(gatesA[i]));
    }
    
    // Terminal B
    for(int i = 0; i < gatesB.length; ++i) {
      gatesB[i] = new Gate("B-" + (i + 1));
      gatesB[i].attach(new GateScreen(gatesB[i]));
    }
    
    // Terminal C
    for(int i = 0; i < gatesC.length; ++i) {
      gatesC[i] = new Gate("C-" + (i + 1));
      gatesC[i].attach(new GateScreen(gatesC[i]));
    }
  }
  
  /**
   * Adds a new flight based on user-provided information.
   */
  public void addFlight() {
    
    // Collect flight information from the console
    
    System.out.print("Company: ");
    String company = scan.next();
    
    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();
    
    System.out.print("Destination: ");
    String destination = scan.next();
    
    System.out.print("Departure Time (0000): ");
    int departureTime = scan.nextInt();
    
    System.out.print("Terminal (A, B, C): ");
    String terminal = scan.next();
    
    System.out.print("Gate Number: ");
    int gateNumber = scan.nextInt();
    
    System.out.println("Status:");
    System.out.println("1 - " + Flight.ONTIME);
    System.out.println("2 - " + Flight.CANCELLED);
    System.out.println("3 - " + Flight.BOARDING);
    System.out.println("4 - " + Flight.DELAYED);
    int statusInt = scan.nextInt();
    String statusStr = "";
    switch(statusInt) {
      case 1: statusStr = Flight.ONTIME; break;
      case 2: statusStr = Flight.CANCELLED; break;
      case 3: statusStr = Flight.BOARDING; break;
      case 4: statusStr = Flight.DELAYED; break;
    }
    
    // Create an instance of Flight
    Flight flight = new Flight(company, flightNumber, destination,
      departureTime, terminal + "-" + gateNumber, statusStr);
    
    // Add it to the airport's list of flights
    airport.addFlight(flight);
    
    // Add it to the appropriate terminal and gate
    switch(terminal) {
      case "A":
        termA.addFlight(flight);
        gatesA[gateNumber - 1].addFlight(flight);
        break;
      case "B":
        termB.addFlight(flight);
        gatesB[gateNumber - 1].addFlight(flight);
        break;
      case "C":
        termC.addFlight(flight);
        gatesC[gateNumber - 1].addFlight(flight);
        break;        
    }
  }
  
  /*
   * TODO: Implement the following methods. You may add other methods as well.
   */
  
  /**
 * Delays a flight by updating its status across the airport, terminals, and gates.
 * The method prompts the user for flight details (company and flight number) and 
 * updates the status of the flight to "DELAYED" in relevant components.
 * 
 * @throws InputMismatchException if the user enters invalid input for company or flight number.
 */
 public void delayFlight() {

    System.out.print("Company: ");
    String company = scan.next();
    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();

    boolean flightFound = false;

    for (Flight flight : airport.getFlights()) {
        if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
            flight.setStatus(Flight.DELAYED);
            airport.notifyObservers();
            flightFound = true;
        }
    }

    Terminal[] terminals = {termA, termB, termC};
    for (Terminal terminal : terminals) {
        for (Flight flight : terminal.getFlights()) {
            if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                flight.setStatus(Flight.DELAYED);
                terminal.notifyObservers();
                flightFound = true;
            }
        }
    }

    Gate[][] allGates = {gatesA, gatesB, gatesC};
    for (Gate[] gates : allGates) {
        for (Gate gate : gates) {
            for (Flight flight : gate.getFlights()) {
                if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                    flight.setStatus(Flight.DELAYED);
                    gate.notifyObservers();
                    flightFound = true;
                }
            }
        }
    }

    if (!flightFound) {
        System.out.println("Flight not found.");
    }
}

/**
 * Changes the gate for a specific flight by updating its gate information in the 
 * relevant terminal and gate components. The method ensures that the requested gate 
 * is available before making the change, and it notifies the necessary observers.
 * 
 * @throws InputMismatchException if the user enters invalid input for company, flight number, 
 *         terminal, or gate number.
 */
public void changeGate() {
    System.out.print("Company: ");
    String company = scan.next();

    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();

    System.out.print("Terminal (A, B, C): ");
    String terminal = scan.next();

    System.out.print("Gate Number: ");
    int gateNumber = scan.nextInt();

    List<Map.Entry<Flight, Object>> flightResult = new ArrayList<>();

    List<Object> components = new ArrayList<>();
    components.add(airport); 
    components.add(termA);   
    components.add(termB);   
    components.add(termC);   
    components.addAll(Arrays.asList(gatesA));  
    components.addAll(Arrays.asList(gatesB));  
    components.addAll(Arrays.asList(gatesC));  

    for (Object component : components) {
        if (component instanceof Airport) {
            for (Flight flight : airport.getFlights()) {
                if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                    flightResult.add(Map.entry(flight, airport));
                }
            }
        } else if (component instanceof Terminal) {
            Terminal terminalComponent = (Terminal) component;
            for (Flight flight : terminalComponent.getFlights()) {
                if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                    flightResult.add(Map.entry(flight, terminalComponent));
                }
            }
        } else if (component instanceof Gate) {
            Gate gateComponent = (Gate) component;
            for (Flight flight : gateComponent.getFlights()) {
                if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                    flightResult.add(Map.entry(flight, gateComponent));
                }
            }
        }
    }

    if (flightResult.isEmpty()) {
        System.out.println("Flight not found.");
        return;
    }

    Map<String, Gate[]> terminalGatesMap = new HashMap<>();
    terminalGatesMap.put("A", gatesA);
    terminalGatesMap.put("B", gatesB);
    terminalGatesMap.put("C", gatesC);

    Gate[] GateCheckChangeCheck = terminalGatesMap.get(terminal);
    boolean gateTaken = false;

    if (GateCheckChangeCheck != null) {
        for (Gate g : GateCheckChangeCheck) {
            int gateNum = Integer.parseInt(g.getGateName().split("-")[1]);
            if (gateNum == gateNumber && !g.getFlights().isEmpty()) {
                gateTaken = true;
                break;
            }
        }
    }

    if (gateTaken) {
        System.out.println("New gate " + terminal + "-" + gateNumber + " is not available");
        return;
    }

    boolean differentTerminal = false;

    for (Map.Entry<Flight, Object> entry : flightResult) {
        Flight flight = entry.getKey();
        Object component = entry.getValue();
        String currentTerminal = flight.getGate().split("-")[0];

        if (!currentTerminal.equals(terminal)) {
            if (component instanceof Terminal) {
                ((Terminal) component).removeFlight(flight);
                differentTerminal = true;
            }
        }

        if (component instanceof Gate) {
            ((Gate) component).removeFlight(flight);
        }
    }

    for (Map.Entry<Flight, Object> entry : flightResult) {
        Flight flight = entry.getKey();
        Object component = entry.getValue();

        flight.setGate(terminal + "-" + gateNumber);

        if (component instanceof Airport && ((Airport) component).getFlights().size() > 0) {
            ((Airport) component).notifyObservers();
        }

        if (differentTerminal && component instanceof Terminal) {
            Terminal terminalComponent = (Terminal) component;
            switch (terminal) {
                case "A":
                    termA.addFlight(flight);
                    break;
                case "B":
                    termB.addFlight(flight);
                    break;
                case "C":
                    termC.addFlight(flight);
                    break;
            }
        }

        if (component instanceof Terminal && ((Terminal) component).getFlights().size() > 0) {
            ((Terminal) component).notifyObservers();
        }

        if (component instanceof Gate) {
            switch (terminal) {
                case "A":
                    gatesA[gateNumber - 1].addFlight(flight);
                    break;
                case "B":
                    gatesB[gateNumber - 1].addFlight(flight);
                    break;
                case "C":
                    gatesC[gateNumber - 1].addFlight(flight);
                    break;
            }

            if (((Gate) component).getFlights().size() > 0) {
                ((Gate) component).notifyObservers();
            }
        }
    }
}


/**
 * Cancels a flight by setting its status to "CANCELLED" and notifying all relevant 
 * components (airport, terminals, gates). It updates the system's status across all components 
 * where the flight is located.
 * 
 * @throws InputMismatchException if the user enters invalid input for company or flight number.
 */
public void cancelFlight() {
    System.out.print("Company: ");
    String company = scan.next();

    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();

    List<Map.Entry<Flight, Object>> flightResult = new ArrayList<>();

    List<Object> components = new ArrayList<>();
    components.add(airport);
    components.addAll(Arrays.asList(termA, termB, termC));
    components.addAll(Arrays.asList(gatesA));
    components.addAll(Arrays.asList(gatesB));
    components.addAll(Arrays.asList(gatesC));

    for (Object component : components) {
        Collection<Flight> flights = null;
        if (component instanceof Airport) {
            flights = ((Airport) component).getFlights();
        } else if (component instanceof Terminal) {
            flights = ((Terminal) component).getFlights();
        } else if (component instanceof Gate) {
            flights = ((Gate) component).getFlights();
        }

        if (flights == null) continue;

        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                flightResult.add(Map.entry(flight, component));
            }
        }
    }

    if (flightResult.isEmpty()) {
        System.out.println("Flight not found.");
        return;
    }

    for (Map.Entry<Flight, Object> entry : flightResult) {
        Flight flight = entry.getKey();
        Object component = entry.getValue();

        flight.setStatus(Flight.CANCELLED);

        if (component instanceof Airport) {
            ((Airport) component).notifyObservers();
        } else if (component instanceof Terminal) {
            ((Terminal) component).notifyObservers();
        } else if (component instanceof Gate) {
            ((Gate) component).notifyObservers();
        }
    }
}


 /**
 * Notifies all relevant components that a specific flight is ready for boarding by 
 * changing its status to "BOARDING" and triggering notifications to the airport, terminals, 
 * and gates where the flight is located.
 * 
 * @throws InputMismatchException if the user enters invalid input for company or flight number.
 */
public void notifyBoarding() {
    System.out.print("Company: ");
    String company = scan.next();

    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();

    List<Map.Entry<Flight, Object>> flightResult = new ArrayList<>();

    List<Object> components = new ArrayList<>();
    components.add(airport); 
    components.addAll(Arrays.asList(termA, termB, termC)); 
    components.addAll(Arrays.asList(gatesA)); 
    components.addAll(Arrays.asList(gatesB)); 
    components.addAll(Arrays.asList(gatesC)); 

    for (Object component : components) {
        Collection<Flight> flights = null;
        if (component instanceof Airport) {
            flights = ((Airport) component).getFlights();
        } else if (component instanceof Terminal) {
            flights = ((Terminal) component).getFlights();
        } else if (component instanceof Gate) {
            flights = ((Gate) component).getFlights();
        }

        if (flights == null) continue;

        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                flightResult.add(Map.entry(flight, component));
            }
        }
    }

    if (flightResult.isEmpty()) {
        System.out.println("Flight not found.");
        return;
    }

    for (Map.Entry<Flight, Object> entry : flightResult) {
        Flight flight = entry.getKey();
        Object component = entry.getValue();

        flight.setStatus(Flight.BOARDING);

        if (component instanceof Airport) {
            ((Airport) component).notifyObservers();
        } else if (component instanceof Terminal) {
            ((Terminal) component).notifyObservers();
        } else if (component instanceof Gate) {
            ((Gate) component).notifyObservers();
        }
    }
}

/**
 * Removes a flight from the system by finding it across the airport, terminals, and gates, 
 * and then removing it from each relevant component. It ensures that the flight is properly 
 * removed from all locations in the system.
 * 
 * @throws InputMismatchException if the user enters invalid input for company or flight number.
 */
public void removeFlight() {
    System.out.print("Company: ");
    String company = scan.next();

    System.out.print("Flight Number: ");
    int flightNumber = scan.nextInt();

    List<Map.Entry<Flight, Object>> flightResult = new ArrayList<>();

    List<Object> components = new ArrayList<>();
    components.add(airport); 
    components.addAll(Arrays.asList(termA, termB, termC)); 
    components.addAll(Arrays.asList(gatesA)); 
    components.addAll(Arrays.asList(gatesB)); 
    components.addAll(Arrays.asList(gatesC)); 

    for (Object component : components) {
        Collection<Flight> flights = null;
        if (component instanceof Airport) {
            flights = ((Airport) component).getFlights();
        } else if (component instanceof Terminal) {
            flights = ((Terminal) component).getFlights();
        } else if (component instanceof Gate) {
            flights = ((Gate) component).getFlights();
        }

        if (flights == null) continue;

        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber && flight.getCompany().equals(company)) {
                flightResult.add(Map.entry(flight, component));
            }
        }
    }

    if (flightResult.isEmpty()) {
        System.out.println("Flight not found.");
        return;
    }

    for (Map.Entry<Flight, Object> entry : flightResult) {
        Flight flight = entry.getKey();
        Object component = entry.getValue();

        if (component instanceof Airport) {
            ((Airport) component).removeFlight(flight);
        } else if (component instanceof Terminal) {
            ((Terminal) component).removeFlight(flight);
        } else if (component instanceof Gate) {
            ((Gate) component).removeFlight(flight);
        }
    }
}



  
  /**
   * Displays the options available in the console.
   * DO NOT EDIT
   */
  public void displayMenu() {
    System.out.println("********************");
    System.out.println("1 - Add Flight");
    System.out.println("2 - Delay Flight");
    System.out.println("3 - Change Gate");
    System.out.println("4 - Cancel Flight");
    System.out.println("5 - Notify Boarding");
    System.out.println("6 - Remove Flight");
    System.out.println("0 - Quit");
    System.out.println("********************");
  }
  
  /**
   * Displays the choice menu, waits for the user input, and calls the appropriate method.
   * DO NOT EDIT
   */
  public void displayPrompt() {
    int option = 0;
    
    do {
      displayMenu();
      System.out.print("Select Option: ");
      option = scan.nextInt();
      
      switch(option) {
        case 1: addFlight(); break;
        case 2: delayFlight(); break;
        case 3: changeGate(); break;
        case 4: cancelFlight(); break;
        case 5: notifyBoarding(); break;
        case 6: removeFlight(); break;
        case 0: scan.close(); break;
        default:
          System.out.println("Error - stop");
          scan.close();
          return;
      }
    } while (option != 0); // While the option is not Quit
  }
  
  /**
   * Creates all the objects and starts the simulation.
   * DO NOT EDIT
   */
  public static void main(String[] args) {
    ConsoleApp app = new ConsoleApp();
    
    // Object setup
    app.createObjects();
    
    // Start the simulation
    app.displayPrompt();
  }

}
