// Imports
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class User implements Serializable {
    // User attributes
    private String username;
    private String password;
    private Account account;
    private Membership membership;

    // Constructor
    public User(String username, String password, Account account, Membership membership){
        this.username = username;
        this.password = password;
        this.account = account;
        this.membership = membership;
    }

    // Sets username
    public void setUsername(String n){
        this.username = n;
    }

    // Gets username
    public String getUsername(){
        return username;
    }

    // Sets password
    public void setPassword(String n){
        this.password = n;
    }

    // Gets password
    public String getPassword(){
        return password;
    }

    // Sets account
    public void setAccount(Account account){
    this.account = account;
    }
     
    // Gets account
    public Account getAccount(){
        return account;
    }

    // Sets membership
    public void setMembership(Membership membership){
        this.membership = membership;
    }  

    // Gets membership
    public Membership getMembership(){
        return membership;
    }

    // Gets balance
    public double getBalance(){
        return this.account.getBalance();
    }

    // Account setter with included check for overdraft boolean
    public void newAccount(boolean overdraft, double balance){
        if(overdraft){
            this.account = new OverdraftAccount(balance);

        } else{
            this.account = new NormalAccount(balance);
        }
    }

    // Membership setter with included check for the type of membership
    public void newMembership(String type){
        if(type.equals("Gold") || type.equals("gold")){
            this.membership = new GoldMember();
        } else if(type.equals("Silver") || type.equals("silver")){
            this.membership = new SilverMember();
        } else{
            this.membership = new BronzeMember();
        }
    }

    // Returns whether or not the users account is overdraft or not
    public boolean getAccountType(){
        return account.getOverdraftBoolean();
    }

    // Calculates interest based on the users membership and account balance
    public void calcInterest(int time){
        System.out.println(membership.interestCalc(account.getBalance(), time));
    }

    // Custom serialization in order to save User as an object
    private void writeObject(ObjectOutputStream outStream) throws IOException{
        outStream.writeUTF(this.getUsername());
        outStream.writeUTF(this.getPassword());
        outStream.writeBoolean(this.getAccountType());
        outStream.writeDouble(account.getBalance());
        outStream.writeUTF(membership.toString());
    }

    // Custom serialization reading in order to set the right attributes and users objects according to how
    // they were written.
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.username = inputStream.readUTF();
        this.password = inputStream.readUTF();
        newAccount(inputStream.readBoolean(), inputStream.readDouble());
        newMembership(inputStream.readUTF());
    }
}
