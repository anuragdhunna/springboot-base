package com.springboot.base.models;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int deviceId;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    private int operatorId;

    private int protocolId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(int protocolId) {
        this.protocolId = protocolId;
    }
}
