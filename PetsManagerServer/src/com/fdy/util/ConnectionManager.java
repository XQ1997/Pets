package com.fdy.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdy.exception.DataAccessException;
/** jdbc�������ݿ������
 * @author fdy
 * @date 2019��3��27��
 * @Version
 */
public class ConnectionManager {
	
	private static String DRIVER;
	private static String URL;
	private static String NAME;
	private static String PASSWORD;

	private static BasicDataSource dataSource = new BasicDataSource();
	private static Properties prop = new Properties();
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	static {
		try {
			prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
			
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			NAME = prop.getProperty("jdbc.username","root");
			PASSWORD = prop.getProperty("jdbc.password","rootroot");
		} catch (IOException e) {
			logger.error("���ݿ������쳣");
			throw new DataAccessException("���ݿ������쳣",e);
		}
		
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(NAME);
		dataSource.setPassword(PASSWORD);
		
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(20);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5000);
		
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("���ݿ������쳣");
			throw new DataAccessException("���ݿ������쳣",e);
		}
		return conn;
	}
}
