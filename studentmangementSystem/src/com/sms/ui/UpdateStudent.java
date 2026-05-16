package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.db.DBConnection;

public class UpdateStudent extends JFrame implements ActionListener {

    JPanel panel;

    JLabel titleLabel,
            idLabel,
            nameLabel,
            emailLabel,
            courseLabel,
            phoneLabel;

    JTextField idField,
            nameField,
            emailField,
            courseField,
            phoneField;

    JButton updateBtn, clearBtn;

    public UpdateStudent() {

        setTitle("Update Student");

        setSize(700, 700);
        setResizable(false);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        panel = new JPanel();

        panel.setBounds(0, 0, 700, 600);

        panel.setBackground(new Color(245, 247, 250));

        panel.setLayout(null);

        add(panel);

        titleLabel = new JLabel("Update Student");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        titleLabel.setBounds(220, 30, 300, 40);

        panel.add(titleLabel);

        idLabel = new JLabel("Student ID");

        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        idLabel.setBounds(100, 110, 150, 30);

        panel.add(idLabel);

        idField = new JTextField();

        idField.setBounds(100, 145, 500, 45);

        idField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(idField);

        nameLabel = new JLabel("Full Name");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        nameLabel.setBounds(100, 210, 150, 30);

        panel.add(nameLabel);

        nameField = new JTextField();

        nameField.setBounds(100, 245, 500, 45);

        nameField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(nameField);

        emailLabel = new JLabel("Email");

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        emailLabel.setBounds(100, 310, 150, 30);

        panel.add(emailLabel);

        emailField = new JTextField();

        emailField.setBounds(100, 345, 500, 45);

        emailField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(emailField);

        courseLabel = new JLabel("Course");

        courseLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        courseLabel.setBounds(100, 410, 150, 30);

        panel.add(courseLabel);

        courseField = new JTextField();

        courseField.setBounds(100, 445, 500, 45);

        courseField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(courseField);

        phoneLabel = new JLabel("Phone");

        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        phoneLabel.setBounds(100, 510, 150, 30);

        panel.add(phoneLabel);

        phoneField = new JTextField();

        phoneField.setBounds(100, 545, 300, 40);

        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(phoneField);

        updateBtn = new JButton("Update");

        updateBtn.setBounds(430, 545, 170, 40);

        updateBtn.setBackground(new Color(33, 150, 243));

        updateBtn.setForeground(Color.WHITE);

        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));

        updateBtn.setFocusPainted(false);

        panel.add(updateBtn);

        updateBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int id = Integer.parseInt(idField.getText());

        String name = nameField.getText();

        String email = emailField.getText();

        String course = courseField.getText();

        String phone = phoneField.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "UPDATE students SET name=?, email=?, course=?, phone=? WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);

            ps.setString(2, email);

            ps.setString(3, course);

            ps.setString(4, phone);

            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(this,
                        "Student Updated Successfully");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Student Not Found");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new UpdateStudent();
    }
}