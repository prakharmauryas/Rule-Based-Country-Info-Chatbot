package com.ajsd.chatbot.model;

import java.util.ArrayList;
import java.util.List;

public class ConversationContext {
    private String currentStep; // e.g., "ASK_INTENT", "SELECT_COUNTRY", "CHOOSE_OPTION"
    private String selectedCountry; // Country currently being discussed
    private List<String> availableOptions; // Options available for the current step

    public ConversationContext() {
        this.currentStep = "ASK_INTENT";
        this.availableOptions = new ArrayList<>();
    }

    // Getters and Setters
    public String getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<String> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(List<String> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public void addOption(String option) {
        this.availableOptions.add(option);
    }

    public void clearOptions() {
        this.availableOptions.clear();
    }

    public void clear() {
        this.currentStep = "ASK_INTENT";
        this.selectedCountry = null;
        this.availableOptions.clear();
    }
}