package com.fdy.util;

import java.io.IOException;
import java.util.Properties;
/**��ȡ�����ļ��ļ�ֵ�ԣ��Լ�����һЩ����
 * @author fdy
 * @date 2019��3��27��
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
