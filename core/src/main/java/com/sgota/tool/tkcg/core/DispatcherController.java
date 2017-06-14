package com.sgota.tool.tkcg.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sgota.tool.tkcg.exception.GeneratorException;

/**
 * 前置控制器
 *
 * @author tiankuo
 */
public class DispatcherController {

    /**
     * Provider缓存.
     */
    private ConcurrentHashMap<String, DataProvider> providerCache = new ConcurrentHashMap<>(16);
    /**
     * 单例变量.
     */
    private static DispatcherController instance = new DispatcherController();

    /**
     * The Lock.
     */
    private Lock lock = new ReentrantLock();

    /**
     * 构造方法.
     */
    private DispatcherController(){
        this.reLoadService(DispatcherController.class.getClassLoader());
    }

    /**
     * 重新加载服务.
     *
     * @param cl 类加载器
     */
    public void reLoadService(ClassLoader cl) {
        try {
            lock.lock();
            ServiceLoader<DataProvider> providers;
            if (cl == null) {
                providers = ServiceLoader.load(DataProvider.class);
            } else {
                providers = ServiceLoader.load(DataProvider.class, cl);
            }
            providerCache.clear();
            providers.forEach(provider -> {
                String id = provider.getId();
                DataProvider oldProvider = providerCache.get(id);
                if (oldProvider != null) {
                    throw new GeneratorException("provider出现重复id:" + id + " 旧类名:" + oldProvider.getClass().getName()
                                                 + " 新类名:" + provider.getClass().getName());
                } else {
                    providerCache.put(provider.getId(), provider);
                }
            });
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取实例.
     *
     * @return the instance
     */
    public static DispatcherController getInstance() {
        return instance;
    }

    /**
     * 获取指定id的DataProvider.
     *
     * @param id id
     * @return the data provider
     */
    public DataProvider getDataProvider(String id) {
        return providerCache.get(id);
    }

    /**
     * 分配生成任务.
     * 
     * @param config config
     */
    public void dispatch(GeneratorConfig config) {
        // 获取providerConfig
        Map<String, Object> providerConfig = config.getProviderConfig();
        // 获取指定数据提供者
        String providerId = (String) providerConfig.get("id");
        // 获取dataProvider
        DataProvider dataProvider = this.getDataProvider(providerId);
        // 加载数据
        Map<String, Map<String, Object>> dataMap = dataProvider.loadData(config, providerConfig);
        // 创建生成器
        Generator generator = new Generator();
        generator.init(config);
        // 执行生成
        if (config.isGroup()) {
            generator.executeGroup(dataMap, config.getGlobal(), config.getKeys());
        } else {
            List<Map<String, Object>> dataList = new ArrayList<>(dataMap.values());
            Map<String, Object> data = new HashMap<>(1);
            data.put("dataList", dataList);
            generator.execute(data, config.getGlobal());
        }
    }

}
