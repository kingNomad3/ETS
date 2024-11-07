import java.util.Scanner;


public class ManipulationDeChaine {

    public static String chaine ="";

    public static boolean estPalindrome(String chaine){
           
        String newChaine = "";

        for(int i =chaine.length()-1; i>=0; i--){
            
            newChaine += chaine.charAt(i);

            
        }

        return newChaine.equals(chaine);
    }
    
    public static void main(String[] args) {
       

        
       Scanner sc =  new Scanner(System.in);

      
       
       chaine =sc.nextLine();
      
        ManipulationDeChaine mdc = new ManipulationDeChaine();
        // Vérifier si la chaîne est un palindrome

        

        System.out.println("Est-ce que \"" + chaine + "\" est un palindrome ? " + mdc.estPalindrome(chaine));
       

    }
    
}