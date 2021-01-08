package com.chess.server;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	
	private static Config conf;
	
	public static Config getConfig() {
		if(conf == null)
			conf = new Config();
		return conf;
	}
	
	private Properties prop;
	
	public Config() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}
}
