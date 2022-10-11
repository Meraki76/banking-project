// Imports

import java.io.*;

public class WriterReader {
    // Size of the array to be saved
    static int SIZE;
    
    public void write(UserList userList){
        try{
            FileOutputStream file = new FileOutputStream(new File("users.dat"));
            ObjectOutputStream obj = new ObjectOutputStream(file);


            // Writes each user in the arraylist into the file
            SIZE = userList.size();
            obj.writeInt(SIZE);
            for(int i = 0; i < SIZE; i++){
                obj.writeObject(userList.get(i));
            }
            file.close();
            obj.close();

        // Catches for failed file write
        } catch(FileNotFoundException e){
            System.out.println("Error: File not found.");
        } catch(IOException e){
            System.out.println("Error: Failed to initialize stream.");
        }
    }

    public UserList reader(){   
        // Returns a userlist as that is what we are saving
        UserList u = new UserList();

        try{
            FileInputStream fileIn = new FileInputStream(new File("users.dat"));
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            // Adds each user back into the arraylist
            SIZE = objIn.readInt();
            for(int i = 0; i < SIZE; i++){
                u.add((User) objIn.readObject());
            }

        // Exceptions to be dealt with
        } catch(FileNotFoundException e){
            System.out.println("Error: File not found.");
        } catch(IOException e){
            System.out.println("Error: Failed to initialize stream.");     
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        // Returns the userlist read from the file
        return u;
    }
}
