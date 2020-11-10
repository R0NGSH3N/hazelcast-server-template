package com.r0ngsh3n.hazelcast.server.notifier.channel;


import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;

public interface HazelCastEventNotifierChannel {

    public void sendEvent(HazelCastEvent event);
}
