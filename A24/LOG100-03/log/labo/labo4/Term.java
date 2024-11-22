public class Term {
  private final double coefficient;
  private final char variable;
  private final int exponent;

  
  public Term(double coefficient,char variable,int exponent){
    if(coefficient == 0){
      throw new ZeroCoefficientException();
    }
    this.coefficient = coefficient;
    this.variable = variable;
    this.exponent = exponent;
  }

  public double getCoefficient(){
    return coefficient;
  }

  public char getVariable(){
    return variable;
  }

  public int getExponent(){
    return exponent;
  }

  @Override
  public String toString() {
    if (variable == ' ') { // Cas où la variable est indéterminée
        return String.valueOf(coefficient); // Affiche juste le coefficient
    } else if (exponent == 1) { // Cas où l'exposant est 1
        return coefficient + String.valueOf(variable); // Exclut l'affichage de ^1
    } else { // Cas général
        return coefficient + String.valueOf(variable) + "^" + exponent;
    }
}




}

