package com.zookeeper.zk.service;

import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author gefuxing
 * @create 2021/8/14 17:09
 */
@RestController
public class produce {
    @Autowired
    private ZooKeeper zooKeeper;

    @GetMapping("/get")
    public String pro() throws KeeperException, InterruptedException {
        UUID uuid = UUID.randomUUID();
        String s = zooKeeper.create("/service/ip", uuid.toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        return s;

    }

}
