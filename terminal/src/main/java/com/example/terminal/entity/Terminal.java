package com.example.terminal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer bankNo;
    private String model;
    private String sn;
    private Boolean wireless;
    private Boolean eth;
    private Boolean wifi;
    private Boolean gprs;
    private Long contractId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBankNo() {
        return bankNo;
    }

    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getsN() {
        return sn;
    }

    public void setsN(String sn) {
        this.sn = sn;
    }

    public Boolean getWireless() {
        return wireless;
    }

    public void setWireless(Boolean wireless) {
        this.wireless = wireless;
    }

    public Boolean getEth() {
        return eth;
    }

    public void setEth(Boolean eth) {
        this.eth = eth;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getGprs() {
        return gprs;
    }

    public void setGprs(Boolean gprs) {
        this.gprs = gprs;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
