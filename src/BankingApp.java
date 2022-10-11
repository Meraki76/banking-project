// Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class
class BankingApp {

    // Elements of the program
    GUI application;
    UserList userList;
    WriterReader writer;

    public BankingApp(){
        
        writer = new WriterReader();
        userList = writer.reader();  
        
        
        /* This is the current "database", uncomment to reset file.
           These logins should work and be stored within users.dat.
           (View users page?)
        User user1 = new User("TestUser1", "password", new NormalAccount(100), new BronzeMember());
        User user2 = new User("GoldMember123", "12345", new OverdraftAccount(220), new GoldMember());
        User user3 = new User("User3", "newpw", new OverdraftAccount(50), new SilverMember());
        userList = new UserList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        writer.write(userList);
        */
        

        // Begins the application
        application = new GUI(userList, writer);
        // Adds listener to close the "main" application and return to login page.
        application.addLogoutListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

              writer.write(userList); // Save
              application.dispose();
              reset();
          }
      });
    }

    // Main method
    public static void main(String[] args){
      new BankingApp();
    }

    // Resets the application
    public void reset(){
      new BankingApp();
    }
}