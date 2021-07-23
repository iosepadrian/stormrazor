package com.aiosep.stormrazor;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "location")
public class Location {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "coordonate")
    private String coordonate;
    @ColumnInfo(name = "temperature")
    private String temperature;
    @ColumnInfo(name = "spec")
    private String spec;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Ignore
    public Location(int id, String name, String coordonate, String temperature, String spec) {
        this.id = id;
        this.name = name;
        this.coordonate = coordonate;
        this.temperature = temperature;
        this.spec = spec;
    }

    public Location(String name, String coordonate, String temperature, String spec) {
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordonate='" + coordonate + '\'' +
                ", temperature='" + temperature + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
