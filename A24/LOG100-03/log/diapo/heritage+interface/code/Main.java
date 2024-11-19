public class Main {
    public static void main(String[] args) throws Exception {

        Vol v = new Vol("AC750", 2, "Paris");
        Reservation res = new Reservation("Anes", 3, v);
        v.AfficherDetails();
        res.faireReservation();
        v.AfficherDetails();
    }
}