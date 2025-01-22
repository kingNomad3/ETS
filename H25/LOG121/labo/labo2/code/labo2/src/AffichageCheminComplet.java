public class AffichageCheminComplet implements AffichageStrategie {
    @Override
    public void afficher(ComposantDessin composant, int niveau, String chemin) {
        System.out.println(chemin);

        if (composant instanceof Dessin) {
            Dessin dessin = (Dessin) composant;
            for (ComposantDessin enfant : dessin.getEnfants()) {
                enfant.afficher(this, niveau + 1, chemin + "." + enfant.getNom());
            }
        }
    }
}

