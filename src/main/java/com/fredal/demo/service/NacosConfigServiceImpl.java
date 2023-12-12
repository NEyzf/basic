package com.fredal.demo.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fredal.demo.api.NacosConfigService;
import com.fredal.demo.api.common.Util;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Properties;
import java.util.concurrent.Executor;

import static com.fredal.demo.api.common.Const.ConfigPrefix;
import static com.fredal.demo.api.common.Const.DefaultGroup;

/**
 * @author eileen.cf@bytedance.com
 * @version 1.0
 * @date 2023/9/6 20:48
 */
@Service
@Component
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "nacos")
public class NacosConfigServiceImpl implements NacosConfigService, InitializingBean {
    @NacosInjected
    com.alibaba.nacos.api.config.ConfigService configService;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Value("${spring.cloud.nacos.discovery.username}")
    private String userName;
    @Value("${spring.cloud.nacos.discovery.password}")
    private String pwd;
    @Value("${nacos.config.num}")
    private int num;
    @Value("${spring.cloud.nacos.discovery.namespace}")
    private String namespaceId;

    @Override
    public String publishConfig(String dataId, String group, String content, String type) {
        if (StringUtils.isEmpty(content)) {
            content = "user.name=" + userName + "\n" +
                    "user.age=18";
        }
        if (StringUtils.isEmpty(type)) {
            type = ConfigType.PROPERTIES.getType();
        }
        if (StringUtils.isEmpty(group)) {
            group = DefaultGroup;
        }
        if (StringUtils.isEmpty(dataId)) {
            dataId = ConfigPrefix.concat("-").concat(RandomStringUtils.randomAlphanumeric(5));
        }
        try {
            configService.publishConfig(dataId, group, content, type);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        return dataId;
    }

    @Override
    public String getConfig(String dataId, String group) throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DefaultGroup;
        }
        if (StringUtils.isEmpty(dataId)) {
            dataId = userName.concat(".properties");
        }
        try {
            return configService.getConfig(dataId, group, 5000);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeConfig(String dataId, String group) throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DefaultGroup;
        }
        if (StringUtils.isEmpty(dataId)) {
            dataId = userName.concat(".properties");
        }
        try {
            return configService.removeConfig(dataId, group);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addListener(String dataId, String group, Listener listener) throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DefaultGroup;
        }
        if (StringUtils.isEmpty(dataId)) {
            dataId = userName.concat(".properties");
        }
        configService.addListener(dataId, group, listener);
    }

    @Override
    public void removeListener(String dataId, String group, Listener listener) throws NacosException {
        if (StringUtils.isEmpty(group)) {
            group = DefaultGroup;
        }
        if (StringUtils.isEmpty(dataId)) {
            dataId = userName.concat(".properties");
        }
        configService.removeListener(dataId, group, listener);
    }

    @Override
    public void batchCreateConfigs(Integer num, String group) throws NacosException {
        if (num < 0) {
            num = 1;
        }
        try {
            for (int i = 0; i < num; i++) {
                String dataId = ConfigPrefix.concat(".").concat(String.valueOf(i));
                publishConfig(dataId, group, "", "");
                Listener listener = new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                    }
                };
                addListener(dataId, "", listener);
            }
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = Util.initProperties(serverAddr, userName, pwd,namespaceId);
        configService = NacosFactory.createConfigService(properties);
        batchCreateConfigs(num,"");
    }

}
