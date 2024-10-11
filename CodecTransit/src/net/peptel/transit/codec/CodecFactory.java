package net.peptel.transit.codec;

import java.util.HashSet;
import java.util.Set;

import net.peptel.transit.dao.DAOManagement;

/**
 * @author Andy Peptyuk
 */
public class CodecFactory {
	private static Set<Codec> codecs = new HashSet<Codec>();
	
	private CodecFactory() {
	}

	public static void startCodec(String className, SendingMode mode, CodecMonitor monitor, DAOManagement dao) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		CodecAdapter codec = (CodecAdapter) Class.forName(className).newInstance();
		codec.setSendingMode(mode);
		codec.setMonitor(monitor);
		codec.setDAO(dao);
		
		if (!codecs.contains(codec)) {
			codecs.add(codec);
			new Thread(codec).start();
		} 
	}

	public static void startCodec(String className, SendingMode notificationMode, int codecType, String codecName, CodecMonitor monitor, DAOManagement dao, ActivityMode activityMode) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		CodecAdapter codec = (CodecAdapter) Class.forName(className).newInstance();
		codec.setSendingMode(notificationMode);
		codec.setMonitor(monitor);
		codec.setCodecType(codecType);
		codec.setCodecName(codecName);
		codec.setDAO(dao);
		codec.setActivityMode(activityMode);
		
		if (!codecs.contains(codec)) {
			codecs.add(codec);
			new Thread(codec).start();
		} 
	}

}
