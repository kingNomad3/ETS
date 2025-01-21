public interface ComposantDessin {

    void afficher(AffichageStrategie strategy, int niveau, String chemin);
    String getNom();
}
