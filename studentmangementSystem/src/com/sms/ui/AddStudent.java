package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.db.DBConnection;

public class AddStudent extends JFrame implements ActionListener {

    JPanel panel;

    JLabel titleLabel,
            nameLabel,
            emailLabel,
            courseLabel,
            phoneLabel,
            dateLabel;

    JTextField nameField,
            emailField,
            courseField,
            phoneField,
            dateField;

    JButton saveBtn;

    public AddStudent() {

        setTitle("Add Student");

        setSize(700, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        panel = new JPanel();

        panel.setBounds(0, 0, 700, 600);

        panel.setBackground(new Color(245, 247, 250));

        panel.setLayout(null);

        add(panel);

        titleLabel = new JLabel("Add Student");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        titleLabel.setBounds(240, 30, 250, 40);

        panel.add(titleLabel);

        nameLabel = new JLabel("Full Name");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        nameLabel.setBounds(100, 110, 120, 30);

        panel.add(nameLabel);

        nameField = new JTextField();

        nameField.setBounds(100, 145, 500, 45);

        nameField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(nameField);

        emailLabel = new JLabel("Email");

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        emailLabel.setBounds(100, 210, 120, 30);

        panel.add(emailLabel);

        emailField = new JTextField();

        emailField.setBounds(100, 245, 500, 45);

        emailField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(emailField);

        courseLabel = new JLabel("Course");

        courseLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        courseLabel.setBounds(100, 310, 120, 30);

        panel.add(courseLabel);

        courseField = new JTextField();

        courseField.setBounds(100, 345, 500, 45);

        courseField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(courseField);

        phoneLabel = new JLabel("Phone");

        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        phoneLabel.setBounds(100, 410, 120, 30);

        panel.add(phoneLabel);

        phoneField = new JTextField();

        phoneField.setBounds(100, 445, 500, 45);

        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(phoneField);

        dateLabel = new JLabel("Enrollment Date");

        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        dateLabel.setBounds(100, 510, 150, 30);

        panel.add(dateLabel);

        dateField = new JTextField();

        dateField.setBounds(260, 505, 200, 40);

        dateField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(dateField);

        saveBtn = new JButton("Save Student");

        saveBtn.setBounds(490, 505, 140, 40);

        saveBtn.setBackground(new Color(33, 150, 243));

        saveBtn.setForeground(Color.WHITE);

        saveBtn.setFont(new Font("Arial", Font.BOLD, 16));

        saveBtn.setFocusPainted(false);

        panel.add(saveBtn);

        saveBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();

        String email = emailField.getText();

        String course = courseField.getText();

        String phone = phoneField.getText();

        String date = dateField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO students(name,email,course,phone,enrollment_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);

            ps.setString(2, email);

            ps.setString(3, course);

            ps.setString(4, phone);

            ps.setString(5, date);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(this,
                        "Student Added Successfully");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Failed");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new AddStudent();
    }
}