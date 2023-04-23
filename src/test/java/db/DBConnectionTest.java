package db;

import com.wififriend.web.config.DbConfig;

import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) {
        testConnection();
    }

    private static void testConnection() {
        Connection conn = null;

        try {
            Class.forName(DbConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DbConfig.DB_URL);

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");

                System.out.println(id + "	" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
