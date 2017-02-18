package dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

abstract class DbManager {

	private static String dbPassword;
	private static String dbUrl;
	private static String dbUsername;
	
	protected String query;
	protected String response;
	
	public DbManager() {
		loadDBProperties();
	}

	private static void loadDriver(final String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException("Cannot load the driver class", e);
		}
	}

	private static Properties getPropertiesQuietly() {
		final Properties properties = new Properties();
		try {
			properties.load(DbManager.class.getClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (final IOException e) {
			throw new RuntimeException("Cannot load db properties file", e);
		}
		return properties;
	}

	
	protected static void loadDBProperties() {
		final Properties properties = getPropertiesQuietly();
		final String dbDriver = properties.getProperty("db.driver");
		dbUrl = properties.getProperty("db.url");
		dbUsername = properties.getProperty("db.username");
		dbPassword = properties.getProperty("db.password");
		loadDriver(dbDriver);
	}
	
	protected Connection createConnection() {
		try {
			return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (final SQLException e) {
			throw new RuntimeException("Cannot create the connection", e);
		}
	}

	protected void closeConnection(Connection mConnection) {
		try {
			mConnection.close();
//			DriverManager.getConnection(dbUrl, dbUsername, dbPassword).close();
		} catch (final SQLException e) {
			throw new RuntimeException("Cannot close the connection", e);
		}
	}
	
}
