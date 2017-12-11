package com.zhy.java.zookeeper.demo1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;

/**
 * 监控客户端
 *
 * @author yang.zhang3
 * @create 2017/12/11
 */
public class AdminClient implements Watcher {
    ZooKeeper zk;
    String hostPort;

    public AdminClient(String hostPort) {
        this.hostPort = hostPort;
    }

    void start() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    void listState() throws KeeperException, InterruptedException {
        try{
            Stat stat = new Stat();
            byte masterData[] = zk.getData("/master", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("Master: " + new String(masterData) + "since " + startDate);
        }catch (KeeperException.NoNodeException e){
            System.out.println("No Master");
        }
        System.out.println("Workers:");
        for (String t : zk.getChildren("/assign", false)){
            System.out.println("\t" +t);
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception {
        AdminClient adminClient = new AdminClient("192.168.217.42:2181,192.168.217.42:2182,192.168.217.42:2183");
        adminClient.start();
        adminClient.listState();
    }
}
