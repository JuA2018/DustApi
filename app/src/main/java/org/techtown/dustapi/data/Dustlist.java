package org.techtown.dustapi.data;

public class Dustlist {
    private String stationName;
    private String dataTime;
    private String pm10Value;
    private String pm25Value;

    public Dustlist() {}

    public Dustlist(String stationName, String dataTime, String pm10Value, String pm25Value) {
        this.stationName = stationName;
        this.dataTime = dataTime;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public String getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }
}
