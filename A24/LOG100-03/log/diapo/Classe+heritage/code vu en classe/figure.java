public class Figure {

    public double longueur;
    public double hauteur;

    public Figure(double longu, double haut){
        this.longueur = longu; this.hauteur = haut;
    }
    public void afficher(){
        System.out.println("Longueur est: " + this.longueur);
        System.out.println("Hauteur est: " + this.hauteur);
    }
}