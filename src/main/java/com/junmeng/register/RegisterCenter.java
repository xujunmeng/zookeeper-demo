package com.junmeng.register;

import com.google.common.collect.Maps;
import com.junmeng.consumer.IRegisterCenter4Invoker;
import com.junmeng.consumer.InvokerService;
import com.junmeng.provider.IRegisterCenter4Provider;
import com.junmeng.provider.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * 注册中心实现
 * @author james
 * @date 2020/3/9
 */
public class RegisterCenter implements IRegisterCenter4Invoker, IRegisterCenter4Provider {

    private static RegisterCenter registerCenter = new RegisterCenter();

    /**
     * 服务提供者列表，key:服务提供者接口  value:服务提供者 服务方法列表
     */
    private static final Map<String, List<ProviderService>> providerServiceMap = Maps.newConcurrentMap();

    /**
     * 服务端ZK服务元信息，选择服务(第一次直接从ZK拉取，后续由ZK的监听机制主动更新)
     */
    private  static final Map<String, List<ProviderService>> serviceMetaDataMap4Consume = Maps.newConcurrentMap();

    /**
     * 从配置文件中获取ZK的服务地址列表
     */
    private static String ZK_SERVICE;

    @Override
    public void initProviderMap() {

    }

    @Override
    public Map<String, List<ProviderService>> getServiceMetaDataMap4Consume() {
        return null;
    }

    @Override
    public void registerInvoker(InvokerService invoker) {

    }

    @Override
    public void registerProvider(List<ProviderService> serviceMetaData) {

    }

    @Override
    public Map<String, List<ProviderService>> getProviderServiceMap() {
        return null;
    }
}
