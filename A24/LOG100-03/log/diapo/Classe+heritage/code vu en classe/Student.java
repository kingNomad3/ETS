public class Student {

    private String student_name; private int student_id;
    public double[] grades;
    public double moyenne;

    public Student(String nom, int ID, double Moy){
        this.student_name = nom;
        this.student_id = ID;
        this.moyenne = Moy;
        grades = new double[3];

    }

    public void add_grades(double[] notes){
        for (int i=0; i<3; i ++){

            grades[i] = notes[i];
        }
    }

    public double calcAvg(){

        double moy= 0.0;
        for (int j=0; j< grades.length; j++){
            moy+=grades[j];

        }
        return moy/grades.length;
    }

    public String getStudent_name() {
        return student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public double[] getGrades() {
        return grades;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
}