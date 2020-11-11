package com.r0ngsh3n.hazelcast.server.notifier.channel;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestfulRequestChannel implements HazelCastEventNotifierChannel{
    @Value("${notifier.restfulNotifierChannel.baseUrl")
    private String baseUrl;
    @Value("${notifier.restfulNotifierChannel.uri")
    private String uri;

    @Override
    public void sendEvent(HazelCastEvent event) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        webClient.post().uri(uri)
                .body(Mono.just(event), HazelCastEvent.class)
                .retrieve()
                .bodyToMono(HazelCastEvent.class);
    }
}
