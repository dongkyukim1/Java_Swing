package project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class OracleConnectivity {
    public static final  String JDBC_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static final String JDBC_ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public static final String JDBC_ORACLE_USER = "scott";
    public static final String JDBC_ORACLE_PW = "tiger";

    public static Connection getConnection(){
        Connection conn = null;

    try {
        Class.forName(OracleConnectivity.JDBC_ORACLE_DRIVER);
        conn = DriverManager.getConnection(OracleConnectivity.JDBC_ORACLE_URL,
                                            OracleConnectivity.JDBC_ORACLE_USER,
                                            OracleConnectivity.JDBC_ORACLE_PW);
    }catch (ClassNotFoundException e) {
        System.out.println("오라클 드라이버를 찾을 수 없습니다: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("오라클 연결에 문제가 있습니다: " + e.getMessage());
    }
        return conn;
    }
}