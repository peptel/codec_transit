package net.peptel.transit.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import net.peptel.transit.data.CodecProperty;

/**
 * @author Andy Peptyuk
 */
public class InitializationProperties {
	private CodecProperty[] codecProperties;
	private static InitializationProperties initializationProperties;
	
	private InitializationProperties(String INIT_PROPERTIES) {
		BufferedReader br = null;
		try {
			Gson gson = new Gson();
			br = new BufferedReader(new FileReader(INIT_PROPERTIES));
			
			codecProperties = gson.fromJson(br, CodecProperty[].class);
		} catch (JsonSyntaxException e) {
			Log.log.error("Initialization codec error.", e);
		} catch (JsonIOException e) {
			Log.log.error("Initialization codec error.", e);
		} catch (FileNotFoundException e) {
			Log.log.error("Initialization codec error.", e);
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				Log.log.error(e);
			}
		}
	}
	
	public static CodecProperty[] initCodecProperties(String INIT_PROPERTIES) {
		if (initializationProperties == null) initializationProperties = new InitializationProperties(INIT_PROPERTIES);
		return initializationProperties.codecProperties;
	}
	
}
