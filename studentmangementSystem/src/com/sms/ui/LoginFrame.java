package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.db.DBConnection;

public class LoginFrame extends JFrame implements ActionListener {

    JPanel mainPanel, leftPanel, rightPanel;

    JLabel titleLabel, userLabel, passLabel, welcomeLabel;

    JTextField userField;

    JPasswordField passField;

    JButton loginBtn;

    public LoginFrame() {

        setTitle("Student Management System");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

    
        mainPanel = new JPanel();

        mainPanel.setBounds(0, 0, 900, 500);

        mainPanel.setLayout(null);

        add(mainPanel);

      
        leftPanel = new JPanel();

        leftPanel.setBounds(0, 0, 400, 500);

        leftPanel.setBackground(new Color(33, 150, 243));

        leftPanel.setLayout(null);

        mainPanel.add(leftPanel);

      
        welcomeLabel = new JLabel("Welcome Back!");

        welcomeLabel.setForeground(Color.WHITE);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));

        welcomeLabel.setBounds(70, 180, 300, 40);

        leftPanel.add(welcomeLabel);

        JLabel info = new JLabel("Student Management System");

        info.setForeground(Color.WHITE);

        info.setFont(new Font("Arial", Font.PLAIN, 18));

        info.setBounds(70, 230, 300, 30);

        leftPanel.add(info);

  
        rightPanel = new JPanel();

        rightPanel.setBounds(400, 0, 500, 500);

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        mainPanel.add(rightPanel);

      
        titleLabel = new JLabel("LOGIN");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        titleLabel.setBounds(190, 60, 150, 40);

        rightPanel.add(titleLabel);

   
        userLabel = new JLabel("Username");

        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        userLabel.setBounds(70, 150, 100, 30);

        rightPanel.add(userLabel);

        userField = new JTextField();

        userField.setBounds(70, 185, 320, 40);

        userField.setFont(new Font("Arial", Font.PLAIN, 16));

        rightPanel.add(userField);

       
        passLabel = new JLabel("Password");

        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        passLabel.setBounds(70, 250, 100, 30);

        rightPanel.add(passLabel);

        passField = new JPasswordField();

        passField.setBounds(70, 285, 320, 40);

        passField.setFont(new Font("Arial", Font.PLAIN, 16));

        rightPanel.add(passField);

       
        loginBtn = new JButton("LOGIN");

        loginBtn.setBounds(140, 370, 180, 45);

        loginBtn.setBackground(new Color(33, 150, 243));

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));

        loginBtn.setFocusPainted(false);

        rightPanel.add(loginBtn);

        loginBtn.addActionListener(this);

        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {

        String username = userField.getText();

        String password = String.valueOf(passField.getPassword());

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, username);

            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                Dashboard d = new Dashboard();

                d.setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new LoginFrame();
    }
}