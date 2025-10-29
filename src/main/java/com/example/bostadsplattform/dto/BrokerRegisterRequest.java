package com.example.bostadsplattform.dto;

public class BrokerRegisterRequest extends RegisterRequest {
    private String companyName;
    private boolean subscriptionActive;

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public boolean isSubscriptionActive() { return subscriptionActive; }
    public void setSubscriptionActive(boolean subscriptionActive) { this.subscriptionActive = subscriptionActive; }
}
