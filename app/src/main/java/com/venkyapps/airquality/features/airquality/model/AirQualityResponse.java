package com.venkyapps.airquality.features.airquality.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class AirQualityResponse {

    private String datetime;
    @SerializedName("country_name")
    private String countryName;
    @SerializedName("breezometer_aqi")
    private Integer breezometerAqi;
    @SerializedName("breezometer_color")
    private String breezometerColor;
    @SerializedName("breezometer_description")
    private String breezometerDescription;
    @SerializedName("country_aqi")
    private Integer countryAqi;
    @SerializedName("country_aqi_prefix")
    private String countryAqiPrefix;
    @SerializedName("country_color")
    private String countryColor;
    @SerializedName("country_description")
    private String countryDescription;
    @SerializedName("data_valid")
    private Boolean dataValid;
    @SerializedName("key_valid")
    private Boolean keyValid;
    @SerializedName("random_recommendations")
    private RandomRecommendations randomRecommendations;
    @SerializedName("dominant_pollutant_canonical_name")
    private String dominantPollutantCanonicalName;
    @SerializedName("dominant_pollutant_description")
    private String dominantPollutantDescription;
    @SerializedName("dominant_pollutant_text")
    private DominantPollutantText dominantPollutantText;
    @SerializedName("pollutants")
    private Pollutants pollutants;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getBreezometerAqi() {
        return breezometerAqi;
    }

    public void setBreezometerAqi(Integer breezometerAqi) {
        this.breezometerAqi = breezometerAqi;
    }

    public String getBreezometerColor() {
        return breezometerColor;
    }

    public void setBreezometerColor(String breezometerColor) {
        this.breezometerColor = breezometerColor;
    }

    public String getBreezometerDescription() {
        return breezometerDescription;
    }

    public void setBreezometerDescription(String breezometerDescription) {
        this.breezometerDescription = breezometerDescription;
    }

    public Integer getCountryAqi() {
        return countryAqi;
    }

    public void setCountryAqi(Integer countryAqi) {
        this.countryAqi = countryAqi;
    }

    public String getCountryAqiPrefix() {
        return countryAqiPrefix;
    }

    public void setCountryAqiPrefix(String countryAqiPrefix) {
        this.countryAqiPrefix = countryAqiPrefix;
    }

    public String getCountryColor() {
        return countryColor;
    }

    public void setCountryColor(String countryColor) {
        this.countryColor = countryColor;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public Boolean getDataValid() {
        return dataValid;
    }

    public void setDataValid(Boolean dataValid) {
        this.dataValid = dataValid;
    }

    public Boolean getKeyValid() {
        return keyValid;
    }

    public void setKeyValid(Boolean keyValid) {
        this.keyValid = keyValid;
    }

    public RandomRecommendations getRandomRecommendations() {
        return randomRecommendations;
    }

    public void setRandomRecommendations(RandomRecommendations randomRecommendations) {
        this.randomRecommendations = randomRecommendations;
    }

    public String getDominantPollutantCanonicalName() {
        return dominantPollutantCanonicalName;
    }

    public void setDominantPollutantCanonicalName(String dominantPollutantCanonicalName) {
        this.dominantPollutantCanonicalName = dominantPollutantCanonicalName;
    }

    public String getDominantPollutantDescription() {
        return dominantPollutantDescription;
    }

    public void setDominantPollutantDescription(String dominantPollutantDescription) {
        this.dominantPollutantDescription = dominantPollutantDescription;
    }

    public DominantPollutantText getDominantPollutantText() {
        return dominantPollutantText;
    }

    public void setDominantPollutantText(DominantPollutantText dominantPollutantText) {
        this.dominantPollutantText = dominantPollutantText;
    }

    public Pollutants getPollutants() {
        return pollutants;
    }

    public void setPollutants(Pollutants pollutants) {
        this.pollutants = pollutants;
    }

}