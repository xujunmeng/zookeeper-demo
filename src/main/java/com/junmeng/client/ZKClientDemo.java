package com.junmeng.client;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author james
 * @date 2020/3/9
 */
public class ZKClientDemo {

    public static void main(String[] args) throws InterruptedException {

        //创建会话
        String zkServers = "127.0.0.1:2181";

        int connectionTimeout = 3000;

        ZkClient zkClient = new ZkClient(zkServers, connectionTimeout);

        String path = "/zk-data";

        //若节点已经存在，则删除
        if (zkClient.exists(path)) {
            zkClient.delete(path);
        }

        //创建持久节点
        zkClient.createPersistent(path);

        //节点写入数据
        zkClient.writeData(path, "test-data-1");

        //节点读取数据
        String data = zkClient.readData(path, true);
        System.out.println(data);

        //注册监听器，监听数据变化
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("handleDataChange, dataPath : " + dataPath + ", data : " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handleDataDeleted, dataPath : " + dataPath);
            }
        });

        //修改数据
        zkClient.writeData(path, "test_data_2");

        //删除节点
        zkClient.delete(path);

        Thread.sleep(Integer.MAX_VALUE);

    }

}
