package com.junmeng.helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

/**
 * @author james
 * @date 2020/3/9
 */
public class IPHelper {

    private static final Logger logger = LoggerFactory.getLogger(IPHelper.class);

    private static String hostIp = StringUtils.EMPTY;

    /**
     * 获取本机IP
     * 通过获取系统所有的networkInterface网络接口，然后遍历每个网络下的InterfaceAddress组
     * 获得符合 InetAddress instanceof Inet4Address 条件的一个IpV4地址
     */
    public static String localIp() {
        return hostIp;
    }

    public static String getRealIp() {

        String localip = null;//本地IP，如果没有配置外网IP则返回它

        String netip = null;//外网IP

        try {
            Enumeration<NetworkInterface> netInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }

            if (netip != null && !"".equals(netip)) {
                return netip;
            } else {
                return localip;
            }
        } catch (SocketException e) {
            logger.warn("获取本机Ip失败:异常信息:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    static {

        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        if (StringUtils.equals(Ip.getHostAddress(), "127.0.0.1")) {
                            continue;
                        }
                        ip = Ip.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            logger.warn("获取本机Ip失败:异常信息:" + e.getMessage());
            throw new RuntimeException(e);
        }
        hostIp = ip;
    }

    /**
     * 获取主机第一个有效ip
     * 如果没有效ip，返回空串
     */
    public static String getHostFirstIp() {
        return hostIp;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(localIp());
        System.out.println(getRealIp());
        System.out.println(getHostFirstIp());
    }



}
