package com.r0ngsh3n.hazelcast.server.notifier.channel;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class StringOutputChannel implements HazelCastEventNotifierChannel{
    private String message;
    private HazelCastEvent event;

    @Override
    public void sendEvent(HazelCastEvent event) {
        this.event = event;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Event Type: %s ]\n", event.getEventType().toString()));
        sb.append(String.format("[Event Msg: %s ]\n", event.getEventMSG()));
        sb.append(String.format("[Generic Msg: %s ]\n", event.getMessage()));
        sb.append(String.format("[Generic Msg: %s ]\n", new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date(event.getTimestamp()))));
        this.message = sb.toString();
    }
}
