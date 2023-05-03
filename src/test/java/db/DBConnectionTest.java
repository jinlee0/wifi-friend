package db;

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
    }
}
