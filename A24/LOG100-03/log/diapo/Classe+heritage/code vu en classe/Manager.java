public class Manager extends Employee{

    private double bonus;

    public Manager(String aName){
        super(aName);
    }

    public void setBonus(double Bonus){
        this.bonus = Bonus;
    }

    @Override
    public double getSalary(){
        return super.getSalary() + bonus;
    }

    public String toString(){
        return "Msg de la classe Manager avec bonus = " + bonus;
    }
}