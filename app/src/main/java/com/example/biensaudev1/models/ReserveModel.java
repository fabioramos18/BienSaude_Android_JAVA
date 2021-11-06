package com.example.biensaudev1.models;

public class ReserveModel {

    String service;
    String reserveday;
    String status;
    String center;

    public ReserveModel() {
    }

    public ReserveModel(String service, String reserveday, String status, String center) {
        this.service = service;
        this.reserveday = reserveday;
        this.status = status;
        this.center = center;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getReserveday() {
        return reserveday;
    }

    public void setReserveday(String reserveday) {
        this.reserveday = reserveday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
