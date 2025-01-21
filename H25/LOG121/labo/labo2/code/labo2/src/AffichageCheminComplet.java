public class AffichageCheminComplet implements AffichageStrategie {
    @Override
    public void afficher(ComposantDessin composant, int niveau, String chemin) {
        // Print the current path
        System.out.println(chemin);

        // If the component is a Dessin, recursively process its children
        if (composant instanceof Dessin) {
            Dessin dessin = (Dessin) composant;
            for (ComposantDessin enfant : dessin.getEnfants()) {
                // Pass the full path for each child
                enfant.afficher(this, niveau + 1, chemin + "." + enfant.getNom());
            }
        }
    }
}

