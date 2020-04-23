package com.android.veggitech.growapp.model;

public class FvtPlantModel {

    String fvtPlantId, plantName, plantDays, plantNumber, imageUrl;

    public FvtPlantModel() {
    }

    public FvtPlantModel(String fvtPlantId, String plantName, String plantDays, String plantNumber, String imageUrl) {
        this.fvtPlantId = fvtPlantId;
        this.plantName = plantName;
        this.plantDays = plantDays;
        this.plantNumber = plantNumber;
        this.imageUrl = imageUrl;
    }

    public String getFvtPlantId() {
        return fvtPlantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setFvtPlantId(String fvtPlantId) {
        this.fvtPlantId = fvtPlantId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setPlantDays(String plantDays) {
        this.plantDays = plantDays;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getPlantDays() {
        return plantDays;
    }

    public String getPlantNumber() {
        return plantNumber;
    }
}
