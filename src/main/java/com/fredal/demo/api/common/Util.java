package com.fredal.demo.api.common;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.naming.pojo.Cluster;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.healthcheck.AbstractHealthChecker;
import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;

import java.util.*;

/**
 * @author eileen.cf@bytedance.com
 * @version 1.0
 * @date 2023/9/6 10:03
 */
public class Util {
    public static Properties initProperties(String serverAddr, String userName, String pwd, String namespaceId) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.USERNAME, userName);
        properties.put(PropertyKeyConst.PASSWORD, pwd);
        properties.put(PropertyKeyConst.CONTEXT_PATH, "/nacos");
        properties.put(PropertyKeyConst.NAMESPACE, namespaceId);
        return properties;
    }

    public static Instance builInstance(String serviceName, String clusterName, Boolean ephemeral, String ip, Integer port) {
        Instance instance = new Instance();
        if (null == port) {
            port = 8888;
        }
        instance.setIp(ip);
        instance.setPort(port);
        instance.setHealthy(true);
        instance.setWeight(2.0);
        if (ephemeral == null) {
            ephemeral = true;
        }
        instance.setEphemeral(ephemeral);
        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("tag", "nacos.test");
        instanceMeta.put("version", "1.0.0");
        instance.setMetadata(instanceMeta);
        instance.setServiceName(serviceName);
        Cluster cluster = new Cluster();
        cluster.setName(clusterName);
        AbstractHealthChecker healthChecker = new AbstractHealthChecker(Http.TYPE) {
            @Override
            public AbstractHealthChecker clone() throws CloneNotSupportedException {
                return new Http();
            }
        };
        cluster.setHealthChecker(healthChecker);
        Map<String, String> clusterMeta = new HashMap<>();
        clusterMeta.put("key", "test");
        cluster.setMetadata(clusterMeta);
        instance.setClusterName(clusterName);
        return instance;
    }
}
