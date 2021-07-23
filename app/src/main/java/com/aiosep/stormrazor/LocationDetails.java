package com.aiosep.stormrazor;

public class LocationDetails {

    public String day;
    public String percentage;
    public String maxTemp;
    public String minTemp;

    public LocationDetails(String day, String percentage, String maxTemp, String minTemp) {
        this.day = day;
        this.percentage = percentage;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getDay() {
        return day;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }
}
