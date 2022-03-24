package com.snow.geodata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EarthQuake {
    private String type;
    @JsonProperty("features")
    List<Features> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "EarthQuake{" +
                "type='" + type + '\'' +
                ", features=" + features +
                '}';
    }
}
