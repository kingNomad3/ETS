public class ReservationPremium extends Reservation{

    private String privilege;

    public ReservationPremium(String nomPassager, int nomPlace, Vol vol, String priv) {
        super(nomPassager, nomPlace, vol);
        privilege = priv;

    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void AfficherDetails(){
        super.AfficherDetails();
        System.out.println("Privilege: "+ getPrivilege());
    }
}