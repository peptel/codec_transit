package net.peptel.transit.fraud;

import java.util.List;

import net.peptel.transit.fraud.data.FraudTransaction;

public interface FraudVerificationList {
	void addEntry(FraudTransaction transaction);
	boolean exist(FraudTransaction transaction);
	List<FraudTransaction> list();
}
