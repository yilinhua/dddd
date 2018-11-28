package cn.smbms.dao;

public class MyThread extends Thread{

	@Override
	public void run() {
		ConfigManager manager = ConfigManager.getInstance();
		System.out.println(manager);
	}


}
