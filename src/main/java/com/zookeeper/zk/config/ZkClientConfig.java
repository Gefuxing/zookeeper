package com.zookeeper.zk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author gefuxing
 * @create 2021/8/14 17:04
 */
@Configuration
public class ZkClientConfig {

    private String ip = "10.60.158.72:2181";

    @Bean
    public ZooKeeper getClient() throws IOException {
        return new ZooKeeper(ip, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
