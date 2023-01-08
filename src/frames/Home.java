package frames;

import javax.swing.*;

import repository.AccountRepository;
import module.Account;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {

    private JLabel banner;
    private JComboBox<String> usernameComboBox;
    private JButton continueButton;
    private JButton newUserButton; // it forwards to the registration page.
    private ArrayList<String> usersList;
    private AccountRepository accountRepository;

    public Home() {

        setTitle("Guess the Scrambled Word");
        setLayout(new GridBagLayout());

        banner = new JLabel("Guess the Scrambled Word");
        banner.setFont(new Font("Ariel", Font.BOLD, 40));
        GridBagConstraints bannerConstraint = new GridBagConstraints();
        bannerConstraint.gridx = 0;
        bannerConstraint.gridy = 0;
        bannerConstraint.insets = new Insets(0, 0, 50, 0);
        
        accountRepository = new AccountRepository();
        usersList = accountRepository.getUsername();
        usernameComboBox = new JComboBox<String>();
        for(String singleUser: usersList){
            usernameComboBox.addItem(singleUser);
        }

        usernameComboBox.setPreferredSize(new Dimension(400, 30));
        GridBagConstraints usernameConstraint = new GridBagConstraints();
        usernameConstraint.gridx = 0;
        usernameConstraint.gridy = 1;
        usernameConstraint.insets = new Insets(0, 0, 10, 0);
        
        continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(400, 30));
        continueButton.addActionListener(this);
        GridBagConstraints continueConstraint = new GridBagConstraints();
        continueConstraint.gridx = 0;
        continueConstraint.gridy = 2;
        
        newUserButton = new JButton("new account");
        newUserButton.setPreferredSize(new Dimension(200, 30));
        newUserButton.addActionListener(this);
        newUserButton.setBackground(Color.GRAY);
        GridBagConstraints newUserConstraint = new GridBagConstraints();
        newUserConstraint.gridx = 0;
        newUserConstraint.gridy = 3;
        newUserConstraint.insets = new Insets(20, 200, 0, 0);

        add(banner, bannerConstraint);
        add(usernameComboBox, usernameConstraint);
        add(continueButton, continueConstraint);
        add(newUserButton, newUserConstraint);

        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent e) { 
        
        if(e.getActionCommand().equals("Continue")){
            Account account = new Account();
            AccountRepository accountRepository = new AccountRepository();
            account.setUsername((String)usernameComboBox.getSelectedItem()); 

            this.dispose();
            String user = account.getUsername();
            String fullName = accountRepository.getFullName(user);
            new WeightSelection(fullName);
        }
        if(e.getActionCommand().equals("new account")){
            this.dispose();
            new Registration();
        }
         
    }
}
