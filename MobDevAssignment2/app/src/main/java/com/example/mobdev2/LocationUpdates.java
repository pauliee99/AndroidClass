package com.example.mobdev2;

public class LocationUpdates {
    private int id;
    private double longitude;
    private double latitude;
    private String dt;

    public LocationUpdates(double longitude, double latitude, String dt) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.dt = dt;
    }

    public LocationUpdates(int id, double longitude, double latitude, String dt) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dt = dt;
    }

    public LocationUpdates() {
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "LocationUpdates{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", dt='" + dt + '\'' +
                '}';
    }
}
