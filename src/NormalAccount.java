import javax.swing.*;

class NormalAccount extends Account{
    final double bonus = 0.02;

    public NormalAccount(double balance){
        super(balance);
    }

    public void deposit(double amt){
        super.deposit(amt);
        this.balance += bonus;
        this.balance = Math.round(balance * 100.0) / 100.0;
    }

    public void withdraw(double amt){
        if((this.balance - amt) < 0){
            JOptionPane.showMessageDialog(null, "Balance cannot go below 0.");
        } else {
            this.balance -= amt;
            this.balance = Math.round(balance * 100) / 100.0;
        }
    }

    public boolean getOverdraftBoolean(){
        return false;
    }

}