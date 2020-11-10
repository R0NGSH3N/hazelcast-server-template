package com.r0ngsh3n.hazelcast.server.instance;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@ConfigurationProperties("hazelcast")
public class ServerInstance implements FactoryBean<HazelcastInstance> {

    private Config hazelCastConfig;
    private HazelcastInstance serverInstance;

    public void setHazelCastConfig(Config hazelCastConfig) {
        this.hazelCastConfig = hazelCastConfig;
    }

    public void setServerInstance(HazelcastInstance serverInstance) {
        this.serverInstance = serverInstance;
    }

    @PostConstruct
    void init(){
        serverInstance = Hazelcast.newHazelcastInstance(hazelCastConfig);
    }

    @Override
    public HazelcastInstance getObject() throws Exception {
        return serverInstance;
    }

    @Override
    public Class<HazelcastInstance> getObjectType() {
        return HazelcastInstance.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @PreDestroy
    public void destroy(){
       this.serverInstance.shutdown();
    }
}
