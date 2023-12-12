package com.fredal.demo.api;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @author eileen.cf@bytedance.com
 * @version 1.0
 * @date 2023/9/6 20:43
 */
public interface NacosInstanceService {
    List<Instance> registerInstances(int num,Boolean ephemeral,String ip,Integer port) throws NacosException;
    void  registerInstance(String serviceName, Boolean ephemeral,String ip,Integer port)throws NacosException;
    void deregisterInstance(String serviceName, String ip, int port) throws NacosException;
    List<Instance> getAllInstances(String serviceName) throws NacosException;
    void subscribe(List<Instance> instances) throws NacosException;
    void unsubscribe(List<Instance> instances) throws NacosException;
    List<String> getServices(int pageNo, int pageSize, String groupName) throws NacosException;

}
