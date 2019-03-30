package com.fdy.util;

import java.io.IOException;
import java.util.Properties;
/**获取配置文件的键值对，以及配置一些常量
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class Config {

	public static final int COMPANY_ID = 1;
	public static final int PUBLIC_ID = 0;
	public static final int TASK_STATUS_UNDONE = 0;
	public static final int TASK_STATUS_DONE = 1;
	
	
	
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
