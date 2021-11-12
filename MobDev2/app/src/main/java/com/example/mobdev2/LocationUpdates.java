package com.example.mobdev2;

public class LocationUpdates {
    private int id;
    private double longitute;
    private double latitude;
    private String dt;

    public LocationUpdates(double longitute, double latitude, String dt) {
        this.longitute = longitute;
        this.latitude = latitude;
        this.dt = dt;
    }

    public LocationUpdates(int id, double longitute, double latitude, String dt) {
        this.id = id;
        this.longitute = longitute;
        this.latitude = latitude;
        this.dt = dt;
    }

    public LocationUpdates() {
    }

    public int getId() {
        return id;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
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
                ", longitute=" + longitute +
                ", latitude=" + latitude +
                ", dt='" + dt + '\'' +
                '}';
    }
}
