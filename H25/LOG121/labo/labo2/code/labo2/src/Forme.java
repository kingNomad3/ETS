

public abstract class Forme implements ComposantDessin {
    private String nom;

    public Forme(String nom) {
        this.nom = nom;
    }

    @Override
    public void afficher(AffichageStrategie strategy, int niveau, String chemin) {
        strategy.afficher(this, niveau, chemin);
    }


    @Override
    public String getNom() {
        return nom;
    }
}

