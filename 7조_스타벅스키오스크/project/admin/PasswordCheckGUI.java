package project.admin;

import project.admin.AdminModeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordCheckGUI extends JFrame implements ActionListener {
    private JTextField passwordField;
    private JButton submitButton;
    private JLabel statusLabel;
    private final String PASSWORD = "admin123";

    public PasswordCheckGUI() {
        super("Admin Password Check");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setBounds(50, 30, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 30, 200, 25);
        panel.add(passwordField);

        submitButton = new JButton("확인");
        submitButton.setBounds(130, 80, 100, 30);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        statusLabel = new JLabel();
        statusLabel.setBounds(50, 120, 300, 25);
        panel.add(statusLabel);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputPassword = passwordField.getText();
        if (inputPassword.equals(PASSWORD)) {
            statusLabel.setText("비밀번호 확인 성공!");
            AdminModeGUI adminModeGUI = new AdminModeGUI();
            adminModeGUI.setVisible(true);
            this.dispose();
        } else {
            statusLabel.setText("잘못된 비밀번호입니다.");
        }
    }
    }

