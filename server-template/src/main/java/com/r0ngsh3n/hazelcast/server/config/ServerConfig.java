package com.r0ngsh3n.hazelcast.server.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.MapConfig;
import com.r0ngsh3n.hazelcast.server.entity.SampleDataSerializableFactory;
import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.listener.HazelCastEventListener;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import com.r0ngsh3n.hazelcast.server.notifier.channel.HazelCastEventNotifierChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("hazelcast")
@Validated
public class ServerConfig {

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
    @Profile("server")
    Config hazelCastConfigServer(MapConfig defualtMapConfig, HazelCastEventListener hazelCastEventListener) {
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
    @Profile("instance")
    Config hazelCastConfigInstance(MapConfig defualtMapConfig) {
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
        hazelcastConfig.getSerializationConfig().addDataSerializableFactoryClass(SampleDataSerializableFactory.SAMPLE_DATA_KEY_FACTORY_ID, SampleDataSerializableFactory.class);

        hazelcastConfig.addMapConfig(defualtMapConfig);

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
    @Profile("server")
    HazelCastEventListener hazelCastEventListener(HazelCastEventNotifier hazelCastEventNotifier) {
        HazelCastEventListener hazelCastEventListener = new HazelCastEventListener();
        hazelCastEventListener.setHazelCastEventNotifier(hazelCastEventNotifier);
        return hazelCastEventListener;
    }

    @Bean
    @Profile("server")
    HazelCastEventNotifier hazelCastEventNotifier(List<HazelCastEventNotifierChannel> notifiers) {
        HazelCastEventNotifier hazelCastEventNotifier = new HazelCastEventNotifier();
        Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> eventNotifiersMap = new HashMap<>();
        for (HazelCastEvent.EVENT_TYPE eventType : HazelCastEvent.EVENT_TYPE.values()) {
            eventNotifiersMap.put(eventType, notifiers);
        }
        hazelCastEventNotifier.setNotifier(eventNotifiersMap);
        return hazelCastEventNotifier;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("your.name@gmail.com");
        mailSender.setPassword("1234");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
