package com.junmeng.consumer;

import com.junmeng.provider.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * 服务注册中心服务消费方
 * @author james
 * @date 2020/3/9
 */
public interface IRegisterCenter4Invoker {

    /**
     * 消费端初始化服务提供者信息本地缓存
     */
    void initProviderMap();

    /**
     * 消费端获取服务提供者信息
     */
    public Map<String, List<ProviderService>> getServiceMetaDataMap4Consume();

    /**
     * 消费端将消息者信息注册到ZK对应的节点下
     */
    void registerInvoker(InvokerService invoker);
}
