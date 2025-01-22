import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le dessin à tester :");
        System.out.println("1- Dessin avec trois niveaux");
        System.out.println("2- Dessin avec deux niveaux");
        System.out.println("3- Dessin avec un niveau (une forme seulement)");
        int dessinChoice = scanner.nextInt();

        System.out.println("Entrez la stratégie d'affichage à utiliser :");
        System.out.println("1- Indentation");
        System.out.println("2- Chemin complet");
        int strategyChoice = scanner.nextInt();

        Dessin dessin = createDessin(dessinChoice);

        AffichageStrategie strategy = createStrategy(strategyChoice);

        System.out.println("Affichage du dessin :");
        dessin.afficher(strategy, 0, dessin.getNom());

        scanner.close();
    }

    private static Dessin createDessin(int choice) {
        Dessin dessin1 = new Dessin("Dessin1");

        if (choice == 1) {
            Dessin dessin2 = new Dessin("Dessin2");
            Dessin dessin3 = new Dessin("Dessin3");
            Dessin dessin4 = new Dessin("Dessin4");

            // Niveau 3
            dessin3.ajouterEnfant(new Cercle("Cercle2"));
            dessin3.ajouterEnfant(new Cercle("Cercle3"));

            // Niveau 3
            dessin4.ajouterEnfant(new Triangle("Triangle1"));
            dessin4.ajouterEnfant(new Triangle("Triangle2"));
            dessin4.ajouterEnfant(new Rectangle("Rectangle1"));

            // Niveau 2
            dessin2.ajouterEnfant(dessin3);
            dessin2.ajouterEnfant(dessin4);

            // Niveau 1
            dessin1.ajouterEnfant(new Cercle("Cercle1"));
            dessin1.ajouterEnfant(dessin2);

        } else if (choice == 2) {
            Dessin dessin2 = new Dessin("Dessin2");

            dessin2.ajouterEnfant(new Triangle("Triangle1"));
            dessin2.ajouterEnfant(new Rectangle("Rectangle1"));

            dessin1.ajouterEnfant(new Cercle("Cercle1"));
            dessin1.ajouterEnfant(dessin2);

        } else if (choice == 3) {
            dessin1.ajouterEnfant(new Cercle("Cercle1"));
        }

        return dessin1;
    }


    private static AffichageStrategie createStrategy(int choice) {
        if (choice == 1) {
            return new AffichageIndentation();
        } else if (choice == 2) {
            return new AffichageCheminComplet();
        } else {
            throw new IllegalArgumentException("Choix de stratégie invalide !");
        }
    }
}
