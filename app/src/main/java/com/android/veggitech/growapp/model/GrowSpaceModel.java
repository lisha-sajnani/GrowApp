package com.android.veggitech.growapp.model;

public class GrowSpaceModel {

    String userId, growId, growSpaceName, location;
    Double length, width;

    public GrowSpaceModel() {
    }

    public GrowSpaceModel(String userId, String growId, String growSpaceName, String location, Double length, Double width) {
        this.userId = userId;
        this.growId = growId;
        this.growSpaceName = growSpaceName;
        this.location = location;
        this.length = length;
        this.width = width;
    }

    public String getUserId() {
        return userId;
    }

    public String getGrowId() {
        return growId;
    }

    public String getGrowSpaceName() {
        return growSpaceName;
    }

    public String getLocation() {
        return location;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }
}
