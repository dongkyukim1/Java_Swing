package project.swing;

import project.customer.CustomerMainGUI;
import project.admin.PasswordCheckGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class
MainScreen extends JFrame {
    private ImageIcon backgroundImage = new ImageIcon("C:\\Users\\kosmo\\Desktop\\7조_스타벅스키오스크\\project\\swing\\배경\\배경.jpg");

    public MainScreen() {
        setTitle("메인 화면");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);

        JButton guestModeButton = new JButton("손님모드");
        JButton adminModeButton = new JButton("관리자모드");

        guestModeButton.setBounds(50, 500, 100, 50); // x, y, width, height
        adminModeButton.setBounds(600, 500, 100, 50); // x, y, width, height

        backgroundPanel.add(guestModeButton);
        backgroundPanel.add(adminModeButton);

        guestModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        CustomerMainGUI customerGUI = new CustomerMainGUI();
                        customerGUI.setVisible(true);

                    }
                });
            }
        });

        // 관리자 모드 버튼의 액션 리스너를 설정합니다.
        adminModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                PasswordCheckGUI passwordCheckGUI = new PasswordCheckGUI();
                passwordCheckGUI.setVisible(true);
            }
        });

        setContentPane(backgroundPanel);
        setVisible(true);
    }
}