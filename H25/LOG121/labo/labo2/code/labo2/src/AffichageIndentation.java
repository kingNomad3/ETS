public class AffichageIndentation implements AffichageStrategie {
    @Override
    public void afficher(ComposantDessin composant, int niveau, String chemin) {
        // Print indentation based on the level
        System.out.println("  ".repeat(niveau) + chemin);

        // Check if the composant is a Dessin to go deeper into its children
        if (composant instanceof Dessin) {
            Dessin dessin = (Dessin) composant;
            for (ComposantDessin enfant : dessin.getEnfants()) {
                enfant.afficher(this, niveau + 1, enfant.getNom());
            }
        }
    }
}
