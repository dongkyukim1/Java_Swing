package project.customer;

import project.KioskVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class CustomerMainGUI extends JFrame {
    private CustomerDao dao = new CustomerDao();
    private ArrayList<KioskVO> coffee;
    private ArrayList<KioskVO> dessert;
    private JLabel totalLabel = new JLabel("총 가격: 0원");
    private JPanel selectedItemsPanel = new JPanel();
    private JTextArea selectedItemsTextArea = new JTextArea();
    private ImageIcon backgroundImage = new ImageIcon("C:\\Users\\kosmo\\Desktop\\7조_스타벅스키오스크\\project\\swing\\배경\\구매화면1.jpg");

    public CustomerMainGUI() {
        setTitle("손님모드");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(95, 80, 488, 497);
        backgroundPanel.add(scrollPane);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JPanel scrollContentPanel = new JPanel();
        scrollContentPanel.setLayout(new BorderLayout());
        scrollContentPanel.add(buttonsPanel, BorderLayout.NORTH);
        scrollPane.setViewportView(scrollContentPanel);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 커피 탭 추가
        JPanel coffeePanel = new JPanel();
        coffeePanel.setLayout(new BoxLayout(coffeePanel, BoxLayout.Y_AXIS));

        coffee = dao.getCoffee();
        createButtonsForItems(coffee, coffeePanel, 0);
        tabbedPane.addTab("커피", coffeePanel);

        // 디저트 탭 추가
        JPanel dessertPanel = new JPanel();
        dessertPanel.setLayout(new BoxLayout(dessertPanel, BoxLayout.Y_AXIS));

        dessert = dao.getDessert();
        createButtonsForItems(dessert, dessertPanel, 0);
        tabbedPane.addTab("디저트", dessertPanel);

        scrollContentPanel.add(tabbedPane, BorderLayout.CENTER);

        totalLabel.setBounds(350, 350, 200, 30);
        backgroundPanel.add(totalLabel);

        JButton purchaseButton = new JButton("구매");
        purchaseButton.setBounds(500, 300, 70, 50);
        scrollContentPanel.add(purchaseButton, BorderLayout.SOUTH);

        purchaseButton.addActionListener(e -> {
            try {
                insertPurchase();
                JOptionPane.showMessageDialog(CustomerMainGUI.this, "구매가 완료되었습니다.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(CustomerMainGUI.this, "구매 중 오류가 발생했습니다.");
            }
        });

        JButton cancelButton = new JButton("취소");
        cancelButton.setBounds(480, 500, 70, 50);
        cancelButton.addActionListener(e -> {
            resetLastQuantity();
            updateTotalPrice();
            updateSelectedItemsInfo();
        });
        buttonsPanel.add(cancelButton); // 취소 버튼을 buttonsPanel에 추가

        setLocationRelativeTo(null);
        setVisible(true);

        selectedItemsPanel.setLayout(new BorderLayout());
        selectedItemsPanel.setBounds(115, 380, 450, 150);
        backgroundPanel.add(selectedItemsPanel);

        selectedItemsTextArea.setEditable(false);
        selectedItemsPanel.add(selectedItemsTextArea, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createButtonsForItems(ArrayList<KioskVO> list, JPanel panel, int yOffset) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 수직 박스 레이아웃 설정
        list.forEach(data -> {
            String itemName = data.getPRODUCT();
            String itemPrice = data.getPRICE();

            JButton button = new JButton(itemName + " - $" + itemPrice + " (" + data.getQuantity() + ")");
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setBorderPainted(false);
            button.setFocusPainted(false); // 포커스 시 테두리 제거
            button.setBorder(new EmptyBorder(5, 5, 5, 5)); // 테두리 제거
            button.setContentAreaFilled(false); // 배경 투명 설정
            panel.add(button);


            button.addActionListener(e -> {
                int quantity = data.getQuantity() + 1;
                data.setQuantity(quantity);

                updateTotalPrice();
                updateSelectedItemsInfo();

                button.setText(itemName + " - $" + itemPrice + " (" + quantity + ")");
            });

            panel.add(Box.createVerticalStrut(10)); // 버튼 사이의 간격 조절
        });
    }

    private void insertPurchase() throws SQLException {
        //
        //
        //구매 내역 저장
        for (KioskVO vo : coffee) {
            dao.insertHistory(vo);
        }

        // 디저트 구매 내역 저장
        for (KioskVO vo : dessert) {
            dao.insertHistory(vo);
        }
    }

    private void resetLastQuantity() {
        // 마지막으로 버튼을 누른 상품의 수량을 0으로 설정
        for (KioskVO coffeeItem : coffee) {
            if (coffeeItem.getQuantity() > 0) {
                coffeeItem.setQuantity(coffeeItem.getQuantity() - 1);
                break; // 마지막으로 버튼을 누른 상품만 취소하므로 반복문을 종료합니다.
            }
        }
        for (KioskVO dessertItem : dessert) {
            if (dessertItem.getQuantity() > 0) {
                dessertItem.setQuantity(dessertItem.getQuantity() - 1);
                break; // 마지막으로 버튼을 누른 상품만 취소하므로 반복문을 종료합니다.
            }
        }
    }

    private void updateSelectedItemsInfo() {
        StringBuilder selectedItemsInfo = new StringBuilder();
        selectedItemsInfo.append("선택한 상품:\n");


        coffee.forEach(data -> {
            int quantity = data.getQuantity();
            int totalPrice = quantity * Integer.parseInt(data.getPRICE());
            if (quantity > 0) {
                selectedItemsInfo.append(data.getPRODUCT()).append(" x ").append(quantity).append(": ").append(totalPrice);
            }
        });

        dessert.forEach(data -> {
            int quantity = data.getQuantity();
            int totalPrice = quantity * Integer.parseInt(data.getPRICE());
            if (quantity > 0) {
                selectedItemsInfo.append(data.getPRODUCT()).append(" x ").append(quantity).append(": ").append(totalPrice);
            }
        });

        selectedItemsTextArea.setText(selectedItemsInfo.toString());
        selectedItemsTextArea.setBounds(40, 420, 495, 150);
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;

        for (KioskVO coffeeItem : coffee) {
            totalPrice += coffeeItem.getQuantity() * Integer.parseInt(coffeeItem.getPRICE());
        }

        for (KioskVO dessertItem : dessert) {
            totalPrice += dessertItem.getQuantity() * Integer.parseInt(dessertItem.getPRICE());
        }

        return totalPrice;
    }

    private void updateTotalPrice() {
        totalLabel.setText("총 가격: " + calculateTotalPrice() + "원");
    }
}