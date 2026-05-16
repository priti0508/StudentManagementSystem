package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.db.DBConnection;

public class RegisterFrame extends JFrame implements ActionListener {

    JPanel leftPanel, rightPanel;

    JLabel titleLabel, userLabel,
            passLabel, emailLabel,
            welcomeLabel;

    JTextField userField, emailField;

    JPasswordField passField;

    JButton registerBtn;

    public RegisterFrame() {

        setTitle("Register");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        leftPanel = new JPanel();

        leftPanel.setBounds(0, 0, 400, 500);

        leftPanel.setBackground(new Color(76, 175, 80));

        leftPanel.setLayout(null);

        add(leftPanel);

        welcomeLabel = new JLabel("Create Account");

        welcomeLabel.setForeground(Color.WHITE);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        welcomeLabel.setBounds(70, 180, 300, 40);

        leftPanel.add(welcomeLabel);

        JLabel infoLabel =
                new JLabel("Student Management System");

        infoLabel.setForeground(Color.WHITE);

        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        infoLabel.setBounds(70, 230, 300, 30);

        leftPanel.add(infoLabel);

        rightPanel = new JPanel();

        rightPanel.setBounds(400, 0, 500, 500);

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        add(rightPanel);

        titleLabel = new JLabel("REGISTER");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        titleLabel.setBounds(170, 50, 200, 40);

        rightPanel.add(titleLabel);

  
        userLabel = new JLabel("Username");

        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        userLabel.setBounds(70, 130, 120, 30);

        rightPanel.add(userLabel);

        userField = new JTextField();

        userField.setBounds(70, 165, 320, 40);

        userField.setFont(new Font("Arial", Font.PLAIN, 16));

        rightPanel.add(userField);

        emailLabel = new JLabel("Email");

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        emailLabel.setBounds(70, 220, 120, 30);

        rightPanel.add(emailLabel);

        emailField = new JTextField();

        emailField.setBounds(70, 255, 320, 40);

        emailField.setFont(new Font("Arial", Font.PLAIN, 16));

        rightPanel.add(emailField);

        passLabel = new JLabel("Password");

        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        passLabel.setBounds(70, 310, 120, 30);

        rightPanel.add(passLabel);

        passField = new JPasswordField();

        passField.setBounds(70, 345, 320, 40);

        passField.setFont(new Font("Arial", Font.PLAIN, 16));

        rightPanel.add(passField);

 
        registerBtn = new JButton("REGISTER");

        registerBtn.setBounds(140, 410, 180, 45);

        registerBtn.setBackground(new Color(76, 175, 80));

        registerBtn.setForeground(Color.WHITE);

        registerBtn.setFont(new Font("Arial", Font.BOLD, 18));

        registerBtn.setFocusPainted(false);

        rightPanel.add(registerBtn);

        registerBtn.addActionListener(this);

        setVisible(true);
    }

 
    public void actionPerformed(ActionEvent e) {

        String username = userField.getText();

        String email = emailField.getText();

        String password =
                String.valueOf(passField.getPassword());

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO users(username,email,password) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, username);

            ps.setString(2, email);

            ps.setString(3, password);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(this,
                        "Registration Successful");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Registration Failed");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new RegisterFrame();
    }
}