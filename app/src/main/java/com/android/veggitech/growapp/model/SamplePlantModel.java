package com.android.veggitech.growapp.model;

public class SamplePlantModel {

    String plantId;
    String plantName;
    String imageUrl;
    String plantDays;
    String plantNumber;
    Boolean state;

    public SamplePlantModel() {
    }

    public SamplePlantModel(String plantId, String plantName, String imageUrl, String plantDays, String plantNumber, Boolean state) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.imageUrl = imageUrl;
        this.plantDays = plantDays;
        this.plantNumber = plantNumber;
        this.state = state;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPlantDays() {
        return plantDays;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public Boolean getState() {
        return state;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlantDays(String plantDays) {
        this.plantDays = plantDays;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
