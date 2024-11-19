public class Reservation {

    private String nomPassager;
    private int nomPlace;
    private Vol vol;

    public Reservation(String nomPassager, int nomPlace, Vol vol) {
        this.nomPassager = nomPassager;
        this.nomPlace = nomPlace;
        this.vol = vol;
    }

    public void faireReservation() throws MonException{

        try {
            if (this.nomPlace > this.vol.getNumPlaces()){
                throw new MonException("ExceptionDeReservation: ");
            }
            this.vol.setNumPlaces(this.vol.getNumPlaces()-this.nomPlace);
            System.out.println("Reservation effectuée avec succès!");
        }catch(MonException e){
            System.out.println(e.getMessage() + " Pas de places disponibles");
        }
    }

    public void setNomPassager(String nomPassager) {
        this.nomPassager = nomPassager;
    }

    public void setNomPlace(int nomPlace) {
        this.nomPlace = nomPlace;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public String getNomPassager() {
        return nomPassager;
    }

    public int getNomPlace() {
        return nomPlace;
    }

    public Vol getVol() {
        return vol;
    }

    public void AfficherDetails(){
        System.out.println("La passager: "+ getNomPassager() +" a réservé: " + getNomPlace() + " places, pour la destination: "+ vol.getDestination());
    }

}