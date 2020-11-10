package com.r0ngsh3n.hazelcast.server.notifier.channel;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestfulRequestChannel implements HazelCastEventNotifierChannel{
    @Value()
    private String baseUrl;
    @Value()
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
