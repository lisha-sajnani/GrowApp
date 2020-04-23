package com.android.veggitech.growapp.model;

public class MyPlantModel {
    //int image, plantDays, plantLeft;
    String plantId, plantName, startDate, endDate, plantDays;

    public MyPlantModel() {
    }

    public MyPlantModel(String plantId, String plantName, String plantDays,  String startDate, String endDate) {
        this.plantId = plantId;
        this.plantDays = plantDays;
        this.plantName = plantName;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getPlantName() {
        return plantName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getPlantDays() {
        return plantDays;
    }
}
