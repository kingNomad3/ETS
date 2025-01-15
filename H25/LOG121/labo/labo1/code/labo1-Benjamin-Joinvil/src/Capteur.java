import java.util.ArrayList;
import java.util.List;

public abstract class Capteur implements Sujet {

    private float valeur;
    private List<Observateur> observateurs = new ArrayList<>();

    public Capteur(float valeurInitiale) {
        this.valeur = valeurInitiale;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
        notifierObservateurs();
    }

    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void retirerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.mettreAJour(this);
        }
    }
}
