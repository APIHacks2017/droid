package com.venkyapps.airquality.features.airquality.model;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class OtherPollutants {
    public String name;
    public String concentration;

    public OtherPollutants(String name, String concentration) {
        this.name = name;
        this.concentration = concentration;
    }

    public String getconcentration() {
        return concentration;
    }

    public String getname() {
        return name;
    }

    public void setconcentration(String concentration) {
        this.concentration = concentration;
    }

    public void setname(String name) {
        this.name = name;
    }
}
