package com.aiosep.stormrazor;

public class SearchLocation {
    private String woeid;
    private String name;
    private String coordonates;

    public SearchLocation(String name, String coordonates) {
        this.name = name;
        this.coordonates = coordonates;
    }

    public SearchLocation(String woeid, String name, String coordonates) {
        this.woeid = woeid;
        this.name = name;
        this.coordonates = coordonates;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public String getWoeid() {
        return woeid;
    }

    public String getName() {
        return name;
    }

    public String getCoordonates() {
        return coordonates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordonates(String coordonates) {
        this.coordonates = coordonates;
    }

    @Override
    public String toString() {
        return "SearchLocation{" +
                "name='" + name + '\'' +
                ", coordonates='" + coordonates + '\'' +
                '}';
    }
}
