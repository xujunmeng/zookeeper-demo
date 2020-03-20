package com.junmeng.register;

import avro.shaded.com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.junmeng.consumer.IRegisterCenter4Invoker;
import com.junmeng.helper.PropertyConfigeHelper;
import com.junmeng.model.InvokerService;
import com.junmeng.model.ProviderService;
import com.junmeng.provider.IRegisterCenter4Provider;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.commons.collections.CollectionUtils;

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
    private static String ZK_SERVICE = PropertyConfigeHelper.getZkService();

    /**
     * 从配置文件中获取ZK连接超时时间配置
     */
    private static int ZK_CONNECTION_TIME_OUT = PropertyConfigeHelper.getZkConnectionTimeout();

    /**
     * 从配置文件中获取ZK会话超时时间配置
     */
    private static int ZK_SESSION_TIME_OUT = PropertyConfigeHelper.getZkSessionTimeout();

    /**
     * 组装ZK根路径 /APPKEY 路径
     */
    private static String ROOT_PATH = "/config_register" + PropertyConfigeHelper.getAppName();

    private static String PROVIDER_TYPE = "/provider";

    private static String INVOKER_TYPE = "/consumer";

    private static volatile ZkClient zkClient = null;

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
        if (CollectionUtils.isEmpty(serviceMetaData)) {
            return;
        }

        //连接ZK，注册服务
        synchronized (RegisterCenter.class) {
            for (ProviderService provider : serviceMetaData) {

                String serviceItfKey = provider.getServiceItf().getName();

                List<ProviderService> providers = providerServiceMap.get(serviceItfKey);
                if (CollectionUtils.isEmpty(providers)) {
                    providers = Lists.newArrayList();
                }
                providers.add(provider);

                providerServiceMap.put(serviceItfKey, providers);
            }

            if (zkClient == null) {
                zkClient = new ZkClient(ZK_SERVICE, ZK_SESSION_TIME_OUT, ZK_CONNECTION_TIME_OUT, new SerializableSerializer());
            }
            //创建 zk 命名空间，当前部署应用App命名空间
            String APP_KEY = serviceMetaData.get(0).getAppKey();
            String ZK_PATH = ROOT_PATH + "/" + APP_KEY;

            boolean exist = zkClient.exists(ZK_PATH);
            if (!exist) {
                zkClient.createPersistent(ZK_PATH, true);
            }

            //创建服务提供者节点
            exist = zkClient.exists(R)


        }
    }

    @Override
    public Map<String, List<ProviderService>> getProviderServiceMap() {
        return null;
    }
}
