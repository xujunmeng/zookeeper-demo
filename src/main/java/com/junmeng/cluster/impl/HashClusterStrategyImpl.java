package com.junmeng.cluster.impl;

import com.junmeng.cluster.ClusterStrategy;
import com.junmeng.helper.IPHelper;
import com.junmeng.model.ProviderService;

import java.util.List;

/**
 * 软负载哈希算法实现
 * Created by jgsoft on 2020/3/22.
 */
public class HashClusterStrategyImpl implements ClusterStrategy {
    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        //获取调用方ip
        String localIP = IPHelper.localIp();
        //获取源地址对应的hashcode
        int hashCode = localIP.hashCode();
        //获取服务列表大小
        int size = providerServices.size();

        return providerServices.get(hashCode % size);
    }
}
