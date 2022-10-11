class SilverMember implements Membership{
    final int interestRate = 4;
    private double silverBonus;

    public double interestCalc(double amt, int time){
        for(int i=0; i<time; i++){
            silverBonus += 30;
        }

        double calc = amt + (amt * interestRate * time)/100 + silverBonus;
        return Math.round(calc * 100.0) / 100.0;
    }

    public String toString(){
        return "Silver";
    }
}