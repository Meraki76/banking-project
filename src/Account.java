// Account abstract class

abstract class Account {
    protected double balance;
    
    //Constructor 
    public Account(double balance){
        this.balance = balance;
    }

    // Returns the balance
    public double getBalance() {
        return balance;
    } 

    // Deposits into the account + rounds it
    public void deposit(double amt){
        this.balance += amt;
        this.balance = Math.round(balance * 100.0) / 100.0;
    };

    // Abstract methods that will will be changed depending on the account created
    abstract public void withdraw(double amt);
    abstract boolean getOverdraftBoolean();

    // Custom to string (Helps with saving+loading)
    public String toString(){
        return Double.toString(balance);
    }
}
