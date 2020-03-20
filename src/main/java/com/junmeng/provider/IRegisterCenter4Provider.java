package com.junmeng.provider;

import com.junmeng.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * 服务注册中心服务提供方
 * Created by james on 2020/3/9.
 */
public interface IRegisterCenter4Provider {

    /**
     * 服务端将服务提供者信息注册到ZK对应的节点下
     */
    void registerProvider(List<ProviderService> serviceMetaData);

    /**
     * 服务端获取服务提供者信息
     */
    Map<String, List<ProviderService>> getProviderServiceMap();

}
