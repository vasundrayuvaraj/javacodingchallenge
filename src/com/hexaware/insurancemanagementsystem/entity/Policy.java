package com.hexaware.insurancemanagementsystem.entity;

public class Policy {
    private int policyId;
    private String policyName;
    private String policyType;
    private double coverageAmount;
    private double premium;

    public Policy() {}

    public Policy(int policyId, String policyName, String policyType, double coverageAmount, double premium) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyType=" + policyType
				+ ", coverageAmount=" + coverageAmount + ", premium=" + premium + "]";
	}

   
    }

