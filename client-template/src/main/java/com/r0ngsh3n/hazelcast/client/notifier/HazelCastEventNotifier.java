package com.r0ngsh3n.hazelcast.client.notifier;

import com.r0ngsh3n.hazelcast.client.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.client.notifier.channel.HazelCastEventNotifierChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HazelCastEventNotifier {

    private Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifierchannelmap;

    public void setNotifier(Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifier) {
        this.notifierchannelmap = notifier;
    }

    public Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> getNotifier() {
        if (notifierchannelmap == null) {
            notifierchannelmap = new HashMap<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>>();
        }
        return notifierchannelmap;
    }

    public void inform(final HazelCastEvent event) {
        this.notifierchannelmap.get(event.getEventType()).forEach(e -> e.sendEvent(event));
    }
}
