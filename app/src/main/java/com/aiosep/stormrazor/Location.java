package com.aiosep.stormrazor;

public class Location {
    private String name;
    private String coordonate;
    private String temperature;
    private String spec;

    public Location(String name, String coordonate, String temperature,String spec) {
        this.name = name;
        this.coordonate = coordonate;
        this.temperature = temperature;
        this.spec=spec;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordonate(String coordonate) {
        this.coordonate = coordonate;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public String getCoordonate() {
        return coordonate;
    }

    public String getTemperature() {
        return temperature;
    }
}
