package com.wififriend.web.adapter;

import com.google.gson.Gson;
import com.wififriend.web.config.ApiConfig;
import com.wififriend.web.entity.Wifi;
import com.wififriend.web.utils.UrlMaker;
import lombok.Data;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WifiClient {

    private final RestClient restClient = new RestClient();

    public List<Wifi> getInfo(String district, String street) {
        String url = makeUrl(district, street);
        try (Response response = restClient.get(url)) {
            System.out.println(response.body());
            try {
                assert response.body() != null;
                WifiApiResponseDto wifiApiResponseDto = new Gson().fromJson(response.body().string(), WifiApiResponseDto.class);
                List<WifiInfoDto> row = wifiApiResponseDto.getTbPublicWifiInfo().getRow();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                return row.stream()
                        .map(dto -> new Wifi(
                                dto.getX_SWIFI_MGR_NO(),
                                dto.getX_SWIFI_WRDOFC(),
                                dto.getX_SWIFI_MAIN_NM(),
                                dto.getX_SWIFI_ADRES1(),
                                dto.getX_SWIFI_ADRES2(),
                                dto.getX_SWIFI_INSTL_FLOOR(),
                                dto.getX_SWIFI_INSTL_TY(),
                                dto.getX_SWIFI_INSTL_MBY(),
                                dto.getX_SWIFI_SVC_SE(),
                                dto.getX_SWIFI_CMCWR(),
                                dto.getX_SWIFI_CNSTC_YEAR(),
                                dto.getX_SWIFI_INOUT_DOOR(),
                                dto.getX_SWIFI_REMARS3(),
                                dto.getLAT(),
                                dto.getLNT(),
                                dto.getWORK_DTTM()
                        )).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String makeUrl(String district, String street) {
        List<String> paths = new ArrayList<>();
        paths.add(district);
        paths.add(street);
        return UrlMaker.makeUrl(ApiConfig.BASE_URL, paths, new ArrayList<>()) + "/";
    }

    @Data
    public static class WifiApiResponseDto {
        private TbPublicWifiInfoDto TbPublicWifiInfo;
    }

    @Data
    static class TbPublicWifiInfoDto {
        private List<WifiInfoDto> row;
    }

    @Data
    static class WifiInfoDto {
        private String X_SWIFI_MGR_NO;
        private String X_SWIFI_WRDOFC;
        private String X_SWIFI_MAIN_NM;
        private String X_SWIFI_ADRES1;
        private String X_SWIFI_ADRES2;
        private String X_SWIFI_INSTL_FLOOR;
        private String X_SWIFI_INSTL_TY;
        private String X_SWIFI_INSTL_MBY;
        private String X_SWIFI_SVC_SE;
        private String X_SWIFI_CMCWR;
        private String X_SWIFI_CNSTC_YEAR;
        private String X_SWIFI_INOUT_DOOR;
        private String X_SWIFI_REMARS3;
        private String LAT;
        private String LNT;
        private String WORK_DTTM;
    }
}
