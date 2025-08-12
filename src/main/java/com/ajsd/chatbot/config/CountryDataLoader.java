package com.ajsd.chatbot.config;


import com.ajsd.chatbot.model.CountryInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
    This class is used to load the countries, capitals, national animals and national flowers
    into a HashMap, which can be used by the chatbot later.

    It implements the singleton design pattern, which means that only one object of the class
    can be created and used throughout the application. It does this by:
    1. Creating a private constructor
    2. Creating a private static instance of the class
    3. Creating a public static method named getInstance() that returns the same instance of the class
        3(a).  If the instance is null, it creates a new instance of the class and returns it.
        3(b).  If the instance is not null, it returns the existing instance of the class.

 **/
@Component
public class CountryDataLoader {

    private static CountryDataLoader instance = null;

    private final Map<String, CountryInfo> countryData = new HashMap<>();



    public static CountryDataLoader getInstance() {

        // check if the instance has not been created before
        if (instance == null) {

            // if the instance is null then create new object
            instance = new CountryDataLoader();
        }

        return instance;
    }


    public void loadCountryData() {

        try (InputStream inputStream = CountryDataLoader.class.getClassLoader().getResourceAsStream("countries_data.json")) {
            if (inputStream == null) {
                throw new RuntimeException("countries_data.json not found");
            }

            // Use Jackson ObjectMapper to parse the JSON file
            ObjectMapper objectMapper = new ObjectMapper();

            // Map the JSON structure to a Map<String, CountryInfo>
            Map<String, Map<String, String>> rawData = objectMapper.readValue(inputStream, Map.class);

            // Convert raw data to CountryInfo objects and populate the map
            for (Map.Entry<String, Map<String, String>> entry : rawData.entrySet()) {
                String country = entry.getKey();
                Map<String, String> countryInfo = entry.getValue();

                String capital = countryInfo.get("capital");
                String nationalAnimal = countryInfo.getOrDefault("nationalAnimal", "Unknown");
                String nationalFlower = countryInfo.getOrDefault("nationalFlower", "Unknown");

                countryData.put(country.toLowerCase(), new CountryInfo(capital, nationalAnimal, nationalFlower));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error loading country data", e);
        }


    }


    /** TODO 2: create a method to return the return a certain property
     *          of a country. The country name is passed with the property
     *          name like "USA" and "nationalAnimal". The method should be
     *          named getCountryProperty(String country, String property) and
     *          should return the value of the property for the country. The
     *          country name should be converted to lowercase before searching in
     *          the HashMap.
     *          If the country is not found, it should return "Country not found".
     *          If the property is not found, it should return "Invalid property".
     **/
    public String getCountryProperty(String country, String property) {
        CountryInfo countryInfo = countryData.get(country.toLowerCase());
        if (countryInfo == null) {
            return "Country not found";
        }
        switch (property) {
            case "capital":
                return countryInfo.getCapital();
            case "nationalAnimal":
                return countryInfo.getNationalAnimal();
            case "nationalFlower":
                return countryInfo.getNationalFlower();
            default:
                return "Invalid property";
        }
    }


    /** TODO 3: Create a method to create an array of String objects with each country in the
     *          array being the name of a country, as available in the HashMap.
     *          The method should be named listAllCountries() and return the array.
     *          The array should be created using the keySet() method of the HashMap.
     **/
    public String[] listAllCountries() {
        return countryData.keySet().toArray(new String[0]);
    }

    /** TODO 4: Create a method to create an array of String objects with each country in the
     *          array being the name of a country, as available in the HashMap
     *          which begins with the String which is passed to the method.
     *          The method should be named listCountriesWhichStartsWith(String).
     *          The country and string passed should be converted to lowercase before searching in the HashMap.
     *          The method should use the stream() method of the HashMap to filter the countries
     *          and return the array.
     **/
    public String[] listCountriesWhichStartsWith(String startsWith) {
        return countryData.keySet().stream().filter(country -> country.toLowerCase().startsWith(startsWith.toLowerCase())).toArray(String[]::new);
    }


    /** TODO 5: Create a method to create an array of String objects with each country in the
     *          array being the name of a country, as available in the HashMap
     *          which ends with the String which is passed to the method.
     *          The method should be named listCountriesWhichEndsWith(String).
     *          The country and string passed should be converted to lowercase before searching in the HashMap.     *
     *          The method should use the stream() method of the HashMap to filter the countries
     *          and return the array.
     **/
    public String[] listCountriesWhichEndsWith(String endsWith) {
        return countryData.keySet().stream().filter(country -> country.toLowerCase().endsWith(endsWith.toLowerCase())).toArray(String[]::new);
    }


    public String[] listCountriesWhichContainsString(String contains) {
        return countryData.keySet().stream().filter(country -> country.toLowerCase().contains(contains.toLowerCase())).toArray(String[]::new);
    }

    public boolean isValidCountry(String country) {
        boolean isFound = countryData.containsKey(country.toLowerCase()) ;
        return isFound;
    }

}
