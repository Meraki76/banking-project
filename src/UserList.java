// Import
import java.util.ArrayList;

public class UserList {
    // Contains an arraylist and who is currently logged in
    ArrayList<User> userList;
    User currentUser;

    // Constructor
    public UserList(){
        this.userList = new ArrayList<>();
    }

    // Adds user to arraylist
    public void add(User user){
        this.userList.add(user);
    }

    // Removes user from arraylist
    public void remove(User user){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).equals(user)){
                userList.remove(i);
            }
        }
    }

    // Gets the size of the arraylist
    public int size(){
        return userList.size();
    }

    // Gets the user at point i
    public User get(int i){
        return userList.get(i);
    }
    // Gets the user from a username
    public User getUser(String username){
        User u = null;

        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUsername().equals(username)){
                u = userList.get(i);
            }
        }

        // Throws an exception if the user is null
        if(u == null){
            throw new RuntimeException("Username not found");
        }

        return u;
    }
}