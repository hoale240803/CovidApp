package com.example.covidapp.Controller;

public class CovidCountry {
    private String covidCountry, cases, totalCases, deaths, totalDeaths, recovered, critical;

    public CovidCountry(String covidCountry, String cases) {
        this.covidCountry = covidCountry;
        this.cases = cases;
        this.totalCases = totalCases;
        this.deaths = deaths;
        this.totalDeaths = totalDeaths;
        this.recovered = recovered;
        this.critical = critical;
    }

    public String getCovidCountry() {
        return covidCountry;
    }


    public String getTotalCases() {
        return totalCases;
    }

}
