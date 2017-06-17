package com.venkyapps.airquality.features.airquality.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class Pm10 {


    @SerializedName("pollutant_description")
    private String pollutantDescription;
    private String units;
    private Float concentration;

    public String getPollutantDescription() {
        return pollutantDescription;
    }

    public void setPollutantDescription(String pollutantDescription) {
        this.pollutantDescription = pollutantDescription;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Float getConcentration() {
        return concentration;
    }

    public void setConcentration(Float concentration) {
        this.concentration = concentration;
    }

}