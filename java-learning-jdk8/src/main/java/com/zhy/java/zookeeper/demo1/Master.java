package com.zhy.java.zookeeper.demo1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * zookeeper Master
 * 同步调用
 * @author yang.zhang3
 * @create 2017/12/11
 */
public class Master implements Watcher {
    ZooKeeper zk;
    String hostPort;
    Random random = new Random();
    String serverId = Integer.toHexString(random.nextInt());
    boolean isLeader = false;
    public Master(String hostPort) {
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

    boolean checkMaster() throws KeeperException, InterruptedException {
        while (true){
            Stat stat = new Stat();
            byte data[];
            try {
                data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                return false;
            } catch (KeeperException.ConnectionLossException e) {
            }
        }
    }

    void runForMaster() throws InterruptedException, KeeperException {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            }catch (KeeperException.NodeExistsException e){
                isLeader = false;
                break;
            }catch (KeeperException.ConnectionLossException e){

            }
            if(checkMaster()){
                break;
            }
        }
    }



    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        Master master = new Master("192.168.217.42:2181,192.168.217.42:2182,192.168.217.42:2183");
        master.startZK();
        master.runForMaster();
        if(master.isLeader){
            System.out.println("I'm the leader!");
            Thread.sleep(60000);
        }else{
            System.out.println("Someone else is the leader");
        }
        master.stopZK();
    }
}
