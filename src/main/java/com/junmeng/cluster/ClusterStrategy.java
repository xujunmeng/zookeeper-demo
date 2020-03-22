package com.junmeng.cluster;

import com.junmeng.model.ProviderService;

import java.util.List;

/**
 * Created by jgsoft on 2020/3/22.
 */
public interface ClusterStrategy {

    /**
     * 负载策略算法
     */
    ProviderService select(List<ProviderService> providerServices);

}
