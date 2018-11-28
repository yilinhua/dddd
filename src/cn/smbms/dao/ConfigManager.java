package cn.smbms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private Properties params=new Properties();
	private ConfigManager(){
		String configFile = "database.properties";
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("调用构造函数");
	}
	
	static class ConfigManagerHelper{
		private static ConfigManager instance = new ConfigManager();
	}
	
	public  static ConfigManager getInstance(){
		/*if(instance==null){
			instance=new ConfigManager();
		}*/
		return ConfigManagerHelper.instance;
	}
	
	public String getValue(String key){
		return (String)params.get(key);
	}
}
