package com.zhy.java.zookeeper.demo1;

import org.apache.zookeeper.*;

import java.io.IOException;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * 客户端
 *
 * @author yang.zhang3
 * @create 2017/12/11
 */
public class Client implements Watcher {
    ZooKeeper zk;
    String hostPort;

    public Client(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    String queueCommand(String command) throws Exception {
        String name = "";
        while (true){
            try {
                name = zk.create("/tasks/task-", command.getBytes(), OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                return name;
            }catch (KeeperException.NodeExistsException e){
                throw new Exception(name + " already appears to be running");
            }catch (KeeperException.ConnectionLossException e){

            }
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        Client client = new Client("192.168.217.42:2181,192.168.217.42:2182,192.168.217.42:2183");
        client.startZK();
        String name = client.queueCommand("cmd");
        System.out.println("Created " + name);
    }
}
