package com.r0ngsh3n.hazelcast.client.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.r0ngsh3n.hazelcast.client.listener.ClientMapEntryListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("hazelcast")
@Validated
public class ClientConfig {

    @Bean
    public HazelcastInstance clientInstance(ClientConfig config, List<MapConfig> mapconfigs, ClientMapEntryListener clientMapEntryListener) {
        HazelcastInstance clientInstance = HazelcastClient.newHazelcastClient();
        mapconfigs.forEach(e -> clientInstance.getMap(e.getName()).addEntryListener(clientMapEntryListener, true));
        return clientInstance;
    }

    @Bean
    public MapConfig sampleDateMap() {
        MapConfig sampleDataMapConfig = new MapConfig();
        sampleDataMapConfig.setName("SampleDataMap");
        sampleDataMapConfig.setTimeToLiveSeconds(-3);
        return sampleDataMapConfig;
    }

    @Bean
    public ClientMapEntryListener clientMapEntryListener() {
        ClientMapEntryListener clientMapEntryListener = new ClientMapEntryListener();
        //TODO add hookup
        return clientMapEntryListener;
    }

}
