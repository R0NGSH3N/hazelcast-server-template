package com.r0ngsh3n.hazelcast.client.notifier.channel;


import com.r0ngsh3n.hazelcast.client.listener.HazelCastEvent;

public interface HazelCastEventNotifierChannel {

    public void sendEvent(HazelCastEvent event);
}
