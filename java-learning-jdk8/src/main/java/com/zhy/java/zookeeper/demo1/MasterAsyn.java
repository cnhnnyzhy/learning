package com.zhy.java.zookeeper.demo1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * zookeeper Master
 * 异步调用
 * @author yang.zhang3
 * @create 2017/12/11
 */
public class MasterAsyn implements Watcher {
    private static final Logger LOG = LoggerFactory.getLogger(MasterAsyn.class);
    ZooKeeper zk;
    String hostPort;
    Random random = new Random();
    String serverId = Integer.toHexString(random.nextInt());
    boolean isLeader = false;
    public MasterAsyn(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    void stopZK() throws InterruptedException {
        if(zk != null){
            zk.close();
        }
    }

    void checkMaster(){
        zk.getData("/master", false, masterCheckCallback, null);
    }

    AsyncCallback.DataCallback masterCheckCallback = (int rc, String path, Object ctx, byte[] data, Stat stat)->{
        switch (KeeperException.Code.get(rc)){
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case NONODE:
                runForMaster();
                return;
        }
    };

    AsyncCallback.StringCallback masterCreateCallback = (int rc, String path, Object ctx, String name) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case OK:
                isLeader = true;
                break;
            default:
                isLeader = false;
        }
        System.out.println("I'm " + (isLeader ? "" : "node") + "the leader");
    };

    void runForMaster(){
        zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
    }



    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        MasterAsyn master = new MasterAsyn("192.168.217.42:2181,192.168.217.42:2182,192.168.217.42:2183");
        master.startZK();
        master.runForMaster();
        if(master.isLeader){
            System.out.println("I'm the leader!");

        }else{
            System.out.println("Someone else is the leader");
        }
        master.bootstrap();
        Thread.sleep(60000);
        master.stopZK();
    }

    public void bootstrap(){
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }

    private void createParent(String path, byte[] data) {
        zk.create(path, data, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallback, data);
    }

    AsyncCallback.StringCallback createParentCallback = (int rc, String path, Object ctx, String name)->{
        switch (KeeperException.Code.get(rc)){
            case CONNECTIONLOSS:
                createParent(path, (byte[]) ctx);
                break;
            case OK:
                LOG.info("Parent created");
                break;
            case NODEEXISTS:
                LOG.warn("Parent already registered: " + path);
                break;
            default:
                LOG.error("Something went wrong:", KeeperException.create(KeeperException.Code.get(rc), path));
        }
    };
}
