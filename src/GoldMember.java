class GoldMember implements Membership{
    // Gold membership contains interest rate + bonus
    final int interestRate = 6;
    private double goldBonus;

    // Interest rate calculation for gold members.
    public double interestCalc(double amt, int time){
        // The bonus is accumulated every year that passes, it added to the final calculation.
        for(int i=0; i<time; i++){
            goldBonus += 50;
        }
        double calc = amt + (amt * interestRate * time)/100 + goldBonus;
        return Math.round(calc * 100.0) / 100.0;
    }

    // Custom toString method
    public String toString(){
        return "Gold";
    }
}