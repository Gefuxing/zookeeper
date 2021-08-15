package com.zookeeper.zk.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author gefuxing
 * @create 2021/8/14 17:19
 */
@Log4j
@Log4j2
public class consu
{
    private static ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        consu con = new consu();
        con.connection();

        con.getList();

    Thread.sleep(Integer.MAX_VALUE);

    }

    private void getList() throws KeeperException, InterruptedException {
        List<String> node =new ArrayList<>();
        List<String> children = zooKeeper.getChildren("/service", true);
        for (String child : children) {
            byte[] data = zooKeeper.getData("/service/" + child, false, null);
            node.add(new String(data));
        }
        System.out.println(node.toString());
    }

    private void connection() throws IOException {
        String ip = "10.60.158.72:2181";
        zooKeeper =new ZooKeeper(ip, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
