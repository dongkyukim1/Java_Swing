package project.customer;

import project.db.OracleConnectivity;

import java.sql.*;
import java.util.ArrayList;

public class PurchaseHistoryDAO {

    public static ArrayList<PurchaseHistoryVO> getAllPurchaseHistory() throws SQLException {
        ArrayList<PurchaseHistoryVO> historyList = new ArrayList<>();

        try (Connection conn = OracleConnectivity.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT ITEM_NAME, QUANTITY, PRICE, PURCHASE_DATE FROM PurchaseHistory");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                int price = rs.getInt("PRICE");
                Timestamp purchaseDate = rs.getTimestamp("PURCHASE_DATE");

                // PurchaseHistoryVO 객체 생성 및 리스트에 추가
                PurchaseHistoryVO history = new PurchaseHistoryVO(itemName, quantity, price, purchaseDate);
                historyList.add(history);
            }
        }
        return historyList;
    }
}
