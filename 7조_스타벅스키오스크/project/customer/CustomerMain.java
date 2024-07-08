package project.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerMain {
    private Map<String, Integer> coffeePrices;
    private Map<String, Integer> dessertPrices;

    public CustomerMain() {

        initializePrices();
    }

    private void initializePrices() {
        coffeePrices = new HashMap<>();
        dessertPrices = new HashMap<>();

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger")) {
            PreparedStatement coffeeStmt = conn.prepareStatement("SELECT PRODUCT, PRICE FROM KIOSK");
            ResultSet coffeeRs = coffeeStmt.executeQuery();
            while (coffeeRs.next()) {
                String coffee = coffeeRs.getString("PRODUCT");
                int price = coffeeRs.getInt("PRICE");
                coffeePrices.put(coffee, price);
            }
            coffeeRs.close();
            coffeeStmt.close();

            PreparedStatement dessertStmt = conn.prepareStatement("SELECT PRODUCT, PRICE FROM KIOSK");
            ResultSet dessertRs = dessertStmt.executeQuery();
            while (dessertRs.next()) {
                String dessert = dessertRs.getString("PRODUCT");
                int price = dessertRs.getInt("PRICE");
                dessertPrices.put(dessert, price);
            }
            dessertRs.close();
            dessertStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}