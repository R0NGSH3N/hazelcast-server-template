package com.r0ngsh3n.hazelcast.server.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.MapConfig;
import com.r0ngsh3n.hazelcast.server.listener.HazelCastEventListener;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("hazelcast")
@Validated
public class HazelCastConfig {

    private String loggingType;
    private String eventThreadCount;
    private String diagnosticsEnabled;
    private String healthLogLevel;
    private String enableJMX;
    private String slowOpDetector;
    private String enableREST;
    private String enableMemCache;
    private String instanceName;
    private int port;

    @Value("${notifier.channelMap}")
    private Map<String, String> hazelcastNotificationChannelMap;

    public void setLoggingType(@NonNull String loggingType) {
        this.loggingType = loggingType;
    }

    public void setEventThreadCount(String eventThreadCount) {
        this.eventThreadCount = eventThreadCount;
    }

    public void setDiagnosticsEnabled(String diagnosticsEnabled) {
        this.diagnosticsEnabled = diagnosticsEnabled;
    }

    public void setHealthLogLevel(String healthLogLevel) {
        this.healthLogLevel = healthLogLevel;
    }

    public void setEnableJMX(String enableJMX) {
        this.enableJMX = enableJMX;
    }

    public void setSlowOpDetector(String slowOpDetector) {
        this.slowOpDetector = slowOpDetector;
    }

    public void setEnableREST(String enableREST) {
        this.enableREST = enableREST;
    }

    public void setEnableMemCache(String enableMemCache) {
        this.enableMemCache = enableMemCache;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    Config hazelCastConfig(MapConfig defualtMapConfig, HazelCastEventListener hazelCastEventListener) {
        Config hazelcastConfig = new Config();

        hazelcastConfig.setProperty("hazelcast.logging.type", loggingType);
        hazelcastConfig.setProperty("hazelcast.event.thread.count", eventThreadCount);
        hazelcastConfig.setProperty("hazelcast.diagnostics.enabled", diagnosticsEnabled);
        hazelcastConfig.setProperty("hazelcast.health.monitoring.level", healthLogLevel);
        hazelcastConfig.setProperty("hazelcast.jmx", enableJMX);
        hazelcastConfig.setProperty("hazelcast.slow.operation.detector.enabled", slowOpDetector);
        hazelcastConfig.setProperty("hazelcast.rest.enabled", enableREST);
        hazelcastConfig.setProperty("hazelcast.memcache.enabled", enableMemCache);

        hazelcastConfig.setInstanceName(instanceName);
        hazelcastConfig.getNetworkConfig().setPortAutoIncrement(true);
        hazelcastConfig.getNetworkConfig().setPort(port);

        hazelcastConfig.addMapConfig(defualtMapConfig);
        hazelcastConfig.addListenerConfig(new ListenerConfig(hazelCastEventListener));

        return hazelcastConfig;
    }

    @Bean
    MapConfig defaultMapConfig() {
        MapConfig defaultMapConfig = new MapConfig("default");
        defaultMapConfig.setStatisticsEnabled(true);
        defaultMapConfig.setBackupCount(2);
        return defaultMapConfig;
    }

    @Bean
    HazelCastEventListener hazelCastEventListener(HazelCastEventNotifier hazelCastEventNotifier){
        HazelCastEventListener hazelCastEventListener = new HazelCastEventListener();
        hazelCastEventListener.setHazelCastEventNotifier(hazelCastEventNotifier);
        return hazelCastEventListener;
    }

    HazelCastEventNotifier hazelCastEventNotifier(){
        HazelCastEventNotifier hazelCastEventNotifier = new HazelCastEventNotifier();
        //TODO hook up event and notifier
        return hazelCastEventNotifier;

    }

}
