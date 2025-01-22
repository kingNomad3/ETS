public class AffichageIndentation implements AffichageStrategie {
    @Override
    public void afficher(ComposantDessin composant, int niveau, String chemin) {
        System.out.println("  ".repeat(niveau) + chemin);

        if (composant instanceof Dessin) {
            Dessin dessin = (Dessin) composant;
            for (ComposantDessin enfant : dessin.getEnfants()) {
                enfant.afficher(this, niveau + 1, enfant.getNom());
            }
        }
    }
}
