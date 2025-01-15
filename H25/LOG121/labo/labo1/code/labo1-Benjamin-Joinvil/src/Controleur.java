public class Controleur implements Observateur {

    private boolean climatisationActive = false;
    private boolean chauffageActif = false;
    private boolean ventilationActive = false;

    @Override
    public void mettreAJour(Capteur capteur) {
        if (capteur instanceof CapteurTemperature) {
            float temperature = capteur.getValeur();
            System.out.println("--> Contrôleur : nouvelle valeur reçue : mesure de température = " + temperature + "°C");

            if (temperature > 22.0) {
                if (!climatisationActive) {
                    climatisationActive = true;
                    chauffageActif = false;
                    System.out.println("--> Contrôleur : Climatisation démarrée.");
                }
            } else if (temperature < 22.0) {
                if (!chauffageActif) {
                    chauffageActif = true;
                    if (climatisationActive) {
                        climatisationActive = false;
                        System.out.println("--> Contrôleur : Climatisation arrêtée.");
                    }
                    System.out.println("--> Contrôleur : Chauffage démarré.");
                }
            } else { // Température exactement 22°C
                if (climatisationActive) {
                    climatisationActive = false;
                    System.out.println("--> Contrôleur : Climatisation arrêtée.");
                }
                if (chauffageActif) {
                    chauffageActif = false;
                    System.out.println("--> Contrôleur : Chauffage arrêté.");
                }
            }
        } else if (capteur instanceof CapteurCO2) {
            float concentrationCO2 = capteur.getValeur();
            System.out.println("--> Contrôleur : nouvelle valeur reçue : mesure de CO2 = " + concentrationCO2 + " ppm");

            if (concentrationCO2 > 1000.0) {
                if (!ventilationActive) {
                    ventilationActive = true;
                    System.out.println("--> Contrôleur : Ventilation démarrée.");
                }
            } else if (ventilationActive) {
                ventilationActive = false;
                System.out.println("--> Contrôleur : Ventilation arrêtée.");
            }
        }
    }

}
