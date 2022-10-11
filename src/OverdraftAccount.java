import javax.swing.*;

class OverdraftAccount extends Account{
   
    public OverdraftAccount(double balance) {
        super(balance);
    }

    public void withdraw(double amt){
        if((this.balance - amt) < -1500){
            JOptionPane.showMessageDialog(null, "Cannot go below -1500.");
        } else {
            this.balance -= amt;
        }
    }

    public boolean getOverdraftBoolean(){
        return true;
    }
}
