package project.db;

import project.KioskVO;
import project.ProductType;

import java.sql.*;
import java.util.ArrayList;

public class Query {

    public static ArrayList<KioskVO> select(String sql) {
        ArrayList<KioskVO> resultList = new ArrayList<>();

        Connection conn = OracleConnectivity.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                KioskVO kiosk = new KioskVO(
                        rs.getString("PRODUCT"),
                        rs.getString("PRICE"),
                        ProductType.fromCode(rs.getString("TYPE")),
                        0
                );
                resultList.add(kiosk);
            }

        } catch (SQLException e) {
            System.out.println("조회 중 오류 발생: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        return resultList;
    }

    public static int insert(String sql, Object... params){
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = OracleConnectivity.getConnection();
            pstmt = conn.prepareStatement(sql);

            for(int i=1; i<=params.length; i++){
                pstmt.setObject(i,params[i-1]);
            }

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public static int update(String sql, Object... params){
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = OracleConnectivity.getConnection();
            pstmt = conn.prepareStatement(sql);

            for(int i=1; i<=params.length; i++){
                pstmt.setObject(i,params[i-1]);
            }

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public static int delete(String sql, Object... params){
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = OracleConnectivity.getConnection();
            pstmt = conn.prepareStatement(sql);

            for(int i=1; i<=params.length; i++){
                pstmt.setObject(i,params[i-1]);
            }

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}