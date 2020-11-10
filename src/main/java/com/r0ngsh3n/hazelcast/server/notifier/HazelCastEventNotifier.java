package com.r0ngsh3n.hazelcast.server.notifier;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.notifier.channel.HazelCastEventNotifierChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HazelCastEventNotifier {

    private Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifier;

    public void setNotifier(final Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifier) {
        this.notifier = notifier;
    }

    public Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> getNotifier() {
        if (notifier == null) {
            notifier = new HashMap<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>>();
        }
        return notifier;
    }

    public void inform(final HazelCastEvent event) {
        this.notifier.get(event.getEventType()).forEach(e -> e.sendEvent(event));
    }
}
