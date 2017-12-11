package com.zhy.java.zookeeper.demo1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * 从节点
 *
 * @author yang.zhang3
 * @create 2017/12/11
 */
public class Worker implements Watcher {
    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);

    ZooKeeper zk;
    String hostPort;
    String serverId = Integer.toHexString(new Random().nextInt());
    String name = "worker-" + serverId;
    String status;
    public Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.info(watchedEvent.toString() + ", " + hostPort);
    }

    void register(){
        zk.create("/workers/" + name, "Idle".getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, createWorkerCallback, null);
    }

    AsyncCallback.StringCallback createWorkerCallback = (int rc, String path, Object ctx, String name)->{
        switch (KeeperException.Code.get(rc)){
            case CONNECTIONLOSS:
                register();
                break;
            case OK:
                LOG.info("Registered successfully:" + serverId);
                break;
            case NODEEXISTS:
                LOG.warn("Already registered: " + serverId);
                break;
            default:
                LOG.error("Something went wrong: " + KeeperException.create(KeeperException.Code.get(rc), path));
        }
    };

    AsyncCallback.StatCallback statusUpdateCallback = (int rc, String path, Object ctx, Stat stat)->{
        switch (KeeperException.Code.get(rc)){
            case CONNECTIONLOSS:
                updateStatus((String)ctx);
                return;
        }
    };

    synchronized private void updateStatus(String status) {
        if(status == this.status){
            zk.setData("/workers/" + name, status.getBytes(), -1, statusUpdateCallback, status);
        }
    }

    public void setStatus(String status){
        this.status = status;
        updateStatus(status);
    }

    public static void main(String[] args) throws Exception{
        Worker worker = new Worker("192.168.217.42:2181,192.168.217.42:2182,192.168.217.42:2183");
        worker.startZK();
        worker.register();
        Thread.sleep(30000);
    }
}
