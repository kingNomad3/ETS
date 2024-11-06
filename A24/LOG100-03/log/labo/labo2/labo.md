# 1. Introduction

Bienvenue dans le deuxième laboratoire!

Comme pour le premier laboratoire, vous aurez le fichier Java à gauche et le guide à droite. Pour rappel, les tests d’évaluation doivent être lancés au moins une fois mais peuvent être lancés plusieurs fois. Vous pouvez également exécuter votre programme depuis le guide. Le débogueur est aussi actif.

## Commentaires de documentation

Écrivez de la documentation Javadoc pour au moins une méthode, en vous assurant de bien respecter le format et les bonnes pratiques. Utilisez `@param` et `@return` au moins une fois dans tout le projet. Générez et vérifiez votre documentation à l’aide des boutons de la barre de menu.

Vous pouvez documenter en français ou en anglais, mais ne mélangez pas les deux langues dans la documentation que vous écrivez. Dans les deux cas, surveillez également la qualité de la langue.

# Classe de gestion de note

La classe `Grade` permet de gérer une note, représentée par un pourcentage et une lettre. La classe stocke également d’autres informations sur le cours.

Dans les sections suivantes, vous allez implémenter l’ensemble de la classe `Grade` : ses attributs, ses constructeurs, etc.

> **Important**  
> Veillez à respecter précisément les noms des attributs et méthodes, sinon ceux-ci ne seront pas trouvés par les tests d’évaluation.

# 3. Attributs

Implémentez dans la classe `Grade` les attributs suivants :

- **department** : une chaîne de caractères contenant l’acronyme du département qui offre le cours. Par exemple : `LOG`, `GTI`, `MTR`, etc.
- **courseNum** : un nombre entier, de type primitif, représentant le numéro associé au cours. Par exemple : `100`, `121`, `801`, etc.
- **percent** : un nombre réel à simple précision, de type primitif, entre 0 et 100. Ce pourcentage correspond à la note obtenue dans le cours.
- **letter** : un seul caractère, de type primitif, qui correspond à la représentation alphabétique de l’attribut `percent` selon une échelle donnée. Les valeurs possibles sont `A`, `B`, `C`, `D` et `E`.

Implémentez un accesseur (getter) pour chacune des variables d’instance : `getDepartment`, `getCourseNum`, `getPercent` et `getLetter`.

> **À noter**  
> Déterminez la visibilité afin que les attributs soient correctement encapsulés. Notez que la classe `Grade` devrait être immuable.  
> On laissera dans la classe le constructeur sans paramètre qui, sans contenu dans son implémentation, va initialiser tous les attributs à leur valeur par défaut, ou suivre les initialisations qui sont faites avec la déclaration des attributs.

```java 
/**
 * Classe représentant une note (Grade) pour un cours spécifique. 
 * Cette classe est immuable : une fois les valeurs initialisées, elles ne peuvent pas être modifiées.
 */
public class Grade {
  
  private String department;  // Acronyme du département, e.g., "LOG"
  private int courseNum;      // Numéro du cours, e.g., 100, 121, etc.
  private float percent;      // Pourcentage entre 0 et 100
  private char letter;        // Note en lettre, e.g., 'A', 'B', etc.

  /**
     * Constructeur par défaut qui initialise les valeurs par défaut pour chaque attribut.
     * department est une chaîne vide, courseNum et percent sont initialisés à 0,
     * et letter est initialisé à 'E'.
     */
  public Grade() {
      this.department = "";
      this.courseNum = 0;
      this.percent = 0.0f;
      this.letter = 'E';
  }
  
  /**
  * Constructeur qui initialise les attributs avec les valeurs spécifiées.
  *
  * @param department l'acronyme du département offrant le cours (e.g., "LOG").
  * @param courseNum le numéro associé au cours.
  * @param percent le pourcentage obtenu dans le cours, entre 0 et 100.
  * @param letter la note alphabétique correspondant au pourcentage.
  */

  public Grade(String department,int courseNum,float percent,char letter) {
    this.department = department;
    this.courseNum = courseNum;
    this.percent = percent;
    this.letter = letter;
  }

  /**
  * Retourne l'acronyme du département offrant le cours.
  *
  * @return l'acronyme du département sous forme de chaîne de caractères.
  */
  public String getDepartment(){
    return department;
  }

  /**
  * Retourne le numéro du cours.
  *
  * @return un entier représentant le numéro du cours.
  */
  public int getCourseNum(){
    return courseNum;
  }

  /**
  * Retourne le pourcentage obtenu dans le cours.
  *
  * @return un nombre réel à simple précision représentant le pourcentage obtenu.
  */
  public float getPercent(){
    return percent;
  }

  /**
  * Retourne la note alphabétique correspondant au pourcentage.
  *
  * @return un caractère représentant la note alphabétique.
  */
  public char getLetter(){
    return letter;
  }
  
}
```

