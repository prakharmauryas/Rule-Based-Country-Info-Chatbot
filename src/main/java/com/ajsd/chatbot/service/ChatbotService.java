package com.ajsd.chatbot.service;

import com.ajsd.chatbot.config.CountryDataLoader;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final CountryDataLoader countryDataLoader;

    public ChatbotService(CountryDataLoader countryDataLoader) {
        this.countryDataLoader = countryDataLoader;
        this.countryDataLoader.loadCountryData();
    }

    /** TODO 6: Create equivalent service methods to call the
     *          following method of the CountryLoader class:
     *          1. getCountryProperty(String, String)
     *        The service method should call its equivalent method in CountryLoaderClass
     *        and return the result. It should have the same name as that in the
     *        CountryLoader class
     **/
    public String getCountryProperty(String country, String property) {
        return countryDataLoader.getCountryProperty(country, property);
    }

    public String[] listAllCountries() {
        return countryDataLoader.listAllCountries();
    }


    public String[] listCountriesWhichStartsWith(String startsWith) {
        return countryDataLoader.listCountriesWhichStartsWith(startsWith);
    }

    public String[] listCountriesWhichEndsWith(String endsWith) {
        return countryDataLoader.listCountriesWhichEndsWith(endsWith);
    }

    public String[] listCountriesWhichContainsString(String contains) {
        return countryDataLoader.listCountriesWhichContainsString(contains);
    }

    public boolean isValidCountry(String country) {
        return countryDataLoader.isValidCountry(country);
    }

    /** TODO 7: Uncomment the three methods below namely:
     *          1. getCapital(String)
     *          2. getNationalAnimal(String)
     *          3. getNationalFlower(String)
     *          so that your code performs correctly.
     *          These is to be done manually, without using the IDE's auto-complete
     *          feature or by Amazon Q developer .
     *          These methods are used by the RuleBasedEngine class to get the
     *          capital, national animal and national flower of a country.
     *          The methods call the getCountryProperty method of the CountryLoader class
     *          with the appropriate property name as indicated by the method name
     *          and return the result.
     */

    public String getCapital(String country) {
        return countryDataLoader.getCountryProperty(country, "capital");
    }

    public String getNationalAnimal(String country) {
        return countryDataLoader.getCountryProperty(country, "nationalAnimal");
    }

    public String getNationalFlower(String country) {
        return countryDataLoader.getCountryProperty(country, "nationalFlower");
    }

}
