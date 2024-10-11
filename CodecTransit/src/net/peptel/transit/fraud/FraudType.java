package net.peptel.transit.fraud;

public enum FraudType {
	NotFraud, WhiteList, BlackList, BlackListVerification, AnotherContact, PhoneChange, RoleChange;
	
	public boolean isFraud() {
		if (ordinal() > 1) return true;
		return false;
	}
}
