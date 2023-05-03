/* Java 1.8 샘플 코드 */

import com.wififriend.web.adapter.WifiClient;
import com.wififriend.web.config.ApiConfig;
import com.wififriend.web.entity.Wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
//        byConstantURL();
        byWifiClient();
    }

    private static void byWifiClient() {
        WifiClient wifiClient = new WifiClient();
        List<Wifi> info = wifiClient.getInfo(1, 1000);
        System.out.println(info);
    }

    private static void byConstantURL() throws IOException {
        URL url = new URL(/*URL*/ApiConfig.BASE_URL + "/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
