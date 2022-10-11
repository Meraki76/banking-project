// Imports swing + some awt libaries for GUI.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame{
    // All attributes
    private JPanel mainPanel, interestPanel;
    private JTabbedPane tabbedPane;
    private JLabel usernameLabel, balanceLabel, inputLabel, accountTypeLabel, calculatedAmountLabel, timeInputLabel;
    private LoginSys login;
    private User currentUser;
    private Account currentAccount;
    private JTextField inputField, timeInputField;
    private JButton depositButton, withdrawButton, logoutButton, interestButton;
    private UserList userList;
    private WriterReader writer;


    public GUI(UserList userList, WriterReader writer){
        // Passes the users and the reading+writing functions
        this.userList = userList;
        this.writer = writer;

        // The main panel, where users can deposit, withdraw and logout.
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // The interest panel, where users can calculate how interest effects their balance over years.
        interestPanel = new JPanel();
        interestPanel.setLayout(null);

        // Tabbed pane allows for each panel to be seperated by tabs.
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Main", mainPanel);
        tabbedPane.addTab("Interest", interestPanel);
        this.add(tabbedPane);

        //// Main Panel ////////////////////////////////////////////////////////////////////////////////////

        Font font = new Font("Arial", Font.PLAIN, 24);

        // Username Label
        usernameLabel = new JLabel();
        usernameLabel.setBounds(30, 20, 500, 30);
        usernameLabel.setFont(font);
        mainPanel.add(usernameLabel);

        // Balance Label
        balanceLabel = new JLabel();
        balanceLabel.setBounds(170, 100, 300, 30);
        balanceLabel.setFont(font);
        mainPanel.add(balanceLabel);

        // Input Label
        inputLabel = new JLabel("Input:");
        inputLabel.setBounds(130, 180, 130, 30);
        inputLabel.setFont(font);
        mainPanel.add(inputLabel);
        
        // Field for input
        inputField = new JTextField();
        inputField.setBounds(210, 180, 150, 30);
        mainPanel.add(inputField);

        // Button to deposit
        depositButton = new JButton("Deposit");
        depositButton.setBounds(170, 270, 180, 50);
        mainPanel.add(depositButton);
        
        // Button to withdraw
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(170, 330, 180, 50);
        mainPanel.add(withdrawButton);

        // Button to logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(170, 390, 180, 50);
        mainPanel.add(logoutButton);

        // Interest Panel ////////////////////////////////////////////////////////////////////////////////////////
        accountTypeLabel = new JLabel();
        accountTypeLabel.setBounds(30, 20, 300, 30);
        interestPanel.add(accountTypeLabel);

        // Label for the amount calculated
        calculatedAmountLabel = new JLabel("Calculated Amount: 0");
        calculatedAmountLabel.setBounds(20, 100, 600, 30);
        calculatedAmountLabel.setFont(font);
        interestPanel.add(calculatedAmountLabel);

        // Label for time input
        timeInputLabel = new JLabel("Time (years):");
        timeInputLabel.setBounds(90, 240, 170, 30);
        timeInputLabel.setFont(font);
        interestPanel.add(timeInputLabel);

        // Field for time input
        timeInputField = new JTextField();
        timeInputField.setBounds(240, 240, 150, 30);
        interestPanel.add(timeInputField);

        // Calculation button
        interestButton = new JButton("Calculate Interest");
        interestButton.setBounds(170, 340, 180, 50);
        interestPanel.add(interestButton);

        // Window closer for GUI
        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);
        
        // GUI values
        this.setTitle("Banking Application");
        this.setSize(520, 600);
        this.setLocationRelativeTo(null);

        // Not set to visible until logged in.
        this.setVisible(false);
        
        // Begins the login system
        login = new LoginSys();
        login.setVisible(true);
        
        // Adds a listener for the login button within the prompt
        login.addSubmitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameInput = login.getUsername();
                String passwordInput = login.getPassword();

                // User validation
                try {
                    User user = userList.getUser(usernameInput);
                    if(passwordInput.equals(user.getPassword())){
                        login.dispose();
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        setCurrentUser(user); // Sets who logged in
                        activate(); // Begins the main GUI
                    } else{
                        JOptionPane.showMessageDialog(null, "Password incorrect.");
                    }
                } catch (RuntimeException RuntimeException) {
                    JOptionPane.showMessageDialog(null, "Username not found");
                }
            }
        });


    }

    // Activates the main GUI after logged in successfully
    private void activate(){
        this.setVisible(true);

        // Main panel ///////////////////////////////////////////////////////////////////////////////////////////
        
        //Sets the necessary text which was dependent on who logged in
        usernameLabel.setText("Welcome, " + currentUser.getUsername());
        balanceLabel.setText("Balance: " + currentUser.getBalance());

        // The method for the button is added, these are also dependent on who logged in.
        depositButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{    
                    //Deposit Method
                    double amt = Double.parseDouble(inputField.getText());
                    currentAccount.deposit(amt);
                    balanceLabel.setText("Balance: " + Double.toString(currentUser.getBalance()));
                    inputField.setText("");
                    JOptionPane.showMessageDialog(null, "Deposit successful.");
                    writer.write(userList);
                // Catches incorrect input
                } catch (NumberFormatException NumberFormatException){
                    timeInputField.setText("");
                    JOptionPane.showMessageDialog(null, "Must be in the format of an integer/double");
                }
            }
        });

        // Method for the withdraw button
        withdrawButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    // Withdraw method
                    double tempBal = currentAccount.getBalance();
                    double amt = Double.parseDouble(inputField.getText());
                    currentAccount.withdraw(amt);
                    balanceLabel.setText("Balance: " + Double.toString(currentUser.getBalance()));
                    inputField.setText("");
                    if(tempBal != currentAccount.getBalance()){ //Message dialog only works if withdraw is successful
                        JOptionPane.showMessageDialog(null, "Withdraw Successful");
                    }
                    writer.write(userList);
                } catch (NumberFormatException NumberFormatException){
                    timeInputField.setText("");
                    JOptionPane.showMessageDialog(null, "Must be in the format of an integer/double");
                }
            }
        });

        // Interest Panel //////////////////////////////////////////////////////////////////////////////////////
        accountTypeLabel.setText("Membership: " + currentUser.getMembership().toString());

        // Interest Button
        interestButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    // Calculate interest method
                    int years = Integer.parseInt(timeInputField.getText());
                    Membership membership = currentUser.getMembership();
                    calculatedAmountLabel.setText("Calculated amount: " + membership.interestCalc(currentAccount.getBalance(), years)); 
                    timeInputField.setText("");
                    JOptionPane.showMessageDialog(null, "Calculation Successful");
                } catch (NumberFormatException numberFormatException){
                    timeInputField.setText("");
                    JOptionPane.showMessageDialog(null, "Must be in the format of an integer (Whole numbers)");
                }
            }
        });

        // Re-calculates all componets.
        revalidate();
    }

    // Sets the current user to be known by the GUI.
    private void setCurrentUser(User user){
        this.currentUser = user;
        this.currentAccount = currentUser.getAccount();
    }

    // Returns the current user.
    public User getCurrentUser(){
        return this.currentUser;
    }

    // Adds a listener to the logout button
    public void addLogoutListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
}