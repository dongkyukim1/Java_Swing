package project.admin;

import project.KioskDAOimpl;
import project.KioskVO;
import project.ProductType;
import project.customer.PurchaseHistoryDAO;
import project.customer.PurchaseHistoryVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminModeGUI extends JFrame implements ActionListener {
    private Container contentPane;
    private JTextField productField, priceField;
    private JComboBox productType;
    private JButton insertButton, updateButton, deleteButton, viewButton, historyButton, mainMenuButton; // mainMenuButton 추가
    private JLabel statusLabel;
    private JTextArea resultArea;
    private KioskDAOimpl dao;

    public AdminModeGUI() {
        super("Admin Mode");

        dao = new KioskDAOimpl();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = getContentPane();
        contentPane.setLayout(null);

        productField = createField("이름:",50,30);
        priceField = createField("가격:",50,80);
        productType = createCombo("타입",50,130);


        insertButton = createButton("입력", 50, 230, 100, 30);
        updateButton = createButton("수정", 180, 230, 100, 30);
        deleteButton = createButton("삭제", 310, 230, 100, 30);
        viewButton = createButton("조회", 440, 230, 100, 30);
        historyButton = createButton("구매내역조회", 202, 275, 200, 40);
        mainMenuButton = createButton("뒤로",450,100,100,100);


        contentPane.setLayout(null);
        contentPane.add(insertButton);
        contentPane.add(updateButton);
        contentPane.add(deleteButton);
        contentPane.add(viewButton);
        contentPane.add(historyButton);
        contentPane.add(mainMenuButton); // mainMenuButton 추가


        statusLabel = new JLabel();
        statusLabel.setBounds(50, 350, 500, 25);
        contentPane.add(statusLabel);


        resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 390, 500, 150);
        contentPane.add(scrollPane);

        setContentPane(contentPane);
    }


    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(this); //
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String PRODUCT = this.productField.getText();
        String PRICE = this.priceField.getText();
        ProductType TYPE = ProductType.fromName(String.valueOf(productType.getSelectedItem()));
        KioskVO vo = new KioskVO(PRODUCT, PRICE, TYPE, "N", null, null);

        if (e.getSource() == insertButton) {
            // 입력 기능 구현
            int nCnt = dao.KioskInsert(vo);
            if (nCnt >= 1) {
                statusLabel.setText("입력 성공 >>>>>" + nCnt);
            } else {
                statusLabel.setText("입력 실패" + nCnt);
            }
        } else if (e.getSource() == updateButton) {
            // 수정 기능 구현
            int nCntUpdate = dao.KioskUpdate(vo);

            if (nCntUpdate >= 1) {
                statusLabel.setText("수정을 완료하였습니다. " + nCntUpdate);
            } else {
                statusLabel.setText("수정을 실패하였습니다." + nCntUpdate);
            }
        } else if (e.getSource() == deleteButton) {
            // 삭제 기능 구현
            int nCntDelete = dao.KioskDelete(vo);

            if (nCntDelete >= 1) {
                statusLabel.setText("삭제 완료" + nCntDelete);
            } else {
                statusLabel.setText("삭제 실패");
            }
        } else if (e.getSource() == viewButton) {
            // 조회 기능 구현
            ArrayList<KioskVO> resultList = dao.SelectALL();
            if (resultList != null) {
                StringBuilder resultText = new StringBuilder();
                for (KioskVO item : resultList) {
                    resultText.append("상품명: ").append(item.getPRODUCT()).append(", ");
                    resultText.append("상품 가격: ").append(item.getPRICE()).append(", ");
                    resultText.append("상품 타입: ").append(item.getTYPE().name()).append("\n");
                }
                resultArea.setText(resultText.toString());
            } else {
                resultArea.setText("조회된 데이터가 없습니다.");
            }
        }   if (e.getActionCommand().equals("구매내역조회")) {
            try {
                // 구매 기록 조회
                ArrayList<PurchaseHistoryVO> historyList = PurchaseHistoryDAO.getAllPurchaseHistory();
                if (historyList != null && !historyList.isEmpty()) {
                    StringBuilder resultText = new StringBuilder();
                    for (PurchaseHistoryVO history : historyList) {
                        resultText.append("구매 일자: ").append(history.getPurchaseDate()).append(", ");
                        resultText.append("상품명: ").append(history.getItemName()).append(", ");
                        resultText.append("수량: ").append(history.getQuantity()).append(", ");
                        resultText.append("가격: ").append(history.getPrice()).append("\n");
                    }
                    resultArea.setText(resultText.toString());
                } else {
                    resultArea.setText("조회된 구매 기록이 없습니다.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                resultArea.setText("구매 기록 조회 중 오류가 발생했습니다.");
            }
        }
    }


    private JTextField createField(String labelText, int x, int y){
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 100, 25); // 위치와 크기 설정
        contentPane.add(label);

        JTextField field = new JTextField();
        field.setBounds(x+100, y, 200, 25); // 위치와 크기 설정
        contentPane.add(field);
        return field;
    }

    private JComboBox createCombo( String labelText, int x, int y){
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 100, 25); // 위치와 크기 설정
        contentPane.add(label);

        JComboBox comboBox = new JComboBox(ProductType.getComboList());
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(x+100, y, 200, 25); // 위치와 크기 설정
        contentPane.add(comboBox);

        return comboBox;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminModeGUI adminModeGUI = new AdminModeGUI();
            adminModeGUI.setVisible(true);
        });
    }
}