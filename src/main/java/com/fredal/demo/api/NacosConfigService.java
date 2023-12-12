package com.fredal.demo.api;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

/**
 * @author eileen.cf@bytedance.com
 * @version 1.0
 * @date 2023/9/6 20:24
 */
public interface NacosConfigService {
    /**
     * 发布配置
     * @param dataId
     * @param group
     * @param content
     * @param type
     * @see com.alibaba.nacos.api.config.ConfigType
     * @return
     */
    String publishConfig(String dataId, String group, String content, String type);

    String getConfig(String dataId, String group) throws NacosException;

    boolean removeConfig(String dataId, String group) throws NacosException;

    void addListener(String dataId, String group,Listener listener) throws NacosException;
    void removeListener(String dataId, String group,Listener listener) throws NacosException;

    void batchCreateConfigs(Integer num, String group) throws NacosException;

}
