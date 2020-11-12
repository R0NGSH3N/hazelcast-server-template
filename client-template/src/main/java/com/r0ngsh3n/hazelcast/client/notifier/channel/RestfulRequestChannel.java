package com.r0ngsh3n.hazelcast.client.notifier.channel;

import com.r0ngsh3n.hazelcast.client.listener.HazelCastEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestfulRequestChannel implements HazelCastEventNotifierChannel{
    private String baseUrl;
    private String uri;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUri() {
        return uri;
    }

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
