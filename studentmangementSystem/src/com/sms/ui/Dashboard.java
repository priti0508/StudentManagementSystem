package com.sms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    JPanel sidebar, topbar, mainPanel;

    JLabel titleLabel, welcomeLabel;

    JButton addBtn, viewBtn, updateBtn,
            searchBtn, logoutBtn;

    public Dashboard() {

        setTitle("Student Management System");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245, 247, 250));

        sidebar = new JPanel();

        sidebar.setBounds(0, 0, 250, 700);

        sidebar.setBackground(new Color(20, 33, 61));

        sidebar.setLayout(null);

        add(sidebar);

        titleLabel = new JLabel("SMS Dashboard");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titleLabel.setBounds(35, 40, 200, 40);

        sidebar.add(titleLabel);

        addBtn = createButton("Add Student", 130);

        viewBtn = createButton("View Students", 210);

        updateBtn = createButton("Update Student", 290);

        searchBtn = createButton("Search Student", 370);

        logoutBtn = createButton("Logout", 500);

        sidebar.add(addBtn);

        sidebar.add(viewBtn);

        sidebar.add(updateBtn);

        sidebar.add(searchBtn);

        sidebar.add(logoutBtn);

        topbar = new JPanel();

        topbar.setBounds(250, 0, 950, 80);

        topbar.setBackground(Color.WHITE);

        topbar.setLayout(null);

        add(topbar);

        welcomeLabel =
                new JLabel("Welcome to Student Management System");

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));

        welcomeLabel.setBounds(40, 20, 600, 40);

        topbar.add(welcomeLabel);

        mainPanel = new JPanel();

        mainPanel.setBounds(250, 80, 950, 620);

        mainPanel.setBackground(new Color(245, 247, 250));

        mainPanel.setLayout(null);

        add(mainPanel);

        JPanel card1 = createCard(
                "Total Students",
                "120",
                60,
                80
        );

        JPanel card2 = createCard(
                "Courses",
                "15",
                350,
                80
        );

        JPanel card3 = createCard(
                "Active Users",
                "30",
                640,
                80
        );

        mainPanel.add(card1);

        mainPanel.add(card2);

        mainPanel.add(card3);

        addBtn.addActionListener(this);

        viewBtn.addActionListener(this);

        updateBtn.addActionListener(this);

        searchBtn.addActionListener(this);

        logoutBtn.addActionListener(this);

        setVisible(true);
    }

    JButton createButton(String text, int y) {

        JButton btn = new JButton(text);

        btn.setBounds(25, y, 200, 50);

        btn.setFocusPainted(false);

        btn.setBackground(new Color(52, 73, 94));

        btn.setForeground(Color.WHITE);

        btn.setFont(new Font("Arial", Font.BOLD, 16));

        return btn;
    }

    JPanel createCard(String title,
                      String value,
                      int x,
                      int y) {

        JPanel panel = new JPanel();

        panel.setBounds(x, y, 240, 140);

        panel.setBackground(Color.WHITE);

        panel.setLayout(null);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        titleLabel.setBounds(25, 25, 180, 30);

        panel.add(titleLabel);

        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));

        valueLabel.setForeground(new Color(33, 150, 243));

        valueLabel.setBounds(25, 65, 180, 40);

        panel.add(valueLabel);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {

            new AddStudent();
        }

        if (e.getSource() == viewBtn) {

            new ViewStudents();
        }

        if (e.getSource() == updateBtn) {

            new UpdateStudent();
        }

        if (e.getSource() == searchBtn) {

            new SearchStudent();
        }

        if (e.getSource() == logoutBtn) {

            JOptionPane.showMessageDialog(this,
                    "Logged Out");

            new LoginFrame();

            dispose();
        }
    }

    public static void main(String[] args) {

        new Dashboard();
    }
}