package com.sms.ui;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.db.DBConnection;

public class SearchStudent extends JFrame implements ActionListener {

    JPanel panel;

    JLabel titleLabel, searchLabel;

    JTextField searchField;

    JButton searchBtn, clearBtn;

    JTable table;

    DefaultTableModel model;

    JScrollPane scrollPane;

    public SearchStudent() {

        setTitle("Search Student");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        panel = new JPanel();

        panel.setBounds(0, 0, 1000, 600);

        panel.setBackground(new Color(245, 247, 250));

        panel.setLayout(null);

        add(panel);

        titleLabel = new JLabel("Search Student");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        titleLabel.setBounds(360, 20, 300, 40);

        panel.add(titleLabel);

        searchLabel = new JLabel("Enter Name");

        searchLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        searchLabel.setBounds(100, 100, 150, 30);

        panel.add(searchLabel);

        searchField = new JTextField();

        searchField.setBounds(230, 95, 300, 40);

        searchField.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(searchField);

        searchBtn = new JButton("Search");

        searchBtn.setBounds(570, 95, 150, 40);

        searchBtn.setBackground(new Color(33, 150, 243));

        searchBtn.setForeground(Color.WHITE);

        searchBtn.setFont(new Font("Arial", Font.BOLD, 16));

        searchBtn.setFocusPainted(false);

        panel.add(searchBtn);

        clearBtn = new JButton("Clear");

        clearBtn.setBounds(750, 95, 150, 40);

        clearBtn.setBackground(new Color(244, 67, 54));

        clearBtn.setForeground(Color.WHITE);

        clearBtn.setFont(new Font("Arial", Font.BOLD, 16));

        clearBtn.setFocusPainted(false);

        panel.add(clearBtn);

        String columns[] = {
                "ID",
                "Name",
                "Email",
                "Course",
                "Phone",
                "Enrollment Date"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setRowHeight(35);

        table.setFont(new Font("Arial", Font.PLAIN, 15));

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        table.getTableHeader().setBackground(
                new Color(33, 150, 243)
        );

        table.getTableHeader().setForeground(Color.WHITE);

        scrollPane = new JScrollPane(table);

        scrollPane.setBounds(40, 180, 900, 320);

        panel.add(scrollPane);

        searchBtn.addActionListener(this);

        clearBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchBtn) {

            String keyword = searchField.getText();

            try {

                model.setRowCount(0);

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "SELECT * FROM students WHERE name LIKE ?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, "%" + keyword + "%");

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    int id = rs.getInt("student_id");

                    String name = rs.getString("name");

                    String email = rs.getString("email");

                    String course = rs.getString("course");

                    String phone = rs.getString("phone");

                    String date =
                            rs.getString("enrollment_date");

                    Object row[] = {
                            id,
                            name,
                            email,
                            course,
                            phone,
                            date
                    };

                    model.addRow(row);
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == clearBtn) {

            searchField.setText("");

            model.setRowCount(0);
        }
    }

    public static void main(String[] args) {

        new SearchStudent();
    }
}