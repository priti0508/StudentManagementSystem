package com.sms.ui;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.db.DBConnection;

public class ViewStudents extends JFrame implements ActionListener {

    JPanel panel;

    JLabel titleLabel;

    JTable table;

    DefaultTableModel model;

    JScrollPane scrollPane;

    JButton deleteBtn, refreshBtn;

    public ViewStudents() {

        setTitle("View Students");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        panel = new JPanel();

        panel.setBounds(0, 0, 1000, 600);

        panel.setBackground(new Color(245, 247, 250));

        panel.setLayout(null);

        add(panel);

        titleLabel = new JLabel("Student Records");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        titleLabel.setBounds(360, 20, 300, 40);

        panel.add(titleLabel);

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

        scrollPane.setBounds(40, 90, 900, 350);

        panel.add(scrollPane);

        deleteBtn = new JButton("Delete Student");

        deleteBtn.setBounds(260, 480, 180, 45);

        deleteBtn.setBackground(new Color(244, 67, 54));

        deleteBtn.setForeground(Color.WHITE);

        deleteBtn.setFont(new Font("Arial", Font.BOLD, 16));

        deleteBtn.setFocusPainted(false);

        panel.add(deleteBtn);

        refreshBtn = new JButton("Refresh");

        refreshBtn.setBounds(540, 480, 180, 45);

        refreshBtn.setBackground(new Color(76, 175, 80));

        refreshBtn.setForeground(Color.WHITE);

        refreshBtn.setFont(new Font("Arial", Font.BOLD, 16));

        refreshBtn.setFocusPainted(false);

        panel.add(refreshBtn);

        deleteBtn.addActionListener(this);

        refreshBtn.addActionListener(this);

        loadStudentData();

        setVisible(true);
    }

    public void loadStudentData() {

        try {

            model.setRowCount(0);

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students";

            PreparedStatement ps =
                    con.prepareStatement(query);

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

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refreshBtn) {

            loadStudentData();
        }

        if (e.getSource() == deleteBtn) {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please Select a Student");

            } else {

                int id = (int) model.getValueAt(row, 0);

                try {

                    Connection con =
                            DBConnection.getConnection();

                    String query =
                            "DELETE FROM students WHERE student_id=?";

                    PreparedStatement ps =
                            con.prepareStatement(query);

                    ps.setInt(1, id);

                    int result = ps.executeUpdate();

                    if (result > 0) {

                        JOptionPane.showMessageDialog(this,
                                "Student Deleted");

                        loadStudentData();

                    } else {

                        JOptionPane.showMessageDialog(this,
                                "Delete Failed");
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        new ViewStudents();
    }
}