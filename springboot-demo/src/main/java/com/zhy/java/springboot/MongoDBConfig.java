package com.zhy.java.springboot;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoDBConfig {
    private String host;
    private int port;
    private String database;
    private int connectionsPerHost;
    private int threadsAllowedToBlockForConnectionMultiplier;
    private int connectTimeout;
    private int maxWaitTime;
    private boolean autoConnectRetry;
    private boolean socketKeepAlive;
    private int socketTimeout;


    @Bean
    public MongoDbFactory mongoDbFactory(){
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port),
                new MongoClientOptions.Builder()
                        .connectionsPerHost(connectionsPerHost)
                        .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
                        .connectTimeout(connectTimeout)
                        .maxWaitTime(maxWaitTime)
                        .socketKeepAlive(socketKeepAlive)
                        .socketTimeout(socketTimeout)
                        .build()
        ), database);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public boolean isAutoConnectRetry() {
        return autoConnectRetry;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
    }

    public boolean isSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
}
