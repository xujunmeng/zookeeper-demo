package com.junmeng.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author james
 * @date 2020/3/9
 */
public class PropertyConfigeHelper {

    private static final Logger logger = LoggerFactory.getLogger(PropertyConfigeHelper.class);

    private static final String PROPERTY_CLASSPATH = "/ares_remoting.properties";
    private static final Properties properties = new Properties();

    //ZK服务地址
    private static String zkService = "";

    //ZK session超时时间
    private static int zkSessionTimeout;

    //ZK connection超时时间
    private static int zkConnectionTimout;

    //序列化算法类型
    private static SerializeType serializeType;

    //每个服务端提供者的Netty的连接数
    private static int channelConnectionSize;



}
