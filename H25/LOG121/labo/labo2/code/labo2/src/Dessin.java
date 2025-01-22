import java.util.ArrayList;
import java.util.List;

public class Dessin implements ComposantDessin {
    private String nom;
    private List<ComposantDessin> enfants = new ArrayList<>();

    public Dessin(String nom) {
        this.nom = nom;
    }

    public void ajouterEnfant(ComposantDessin enfant) {
        enfants.add(enfant);
    }

    public void supprimerEnfant(ComposantDessin enfant) {
        enfants.remove(enfant);
    }

    @Override
    public void afficher(AffichageStrategie strategy, int niveau, String chemin) {
        if (niveau > 3) { // Stop recursion after depth 3
            return;
        }

        strategy.afficher(this, niveau, chemin);
        for (ComposantDessin enfant : enfants) {
            enfant.afficher(strategy, niveau + 1, enfant.getNom());
        }
    }



    @Override
    public String getNom() {
        return nom;
    }

    public List<ComposantDessin> getEnfants() {
        return enfants;
    }
}

