package com.r0ngsh3n.hazelcast.server.config;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import com.r0ngsh3n.hazelcast.server.notifier.channel.HazelCastEventNotifierChannel;
import com.r0ngsh3n.hazelcast.server.notifier.channel.RestfulRequestChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties("notifiers")
public class NotifierConfig {

    private Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> eventNotifierMap;

    public void setEventNotifierMap(Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> eventNotifierMap) {
        this.eventNotifierMap = eventNotifierMap;
    }
    //    @Bean
//    RestfulRequestChannel restfulRequestChannel(@Value("${notifier.restfulNotifierChannel.baseUrl") String baseUrl,
//                                                @Value("${notifier.restfulNotifierChannel.uri") String uri ){
//       RestfulRequestChannel restfulRequestChannel = new RestfulRequestChannel();
//       restfulRequestChannel.setBaseUrl(baseUrl);
//       restfulRequestChannel.setUri(uri);
//       return restfulRequestChannel;
//    }

    @Bean
    HazelCastEventNotifier hazelCastEventNotifier(){
        HazelCastEventNotifier hazelCastEventNotifier = new HazelCastEventNotifier();
        hazelCastEventNotifier.setNotifier(eventNotifierMap);
        return hazelCastEventNotifier;
    }

}
