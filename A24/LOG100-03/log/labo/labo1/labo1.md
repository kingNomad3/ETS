
# Tableaux
## Explications générales

- **`sum(int[] tab1, int[] tab2)`** :  
  - Cette méthode convertit deux tableaux d'entiers représentant des chiffres (ex: `{3, 2, 8}` pour 328) en nombres entiers, puis calcule et renvoie leur somme.

- **Multiplicateur** :  
  - Utilisé pour transformer les chiffres en un nombre entier en considérant chaque position comme une puissance de 10, de droite à gauche.

- **Fonction principale `main`** :  
  - Déclare plusieurs exemples de tableaux d'entiers pour tester la méthode `sum` et affiche les résultats.

## Implémentation de la méthode `sum`

Implémentez la méthode `sum`, qui reçoit comme paramètres deux tableaux de nombres entiers et fait la somme des nombres formés par les deux tableaux. Chacun de ces tableaux représente un nombre entier où le premier élément est le chiffre le plus significatif, c’est-à-dire celui qui a la valeur la plus grande selon sa position.

Par exemple, le tableau avec les éléments `[5, 7, 3]` représente la valeur calculée comme suit :

- `5×100 + 7×10 + 3×1 = 573`

La méthode `sum` doit calculer les valeurs numériques des deux tableaux et retourner la somme comme un nombre entier.

```java
public class ArrayToolbox {
  
  /**
   * Additionne deux tableaux contenant des chiffres.
   * Chaque élément des tableaux représente un chiffre unique.
   * Le chiffre le plus à droite est l'unité.
   * @param tab1 Premier tableau de chiffres
   * @param tab2 Deuxième tableau de chiffres
   * @return La somme des deux nombres représentés par les tableaux
   */
   
  public static int sum(int[] tab1, int[] tab2) {
    int multiplicateur = 1;  // Multiplicateur utilisé pour convertir les chiffres en leur valeur positionnelle (unités, dizaines, etc.)
    int numb1 = 0;           // Entier qui stockera le nombre formé par tab1
    int numb2 = 0;           // Entier qui stockera le nombre formé par tab2

    // Convertit le premier tableau, tab1, en un nombre entier
    for (int i = tab1.length - 1; i >= 0; i--) {  // Boucle sur les chiffres de droite à gauche (des unités aux chiffres les plus significatifs)
      numb1 += tab1[i] * multiplicateur;          // Ajoute chaque chiffre multiplié par sa valeur positionnelle
      multiplicateur *= 10;                       // Met à jour le multiplicateur pour passer à la position suivante (unités à dizaines, etc.)
    }

    multiplicateur = 1;  // Réinitialise le multiplicateur pour le deuxième tableau

    // Convertit le deuxième tableau, tab2, en un nombre entier
    for (int i = tab2.length - 1; i >= 0; i--) {
      numb2 += tab2[i] * multiplicateur;          // Ajoute chaque chiffre multiplié par sa valeur positionnelle
      multiplicateur *= 10;                       // Met à jour le multiplicateur pour la position suivante
    }

    return numb1 + numb2;  // Retourne la somme des deux nombres
  }
  
  public static void main(String[] args) {
    // Tableaux de test représentant des nombres
    int[] tab1 = {3, 2, 8};    // Représente le nombre 328
    int[] tab2 = {4, 7, 1};    // Représente le nombre 471

    int[] tab3 = {2, 4};       // Représente le nombre 24
    int[] tab4 = {5, 2, 9};    // Représente le nombre 529

    int[] tab5 = {1, 5, 0};    // Représente le nombre 150
    int[] tab6 = {3, 4};       // Représente le nombre 34

    // Calcul et affichage des résultats des sommes
    int resultat = sum(tab1, tab2);     // Additionne 328 et 471
    int resultat2 = sum(tab3, tab4);    // Additionne 24 et 529
    int resultat3 = sum(tab5, tab6);    // Additionne 150 et 34

    System.out.println(resultat);       // Affiche le résultat de la première somme
    System.out.println(resultat2);      // Affiche le résultat de la deuxième somme
    System.out.print(resultat3);        // Affiche le résultat de la troisième somme
  }
  
}
```

# Multiplication


Implémentez la méthode `multiply`, qui calcule la multiplication de deux matrices représentées comme des tableaux bidimensionnels.

La formule pour calculer la multiplication de la matrice \( A \), composée de \( n \times m \) éléments, avec la matrice \( B \), composée de \( m \times p \) éléments, est :

\[
\begin{bmatrix} 
a_{11} & \dots & a_{1m} \\ 
\vdots & \ddots & \vdots \\ 
a_{n1} & \dots & a_{nm} 
\end{bmatrix} 
\times 
\begin{bmatrix} 
b_{11} & \dots & b_{1p} \\ 
\vdots & \ddots & \vdots \\ 
b_{m1} & \dots & b_{mp} 
\end{bmatrix} 
= 
\begin{bmatrix} 
c_{11} & \dots & c_{1p} \\ 
\vdots & \ddots & \vdots \\ 
c_{n1} & \dots & c_{np} 
\end{bmatrix}
\]

où chaque élément \( c_{ij} \) est défini par :

\[
c_{ij} = a_{i1} \times b_{1j} + \dots + a_{im} \times b_{mj} = \sum_{k=1}^{m} (a_{ik} \times b_{kj})
\]

Cette formule exprime la valeur de chaque élément \( c_{ij} \) de la matrice résultat comme la somme des produits des éléments correspondants des lignes de \( A \) et des colonnes de \( B \).


```java 
public class MatrixToolbox {

  /**
   * Multiplie deux matrices.
   * Les matrices sont stockées en format "row-major", c'est-à-dire que chaque sous-tableau représente une ligne.
   * @param matA Première matrice (Double[][])
   * @param matB Deuxième matrice (Double[][])
   * @return La matrice résultat de la multiplication A x B
   */
  public static Double[][] multiply(Double[][] matA, Double[][] matB) {
    // Vérifie que le nombre de colonnes de matA est égal au nombre de lignes de matB
    if (matA[0].length != matB.length) {
      System.out.println("Les matrices ne sont pas compatibles pour la multiplication.");
      // Retourne une matrice contenant NaN pour indiquer une incompatibilité
      return new Double[][] { { Double.NaN } };
    }

    // Crée une nouvelle matrice pour stocker le résultat de la multiplication
    // Sa taille sera nombre de lignes de matA x nombre de colonnes de matB
    Double[][] resultat = new Double[matA.length][matB[0].length];

    // Boucle pour chaque ligne de la première matrice (matA)
    for (int row = 0; row < matA.length; row++) {
      // Boucle pour chaque colonne de la deuxième matrice (matB)
      for (int col = 0; col < matB[0].length; col++) {
        // Initialise la cellule de résultat à zéro
        resultat[row][col] = 0.0;

        // Calcule la valeur de resultat[row][col] en sommant les produits des éléments
        for (int i = 0; i < matA[0].length; i++) {
          resultat[row][col] += matA[row][i] * matB[i][col];
        }
      }
    }

    // Retourne la matrice résultat après avoir complété la multiplication
    return resultat;
  }

  /**
   * Affiche le contenu d'une matrice dans un format lisible.
   * @param mat Matrice à afficher (row-major)
   */
  public static void printMat(Double[][] mat) {
    // Parcourt chaque ligne de la matrice
    for (Double[] row : mat) {
      System.out.print("[ ");
      // Parcourt chaque valeur dans la ligne et l'affiche
      for (Double val : row) {
        System.out.print(val + " ");
      }
      System.out.println("]"); // Termine la ligne
    }
  }

  public static void main(String[] args) {
    // Exemple de matrices pour démontrer la méthode multiply et printMat

    Double[][] matA = {
      {2.0, 3.0},
      {4.0, 5.0}
    }; // Matrice 2x2

    Double[][] matB = {
      {6.0, 7.0},
      {8.0, 9.0}
    }; // Matrice 2x2, compatible avec matA pour la multiplication

    Double[][] matC = {
      {1.0, 0.0},
      {3.0, 2.0}
    }; // Matrice 2x2

    Double[][] matD = {
      {8.0, 6.0, 2.0},
      {4.0, 1.0, 0.0}
    }; // Matrice 2x3, compatible avec matC pour la multiplication

    Double[][] matE = {
      {5.0, 6.0, 7.0}
    }; // Matrice 1x3

    Double[][] matF = {
      {1.0, 0.0},
      {0.0, 1.0},
      {1.0, 1.0}
    }; // Matrice 3x2, compatible avec matE pour la multiplication

    // Multiplie les matrices et stocke les résultats
    Double[][] resultat = multiply(matA, matB); // Multiplication de matA par matB
    Double[][] resultat2 = multiply(matC, matD); // Multiplication de matC par matD
    Double[][] resultat3 = multiply(matE, matF); // Multiplication de matE par matF

    // Affiche les matrices résultantes
    System.out.println("Résultat de matA x matB :");
    printMat(resultat);

    System.out.println("Résultat de matC x matD :");
    printMat(resultat2);

    System.out.println("Résultat de matE x matF :");
    printMat(resultat3);
  }
}
```

# Statistiques

# Nombre d’éléments

Implémentez la méthode `elemCount`, qui reçoit comme paramètre un tableau de nombres réels et qui retourne le nombre d’éléments dans le tableau.

# Moyenne

Implémentez la méthode `average`, qui reçoit comme paramètre un tableau de nombres réels et qui retourne la moyenne arithmétique des nombres.

# Maximum

Implémentez, à l’aide d’une boucle, la méthode `max`, qui reçoit comme paramètre un tableau de nombres réels et qui retourne la plus grande valeur du tableau.

# Minimum

Implémentez, à l’aide d’une boucle, la méthode `min`, qui reçoit comme paramètre un tableau de nombres réels et qui retourne la plus petite valeur du tableau.

```java 
public class Statistics {

  /**
   * Compte le nombre d'éléments dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return Nombre d'éléments dans le tableau
   */
  public static int elemCount(float[] tab) {
    int ele = tab.length; // Nombre d'éléments dans le tableau
    return ele;
  }

  /**
   * Calcule la moyenne arithmétique des éléments d'un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return Moyenne des éléments du tableau
   */
  public static float average(float[] tab) {
    float sum = 0;
    float length = tab.length;

    // Somme tous les éléments du tableau
    for(int i = 0; i < tab.length; i++) {
      sum += tab[i];
    }

    // Calcule la moyenne
    float avg = sum / length;
    return avg;
  }

  /**
   * Trouve la valeur maximale dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return La plus grande valeur du tableau
   */
  public static float max(float[] tab) {
    float max = tab[0]; // Initialise avec le premier élément du tableau

    // Parcourt tous les éléments pour trouver la valeur maximale
    for(int i = 0; i < tab.length; i++) {
      if(tab[i] > max) {
        max = tab[i];
      }
    }
    
    return max;
  }

  /**
   * Trouve la valeur minimale dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return La plus petite valeur du tableau
   */
  public static float min(float[] tab) {
    float min = tab[0]; // Initialise avec le premier élément du tableau

    // Parcourt tous les éléments pour trouver la valeur minimale
    for(int i = 0; i < tab.length; i++) {
      if(tab[i] < min) {
        min = tab[i];
      }
    }
    return min;
  }

  public static void main(String[] args) {
    // Vérifie qu'il y a au moins deux arguments : une option et une liste de nombres
    if (args.length < 2) {
      System.out.println("Pas assez d'arguments.");
      return;
    }

    int argument = Integer.parseInt(args[0]); // Premier argument : type d'opération

    // Convertit les arguments de chaîne en un tableau de nombres réels
    float[] values = new float[args.length - 1];
    for(int i = 1; i < args.length; i++) {
      values[i - 1] = Float.parseFloat(args[i]);
    }

    // Exécute l'opération en fonction de l'argument donné
    switch (argument) {
      case 1:
        // Compte le nombre d'éléments
        int ele = elemCount(values);
        System.out.println("Nombre d'éléments : " + ele);
        break;

      case 2:
        // Calcule la moyenne
        float avg = average(values);
        System.out.println("Moyenne : " + avg);
        break;

      case 3:
        // Affiche le minimum et le maximum
        float min = min(values);
        float max = max(values);
        System.out.println("Maximum : " + max + ", Minimum : " + min);
        break;

      default:
        // Message d'erreur pour une opération invalide
        System.out.println("Opération invalide : " + argument);
        break;
    }
  }
}
```

# Programme

La méthode `main` de la classe reçoit comme premier argument un nombre entier pour sélectionner la ou les méthodes à appeler avec le reste des valeurs en argument.

Les valeurs possibles pour ce premier argument sont :
- `1` pour appeler la méthode `elemCount`
- `2` pour appeler la méthode `average`
- `3` pour appeler les méthodes `max` et `min`

Pour toutes les autres valeurs, la méthode affiche seulement le message `Invalid operation`, suivi d’un espace et du nombre reçu.

## Utilisez une instruction `switch` pour faire l’appel à la méthode adéquate.

## À noter

- Les arguments d’un programme sont les chaînes de caractères séparées par des espaces passées avec l’appel du programme. On les retrouve dans le paramètre de la méthode `main`.
- On peut obtenir la première valeur avec l’aide de la méthode statique `Integer.parseInt`, qui convertit une chaîne de caractères en un entier.
- On peut procéder de façon similaire pour obtenir un nombre réel (`Float.parseFloat`).
- Pour les trois opérations, on doit avoir au moins une valeur d’entrée pour les calculs.

```java 
public class Statistics {
  
  /**
   * Compte le nombre d'éléments dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return Nombre d'éléments dans le tableau
   */
  public static int elemCount(float[] tab) {
    // Utilise la propriété length pour obtenir le nombre d'éléments
    int ele = tab.length;
    return ele;
  }
  
  /**
   * Calcule la moyenne arithmétique des éléments d'un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return Moyenne des éléments du tableau
   */
  public static float average(float[] tab) {
    float sum = 0; // Somme de tous les éléments
    float length = tab.length; // Nombre d'éléments dans le tableau

    // Additionne chaque élément du tableau pour obtenir la somme totale
    for(int i = 0; i < tab.length; i++) {
      sum += tab[i];
    }

    // Calcule la moyenne en divisant la somme par le nombre d'éléments
    float avg = sum / length;
    return avg;
  }
  
  /**
   * Trouve la valeur maximale dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return La plus grande valeur du tableau
   */
  public static float max(float[] tab) {
    float max = tab[0]; // Initialise avec le premier élément du tableau

    // Parcourt tous les éléments pour trouver la valeur maximale
    for(int i = 0; i < tab.length; i++) {
      if(tab[i] > max) {
        max = tab[i]; // Met à jour max si un élément plus grand est trouvé
      }
    }
    return max;
  }
  
  /**
   * Trouve la valeur minimale dans un tableau de nombres réels.
   * @param tab Tableau de nombres réels
   * @return La plus petite valeur du tableau
   */
  public static float min(float[] tab) {
    float min = tab[0]; // Initialise avec le premier élément du tableau

    // Parcourt tous les éléments pour trouver la valeur minimale
    for(int i = 0; i < tab.length; i++) {
      if(tab[i] < min) {
        min = tab[i]; // Met à jour min si un élément plus petit est trouvé
      }
    }
    return min;
  }

  /**
   * Méthode principale pour tester les méthodes elemCount, average, max et min.
   * Elle reçoit les arguments en ligne de commande.
   * @param args Le premier argument est un entier qui détermine l'opération (1, 2, ou 3), les suivants sont des valeurs pour le calcul
   */
  public static void main(String[] args) {
    // Vérifie qu'il y a au moins deux arguments : un code d'opération et une liste de valeurs
    if (args.length < 2) {
      System.out.println("nope");
      return;
    }

    // Convertit le premier argument en entier pour déterminer l'opération
    int argument = Integer.parseInt(args[0]);

    // Convertit les arguments restants en un tableau de nombres réels (float)
    float[] values = new float[args.length - 1];
    for(int i = 1; i < args.length; i++) {
      values[i - 1] = Float.parseFloat(args[i]);
    }
    
    // Utilise une instruction switch pour sélectionner l'opération appropriée
    switch (argument) {
      case 1:
        // Appelle elemCount pour compter les éléments et affiche le résultat
        int ele = elemCount(values);
        System.out.println(ele);
        break;

      case 2:
        // Appelle average pour calculer la moyenne et affiche le résultat
        float avg = average(values);
        System.out.println(avg);
        break;

      case 3:
        // Appelle min et max pour trouver les valeurs minimale et maximale, puis les affiche
        float min = min(values);
        float max = max(values);
        System.out.print(max + ", " + min);
        break;

      default:
        // Affiche un message d'erreur si l'opération est invalide
        System.out.println("Invalid operation " + argument);
        break;
    }
  }
}
```