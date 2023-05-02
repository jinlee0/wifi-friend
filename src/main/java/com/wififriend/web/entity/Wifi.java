package com.wififriend.web.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String deployedYear;
    private String inOrOut;
    private String accessEnvironment;
    private String latitude;
    private String longitude;
    private String workedDate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    public Wifi(String managementNumber, String district, String name, String street, String detailAddress, String deployedFloor, String deployedType, String deploymentOrg, String serviceType, String netType, String deployedYear, String inOrOut, String accessEnvironment, String latitude, String longitude, LocalDateTime workedDate) {
        this(managementNumber, district, name, street, detailAddress, deployedFloor, deployedType, deploymentOrg, serviceType, netType, deployedYear, inOrOut, accessEnvironment, latitude, longitude);
        this.workedDate = workedDate.format(formatter);
    }

    public Wifi(String managementNumber, String district, String name, String street, String detailAddress, String deployedFloor, String deployedType, String deploymentOrg, String serviceType, String netType, String deployedYear, String inOrOut, String accessEnvironment, String latitude, String longitude, String workedDate) {
        this(managementNumber, district, name, street, detailAddress, deployedFloor, deployedType, deploymentOrg, serviceType, netType, deployedYear, inOrOut, accessEnvironment, latitude, longitude);
        this.workedDate = workedDate;
    }

    private Wifi(String managementNumber, String district, String name, String street, String detailAddress, String deployedFloor, String deployedType, String deploymentOrg, String serviceType, String netType, String deployedYear, String inOrOut, String accessEnvironment, String latitude, String longitude) {
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
    }

    public LocalDateTime getWorkedDate() {
        return LocalDateTime.parse(workedDate, formatter);
    }

}
