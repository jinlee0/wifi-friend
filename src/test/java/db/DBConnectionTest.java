package db;

import com.wififriend.web.entity.Member;
import com.wififriend.web.repository.TransactionExecutor;

import java.util.List;

public class DBConnectionTest {
    public static void main(String[] args) {
        testConnection();
    }

    private static void testConnection() {
        TransactionExecutor instance = TransactionExecutor.getInstance();
        List<Member> members = instance.exec("SELECT * FROM Member", Member.class);
        for (Member member : members) {
            System.out.println(member);
        }
//        Connection conn = null;
//
//        try {
//            Class.forName(DbConfig.JDBC_DRIVER);
//
//            conn = DriverManager.getConnection(DbConfig.DB_URL);
//
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member");
//            ResultSet rs = ps.executeQuery();
////            WifiDbConnection instance = WifiDbConnection.getInstance();
////            ResultSet rs = instance.exec("SELECT * FROM Member", Member.class);
//
//            while (rs.next()) {
//                String id = rs.getString("id");
//                String name = rs.getString("name");
//
//                System.out.println(id + "	" + name);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                }
//            }
//        }
    }
}
