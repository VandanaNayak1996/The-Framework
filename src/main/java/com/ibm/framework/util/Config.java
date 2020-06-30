package main.java.com.ibm.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {
	
	public static String getApiKeyForNLU()  {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("api.key.nlu");
	}
	
	public static String getApiKeyForSpeechToText()  {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("api.key.speechToText");
	}
	
	public static String getAudioFilePath() {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("audio.file.path");
	}
	
	public static String getVideoFilePath() {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("video.file.path");
	}
	
	public static String getSpeechToTextServiceUrl() {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("service.url.speechToText");
	}

	public static String getNluServiceUrl() {
		Properties props = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("resource\\config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("service.url.nlu");
	}

}
