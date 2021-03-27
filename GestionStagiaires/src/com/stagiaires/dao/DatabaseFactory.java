package com.stagiaires.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseFactory {
	private static final String PROPERTY_FILE = "com/stagiaires/config/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER_NAME = "userName";
	private static final String PROPERTY_PASSWORD = "password";

	public static Database createDatabase() throws IOException {
		Properties properties = new Properties();
		String url;
		String driver;
		String userName;
		String password;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(PROPERTY_FILE);
		if (fichierProperties == null) {
			throw new DaoConfigurationException("Le fichier properties " + PROPERTY_FILE + " est introuvable.");
		}
		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			userName = properties.getProperty(PROPERTY_USER_NAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (IOException e) {
			throw new DaoConfigurationException("Impossible de charger le fichier properties " + PROPERTY_FILE, e);
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DaoConfigurationException("Le driver est introuvable dans le classpath.", e);
		}
		return new Database(url, userName, password);
	}
}
