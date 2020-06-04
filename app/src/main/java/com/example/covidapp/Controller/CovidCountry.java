package com.example.covidapp.Controller;

public class CovidCountry {
    private String covidCountry, cases;
    private int image;

    public CovidCountry(String covidCountry, String cases, int image) {
        this.covidCountry = covidCountry;
        this.cases = cases;
        this.image=image;
    }

    public String getCovidCountry() {
        return covidCountry;
    }

    public void setCovidCountry(String covidCountry) {
        this.covidCountry = covidCountry;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
