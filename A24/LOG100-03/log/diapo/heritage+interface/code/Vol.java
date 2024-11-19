public class Vol {

    private String numVol;
    private int numPlaces;
    private String Destination;

    public Vol(String numVol, int numPlaces, String destination) {
        this.numVol = numVol;
        this.numPlaces = numPlaces;
        Destination = destination;
    }

    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getNumVol() {
        return numVol;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public String getDestination() {
        return Destination;
    }

    public void AfficherDetails(){
        System.out.println("Le num de vol est: " + getNumVol()+ ", la destination: "
                + getDestination() + ", les places disponibles: " + getNumPlaces());


    }
}