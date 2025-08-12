package com.ajsd.chatbot.service;

import com.ajsd.chatbot.config.CountryDataLoader;
import com.ajsd.chatbot.model.ConversationContext;
import org.springframework.stereotype.Component;

/*
    This class implements the Rule based engine to
    answer questions about countries and communicate
    with the user. The rules are simple.

    1. Chatbot says what do you want to learn.

    2. If user says something like "I want to learn about countries", or "I do not know"
       or "teach me how to use this" then the chatbot will say
       "I can teach about countries, their capitals, national animals and national flowers.
       What do you want to learn about?". If the chatbot does not understand the question,
       it will say "I do not understand. Please ask me something else. I can only teach about
       countries, their capitals, national animals and national flowers. Shall we do that?"

    3. After the user confirms that they want to learn about countries, the chatbot will say
       "Great! I can teach you about countries, their capitals, national animals and national flowers.
       What country do you want to learn about?".

     4.  If the user does not specify a country, the chatbot will say "I do not understand. Please
          list a give me a country to talk about. What do you want to :
           A. give the name of a country in full
           B. give the beginning of the name of a country
           C. give the ending of the name of a country
           D: give me part of the name of a country
           E: Have me list all the countries which are available

    5. If the user is wrong in listing a giving the correct option
       for Step 4, then the chatbot will say "I do not understand. Please list a give me a country to talk about.
       What do you want to :
           A. give the name of a country in full
           B. give the beginning of the name of a country
           C. give the ending of the name of a country
           D: give me part of the name of a country
           E: Have me list all the countries which are available"

    6. When given the name of a country, the chatbot will say "Great! I know about <country name>.
       What do you want to learn about <country name>?
           A. learn about the capital
           B. learn about the national animal
           C. learn about the national flower
           D. learn about all of the above
           E. Choose another country
       ".

    7. If the user is wrong in listing a giving the correct option
       for options given in 6, the chatbot will say "I do not understand. Please choose one of the following:
           A. learn about the capital
           B. learn about the national animal
           C. learn about the national flower
           D. learn about all of the above
           E. Choose another country
       "

    8. If the user wants to learn about one of the valid options under 6 or 7 - A to D, then
       the chatbot replies with the correct option value,  for example
       "The capital of <country name> is <capital name>".
       and then says. "What else do you want to learn about <country name>?" and lists the options
       from A to E of option 6.  If the user wants to learn about another country, the chatbot will
       say "What country do you want to learn about?" and then go back to step 4.

 */



@Component
public class RuleBasedEngine {

    private final ChatbotService chatbotService;
    private static final String COUNTRY_OPTIONS_STRING = "What do you want to learn about it?\n" +
            "A. learn about the capital\n" +
            "B. learn about the national animal\n" +
            "C. learn about the national flower\n" +
            "D. learn about all of the above\n" +
            "E. Choose another country";

    public RuleBasedEngine(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    public String processUserInput(String userInput, ConversationContext context) {
        switch (context.getCurrentStep()) {
            case "ASK_INTENT":
                return handleAskIntent(userInput, context);
            case "SELECT_COUNTRY":
                return handleSelectCountry(userInput, context);
            case "CHOOSE_OPTION":
                return handleChooseOption(userInput, context);
            default:
                context.setCurrentStep("ASK_INTENT");
                return "I do not understand. \nLet's start again. \nWhat country do you want to learn about?";
        }
    }

    private String handleAskIntent(String userInput, ConversationContext context) {
        if (userInput.toLowerCase().contains("countries")
                ||
                userInput.toLowerCase().contains("country")
                ||
                userInput.toLowerCase().contains("teach")
        ) {
            context.setCurrentStep("SELECT_COUNTRY");
            return "I can teach you about countries, their capitals, national animals, and national flowers.\n What country do you want to learn about?";
        }
        return "I do not understand. \nPlease ask me something else. \nI can teach about countries, their capitals, national animals, and national flowers.";
    }

    private String handleSelectCountry(String countryNameInput, ConversationContext context) {
        boolean countryExists = chatbotService.isValidCountry(countryNameInput);
        if (countryExists) {
            context.setSelectedCountry(countryNameInput);
            context.setCurrentStep("CHOOSE_OPTION");
            return "Great! I know about that country.\n" + COUNTRY_OPTIONS_STRING;
        }
        return "I do not understand. \nPlease provide a valid country.";
    }

    private String handleChooseOption(String userInput, ConversationContext context) {
        String country = context.getSelectedCountry();
        String returnValue = "";
        
        /** TODO 8: You will create the logic that handles the user's input during the chatbot's
         *          'CHOOSE_OPTION' step. The method should check the user's input against
         *          the following options:
         *              A: Return the capital of the currently selected country,
         *              followed by a question asking what else the user wants to learn about
         *              the same country.
         *              B: Return the national animal of the currently selected country,
         *              followed by a similar question.
         *              C: Return the national flower of the currently selected country,
         *                followed by the same question.
         *              D: Return all available information about the currently selected country
         *                  (capital, national animal, and national flower),
         *                  and then ask what else the user wants to learn.
         *              E: Reset the step to 'SELECT_COUNTRY'
         *                and prompt the user to select another country.
         *              For invalid inputs, reset the step to 'SELECT_COUNTRY'
         *              and return a message asking the user to choose a valid country.
         *              Use a COUNTRY_OPTIONS_STRING variable to list available
         *              options whenever asking the user what they want to learn next.
         *              The method should also update the current step in the ConversationContext
         *              to reflect the next step in the conversation.
         *              The method should return the appropriate response string.
         *              The method should break the line after each punctuation with a '\n'.
         *              Use if-else if- else statements to handle different cases.
         **/
        if (userInput.equalsIgnoreCase("A")) {
            returnValue = "The capital of " + country + " is " + chatbotService.getCapital(country) + ".\n" + COUNTRY_OPTIONS_STRING;
        } else if (userInput.equalsIgnoreCase("B")) {
            returnValue = "The national animal of " + country + " is " + chatbotService.getNationalAnimal(country) + ".\n" + COUNTRY_OPTIONS_STRING;
        } else if (userInput.equalsIgnoreCase("C")) {
            returnValue = "The national flower of " + country + " is " + chatbotService.getNationalFlower(country) + ".\n" + COUNTRY_OPTIONS_STRING;
        } else if (userInput.equalsIgnoreCase("D")) {
            returnValue = "The capital of " + country + " is " + chatbotService.getCapital(country) + ".\n" +
                    "The national animal of " + country + " is " + chatbotService.getNationalAnimal(country) + ".\n" +
                    "The national flower of " + country + " is " + chatbotService.getNationalFlower(country) + ".\n" +
                    COUNTRY_OPTIONS_STRING;
        } else if (userInput.equalsIgnoreCase("E")) {
            context.setCurrentStep("SELECT_COUNTRY");
            returnValue = "What country do you want to learn about?";
        } else {
            context.setCurrentStep("SELECT_COUNTRY");
            returnValue = "I do not understand. \nPlease choose one of the following:\n" + COUNTRY_OPTIONS_STRING;
        }

         return  returnValue;
    }


}