import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Ask user for the drawing to test
        System.out.println("Entrez le dessin à tester :");
        System.out.println("1- Dessin avec trois niveaux");
        System.out.println("2- Dessin avec deux niveaux");
        System.out.println("3- Dessin avec un niveau (une forme seulement)");
        int dessinChoice = scanner.nextInt();

        // Step 2: Ask user for the display strategy
        System.out.println("Entrez la stratégie d'affichage à utiliser :");
        System.out.println("1- Indentation");
        System.out.println("2- Chemin complet");
        int strategyChoice = scanner.nextInt();

        // Step 3: Create the drawing based on the choice
        Dessin dessin = createDessin(dessinChoice);

        // Step 4: Create the display strategy based on the choice
        AffichageStrategie strategy = createStrategy(strategyChoice);

        // Step 5: Display the drawing
        System.out.println("Affichage du dessin :");
        dessin.afficher(strategy, 0, dessin.getNom());

        scanner.close();
    }

    private static Dessin createDessin(int choice) {
        // Create drawings based on user choice
        Dessin dessin1 = new Dessin("Dessin1");
        if (choice == 1) { // Three levels
            Dessin dessin2 = new Dessin("Dessin2");
            Dessin dessin3 = new Dessin("Dessin3");

            dessin3.ajouterEnfant(new Cercle("Cercle2"));
            dessin3.ajouterEnfant(new Cercle("Cercle3"));

            Dessin dessin4 = new Dessin("Dessin4");
            dessin4.ajouterEnfant(new Triangle("Triangle1"));
            dessin4.ajouterEnfant(new Triangle("Triangle2"));
            dessin4.ajouterEnfant(new Rectangle("Rectangle1"));

            dessin2.ajouterEnfant(dessin3);
            dessin2.ajouterEnfant(dessin4);

            dessin1.ajouterEnfant(new Cercle("Cercle1"));
            dessin1.ajouterEnfant(dessin2);

        } else if (choice == 2) { // Two levels
            Dessin dessin2 = new Dessin("Dessin2");
            dessin2.ajouterEnfant(new Triangle("Triangle1"));
            dessin2.ajouterEnfant(new Rectangle("Rectangle1"));

            dessin1.ajouterEnfant(new Cercle("Cercle1"));
            dessin1.ajouterEnfant(dessin2);

        } else if (choice == 3) { // One level
            dessin1.ajouterEnfant(new Cercle("Cercle1"));
        }

        return dessin1;
    }

    private static AffichageStrategie createStrategy(int choice) {
        // Create display strategy based on user choice
        if (choice == 1) {
            return new AffichageIndentation();
        } else if (choice == 2) {
            return new AffichageCheminComplet();
        } else {
            throw new IllegalArgumentException("Choix de stratégie invalide !");
        }
    }
}
