public class Driver {
  
  public static void main(String[] args) {
    
    // TODO: put all tests
    // coefficient variable exponent
    Term t0 = new Term(0d, 'x', 2); // 3x^2
    Term t1 = new Term(-2d, 'y', 3); // –2y^3
    Term t2 = new Term(4d, 'x', 1); // 4x
    Term t3 = new Term(8d, ' ', 0); // 8

    System.out.println(t0); // Affiche : 3x^2
        System.out.println(t1); // Affiche : -2y^3
        System.out.println(t2); // Affiche : 4x
        System.out.println(t3); // Affiche : 8
    
  }

}
