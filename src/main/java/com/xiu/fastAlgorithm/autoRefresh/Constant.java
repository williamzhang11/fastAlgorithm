package com.xiu.fastAlgorithm.autoRefresh;

import java.util.concurrent.ConcurrentHashMap;

public class Constant {


	public static ConcurrentHashMap<String,String> systemConfigList= new ConcurrentHashMap<String, String>();//配置文件
	
    public static String getSystemKey(String key){
        String value = null;
        try {
            value = SystemConfigService.getValue(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  value;
    }
}
