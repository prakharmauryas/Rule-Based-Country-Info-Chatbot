package com.ajsd.chatbot.model;


/*
    This class is used to store data about
    a country.
    It will be used to associate a country name with the capital, nationalAnimal and nationalFlower.
 */
public class CountryInfo {

    private String capital;
    private String nationalAnimal;
    private String nationalFlower;



    public CountryInfo(String capital, String nationalAnimal, String nationalFlower) {
        /* TODO 1: In this constructor which takes in the capital, nationalAnimal and nationalFlower
         *  as parameters, set the instance variables to the values passed in.
         *  Ensure that the values of nationalAnimal and nationalFlower are set to "Unknown", if nothing
         *  is passed or the values passed is null.
         */
        this.capital = capital;
        this.nationalAnimal = nationalAnimal == null || nationalAnimal.isEmpty() ? "Unknown" : nationalAnimal;
        this.nationalFlower = nationalFlower == null || nationalFlower.isEmpty() ? "Unknown" : nationalFlower;
    }


    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNationalAnimal() {
        return nationalAnimal;
    }

    public void setNationalAnimal(String nationalAnimal) {
        this.nationalAnimal = nationalAnimal;
    }

    public String getNationalFlower() {
        return nationalFlower;
    }

    public void setNationalFlower(String nationalFlower) {
        this.nationalFlower = nationalFlower;
    }

    @Override
    public String toString() {
        return "CountryInfo {" +
                "capital='" + capital + '\'' +
                ", nationalAnimal='" + nationalAnimal + '\'' +
                ", nationalFlower='" + nationalFlower + '\'' +
                '}';
    }

}
