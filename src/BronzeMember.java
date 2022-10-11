class BronzeMember implements Membership{
    final int interestRate = 2;

    // Calculates the interest based on the bronze memberships rate.
    public double interestCalc(double amt, int time){
        double calc = amt + (amt * interestRate * time)/100;
        return Math.round(calc * 100.0) / 100.0;
    }

    // Custom toString method, makes saving easier.
    public String toString(){
        return "Bronze";
    }
}