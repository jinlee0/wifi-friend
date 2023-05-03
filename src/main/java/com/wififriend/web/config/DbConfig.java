package com.wififriend.web.config;

import com.wififriend.web.adapter.WifiClient;
import com.wififriend.web.entity.Wifi;
import com.wififriend.web.repository.TransactionExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.StringJoiner;

public class DbConfig {
    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL_PREFIX = "jdbc:sqlite:";
    public static final String DB_NAME = "C:\\projects\\zerobase\\mission1\\wifi-friend\\web\\wifi-friend-db";
    public static final String DB_URL = DB_URL_PREFIX + DB_NAME;

    static class DbInit {

        private DbInit() {
            initDb();
        }

        private void initDb() {
            WifiClient wifiClient = new WifiClient();
            TransactionExecutor tx = TransactionExecutor.getInstance();
            try {
                int last = 0;
                while (true) {
                    List<Wifi> info = wifiClient.getInfo(last + 1, last + 1000);
                    for (Wifi wifi : info) {
                        if (wifi.getManagementNumber().equals("-WF141587")) {
                            System.out.println("here");
                        }
                        String sql = makeInsertSql(wifi);
                        System.out.println(sql);
                        int affectedRows = tx.execUpdate(sql);
                        System.out.println(affectedRows);
                    }
                    last += 1000;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private String makeInsertSql(Wifi wifi) throws IllegalAccessException {
            StringJoiner sj = new StringJoiner(", ", "insert into wifi values(", ")");
            joinValues(wifi, sj, wifi.getClass().getSuperclass());
            joinValues(wifi, sj, wifi.getClass());
            return sj.toString();
        }

        private static void joinValues(Wifi wifi, StringJoiner sj, Class<?> clazz) throws IllegalAccessException {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) continue;
                field.setAccessible(true);
                String value = ((String)field.get(wifi)).replace("'", "");
                sj.add('\''+ value +'\'');
            }
        }

        public static void main(String[] args) {
            new DbInit();
        }
    }

}
