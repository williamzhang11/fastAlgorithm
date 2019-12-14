package com.xiu.fastAlgorithm.autoRefresh;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

/*
 * 通过信号量控制
 * 先从数据库中加载配置数据，然后每个隔一段时间自动清空配置，然后通知进行重新加载
 * 并且清空数据直到加载好数据之前，都不允许访问，以避免访问到空数据
 * 
 * 后续改进空间：可利用多重缓存，避免刷新数据时，长时间被锁住
 */
public abstract class SystemConfigService {
	
    private  static Semaphore semp1 = new Semaphore(1);//加载控制信号量
    private  static Semaphore semp2 = new Semaphore(0);//清空控制信号量
    private  static Semaphore semp3 = new Semaphore(1);//避免刷新时获取数据信号量
    private  static volatile Boolean isRefresh = false;


    public abstract List<SystemConfig> loadDateBase();

    public void run() throws InterruptedException {


        while (true) {

            semp1.acquire();
            List<SystemConfig> systemConfigs = loadDateBase();
            Iterator<SystemConfig> iter = systemConfigs.iterator();
            while (iter.hasNext()) {
                SystemConfig systemConfig = iter.next();
                if(StringUtils.isNotBlank(systemConfig.getConfigProp()) && StringUtils.isNotBlank(systemConfig.getConfigValue()))
                    Constant.systemConfigList.put(systemConfig.getConfigProp(), systemConfig.getConfigValue());
            }
            if(isRefresh){
                semp3.release();
                isRefresh = false;
            }

            semp2.release();

        }

    }
    /**
     * 定时刷新
     * @param timeoutMinutes
     * @throws InterruptedException
     */
    public void refresh(long timeoutMinutes) throws InterruptedException {

        System.out.println("进入刷新");
        if(timeoutMinutes <= 0){
            timeoutMinutes = 1;
        }

        while (true) {
            semp2.acquire();
            TimeUnit.MINUTES.sleep(timeoutMinutes);
            System.out.println("刷新开始");
            isRefresh = true;
            if(isRefresh){
                semp3.acquire();
            }
            Constant.systemConfigList.clear();
            semp1.release();
            System.out.println("刷新结束");
        }
    }

    public  static String getValue(String key) throws InterruptedException {

        semp3.acquire();
        String value = Constant.systemConfigList.get(key);
        semp3.release();
        return value;
    }
}
