
import javax.naming.ldap.Control;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        CapteurTemperature capteurTemperature = new CapteurTemperature(22.0f);
        CapteurCO2 capteurCO2 = new CapteurCO2(700.0f);

        Controleur controleur = new Controleur();

        capteurTemperature.ajouterObservateur(controleur);
        capteurCO2.ajouterObservateur(controleur);

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;// boucle infinie

        while (continuer) {
            System.out.print("Entrez une nouvelle température : ");
            float nouvelleTemperature = scanner.nextFloat();
            capteurTemperature.setValeur(nouvelleTemperature);

            System.out.print("Entrez une nouvelle concentration de CO2 : ");
            float nouvelleConcentrationCO2 = scanner.nextFloat();
            capteurCO2.setValeur(nouvelleConcentrationCO2);
        }

        scanner.close();

    }
}