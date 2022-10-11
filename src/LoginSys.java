// Imports

import javax.swing.*;
import java.awt.event.ActionListener;

class LoginSys extends JDialog{ // LoginSys is a custom dialog.
    private JPanel panel;
    private JLabel passwordLabel, usernameLabel;
    private JTextField username;
    private JButton submit;
    private JPasswordField password;

    public LoginSys(){
        // Panel for dialog
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        
        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(20, 8, 70, 20);
        panel.add(usernameLabel);
        
        // Username textfield
        username = new JTextField();
        username.setBounds(20, 27, 193, 28);
        panel.add(username);

        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 55, 70, 20);
        panel.add(passwordLabel);

        // Password textfield
        password = new JPasswordField();
        password.setBounds(20, 75, 193, 28);
        panel.add(password);

        // Login button
        submit = new JButton("Login");
        submit.setBounds(75, 110, 90, 25);
        panel.add(submit);

        // LoginSys values
        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);
        this.setTitle("Login");
        this.setSize(250, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Returns the username
    public String getUsername(){
        return username.getText();
    }

    // Returns the password
    public String getPassword(){
        return String.valueOf(password.getPassword());
    }

    // Adds a listener to the submit button
    public void addSubmitListener(ActionListener listener){
        submit.addActionListener(listener);
    }
}