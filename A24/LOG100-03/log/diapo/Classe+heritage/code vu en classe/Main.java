import java.util.Date;

public class Main {

    class ClasseMuable {
        private Date d;
        private String NOM;
        private String NAS;
        public ClasseMuable(){
            d = new Date();
        }

        public ClasseMuable(String nom, String nas){
            this.NOM = nom;
            this.NAS = nas;
        }


        public Date getDate(){
            return (Date) d.clone();
        }
    }



    public static void main(String[] args) {

        Date dd = new Date();
        Main m = new Main();
        ClasseMuable cm = m.new ClasseMuable();

        dd = cm.getDate();

        String chaine = "Anes";
        char[] ch;
        ch = chaine.toCharArray();
        for (int i=0; i<chaine.length(); i++) {
            System.out.println(ch[i]);
        }

        /*
        String message = "IP-TECH BLOG";
        String tempo = message.replace("BLOG", "WEBSITE");
        System.out.println(tempo + "| " + message);

         */

        Student st = new Student("Jean-luc", 54461462, 0.0);
        double[] notes = {98.0, 76.0, 57.8};
        st.add_grades(notes);

        System.out.println("La maoyenne est: " + st.calcAvg());
        Triangle T1 = new Triangle("T1");
        T1.afficher();

        Employee emp = new Employee("Carlos");
        emp.setSalary(2500.0);
        Manager mn = new Manager("Charles");
        mn.setSalary(3500.0);
        mn.setBonus(1000.0);
        System.out.println("Le salaire de Manager :" + mn.getSalary());
        System.out.println(mn);
    }
}