import java.util.Scanner;

public class NombreHarshad {

    // Méthode pour vérifier si un nombre est Harshad
    public static boolean estHarshad(String nbHarshad) {
        String temp = "";
        int sommeDesChiffres = 0;

        // Conversion de la chaîne en entier
        int nbHarshadN = Integer.valueOf(nbHarshad);

        // Calculer la somme des chiffres
        for (int i = 0; i < nbHarshad.length(); i++) {
            temp = String.valueOf(nbHarshad.charAt(i)); // Obtenir le caractère à la position i
            sommeDesChiffres += Integer.valueOf(temp);  // Convertir le caractère en entier et l'ajouter
        }

        // Vérifier si le nombre est divisible par la somme de ses chiffres
        return nbHarshadN % sommeDesChiffres == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lecture de l'entrée utilisateur
        System.out.print("Entrez un nombre entier : ");
        String nbHarshad = scanner.nextLine(); // Lire l'entrée utilisateur sous forme de chaîne

        // Vérifier et afficher le résultat
        if (estHarshad(nbHarshad)) {
            System.out.println("Oui");
        } else {
            System.out.println("Non");
        }

        scanner.close(); // Fermer le scanner
    }
}
