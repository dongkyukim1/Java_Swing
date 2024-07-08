package project.customer;

import project.KioskVO;
import project.db.Query;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CustomerDao {
    public ArrayList<KioskVO> getCoffee(){
        return Query.select("SELECT PRODUCT, PRICE, TYPE FROM KIOSK WHERE TYPE = 'COFFEE'");
    }

    public ArrayList<KioskVO> getDessert(){
        return Query.select("SELECT PRODUCT, PRICE, TYPE FROM KIOSK WHERE TYPE = 'DESSERT'");
    }

    public int insertHistory(KioskVO vo){
        return Query.insert("INSERT INTO PurchaseHistory (ITEM_NAME, QUANTITY, PRICE, PURCHASE_DATE) VALUES (?, ?, ?, ?)",
                vo.getPRODUCT(), vo.getQuantity(), vo.getPRICE(), new Timestamp(System.currentTimeMillis()));
    }
}