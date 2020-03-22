package com.junmeng.cluster.impl;

import com.junmeng.cluster.ClusterStrategy;
import com.junmeng.model.ProviderService;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 软负载随机算法实现
 * Created by jgsoft on 2020/3/22.
 */
public class RandomClusterStrategyImpl implements ClusterStrategy {
    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        int MAX_LEN = providerServices.size();
        int index = RandomUtils.nextInt(0, MAX_LEN - 1);
        return providerServices.get(index);
    }
}
