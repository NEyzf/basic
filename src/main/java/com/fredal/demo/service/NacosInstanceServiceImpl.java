package com.fredal.demo.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fredal.demo.api.NacosInstanceService;
import com.fredal.demo.api.common.Util;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.fredal.demo.api.common.Const.*;

/**
 * @author eileen.cf@bytedance.com
 * @version 1.0
 * @date 2023/9/6 20:48
 */
@Service
@Component
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "nacos")
public class NacosInstanceServiceImpl implements NacosInstanceService, InitializingBean {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Value("${spring.cloud.nacos.discovery.username}")
    private String userName;
    @Value("${spring.cloud.nacos.discovery.password}")
    private String pwd;
    @Value("${nacos.instance.num}")
    private int num;
    @Value("${spring.cloud.nacos.discovery.ephemeral}")
    private Boolean ephemeral;
    @Value("${spring.cloud.nacos.discovery.namespace}")
    private String namespaceId;
    @NacosInjected
    NamingService namingService;

    @Override
    public List<Instance> registerInstances(int num, Boolean ephemeral, String ip, Integer port) throws NacosException {
        List<Instance> list = new ArrayList<>();
        if (num < 0) {
            num = 1;
        }
        try {
            for (int i = 0; i < num; i++) {
                String serviceName = ServicePrefix.concat(".").concat(String.valueOf(ephemeral)).concat(".").concat(String.valueOf(i));
                namingService.registerInstance(serviceName, Util.builInstance(serviceName, DefaultCluster, ephemeral, ip, port));
                list.addAll(namingService.getAllInstances(serviceName));
            }
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    @Override
    public void registerInstance(String serviceName, Boolean ephemeral, String ip, Integer port) throws NacosException {
        if (StringUtils.isBlank(serviceName)) {
            serviceName = ServicePrefix.concat(RandomStringUtils.randomAlphanumeric(10));
        }
        if (null == ephemeral) {
            ephemeral = true;
        }
        namingService.registerInstance(serviceName, Util.builInstance(serviceName, DefaultCluster, ephemeral, ip, port));
    }

    @Override
    public void deregisterInstance(String serviceName, String ip, int port) throws NacosException {
        namingService.deregisterInstance(serviceName, ip, port);
    }

    @Override
    public List<Instance> getAllInstances(String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @Override
    public void subscribe(List<Instance> instances) throws NacosException {
        try {
            for (Instance instance : instances) {
                namingService.subscribe(instance.getServiceName(), event -> {
                    if (event instanceof NamingEvent) {
                        System.out.println(((NamingEvent) event).getServiceName());
                        System.out.println(((NamingEvent) event).getInstances());
                    }
                });
            }
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unsubscribe(List<Instance> instances) throws NacosException {
        try {
            for (Instance instance : instances) {
                namingService.unsubscribe(instance.getServiceName(), event -> {
                });
            }
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getServices(int pageNo, int pageSize, String groupName) throws NacosException {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 1000;
        }
        if (StringUtils.isBlank(groupName)) {
            groupName = DefaultGroup;
        }
        return namingService.getServicesOfServer(pageNo, pageSize, groupName).getData();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = Util.initProperties(serverAddr, userName, pwd, namespaceId);
        String  ip = InetAddress.getLocalHost().getHostAddress();
        namingService = NacosFactory.createNamingService(properties);
        List<Instance> instances = registerInstances(num, ephemeral, ip, null);
        subscribe(instances);
    }
}
