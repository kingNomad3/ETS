public class Triangle extends Figure{

    public String nom;

    public Triangle(String nom){
        super(25.0, 42.0);
        this.nom = nom;
    }
    public double calculerAire(){
        return (hauteur * longueur)/2;
    }

    public void afficher(){
        super.afficher();
        System.out.println("Le nom est: " + nom);
    }
}