package com.wififriend.web.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
public class Wifi extends BaseEntity {
    private String managementNumber;
    private String district;
    private String name;
    private String street;
    private String detailAddress;
    private String deployedFloor;
    private String deployedType;
    private String deploymentOrg;
    private String serviceType;
    private String netType;
    private int deployedYear;
    private String inOrOut;
    private String accessEnvironment;
    private String latitude;
    private String longitude;
    private LocalDateTime workedDate;

    public Wifi(String managementNumber, String district, String name, String street, String detailAddress, String deployedFloor, String deployedType, String deploymentOrg, String serviceType, String netType, int deployedYear, String inOrOut, String accessEnvironment, String latitude, String longitude, LocalDateTime workedDate) {
        this.managementNumber = managementNumber;
        this.district = district;
        this.name = name;
        this.street = street;
        this.detailAddress = detailAddress;
        this.deployedFloor = deployedFloor;
        this.deployedType = deployedType;
        this.deploymentOrg = deploymentOrg;
        this.serviceType = serviceType;
        this.netType = netType;
        this.deployedYear = deployedYear;
        this.inOrOut = inOrOut;
        this.accessEnvironment = accessEnvironment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workedDate = workedDate;
    }
}
