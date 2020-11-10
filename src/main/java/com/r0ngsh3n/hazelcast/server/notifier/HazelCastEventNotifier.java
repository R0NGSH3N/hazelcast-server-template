package com.r0ngsh3n.hazelcast.server.notifier;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.notifier.channel.HazelCastEventNotifierChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HazelCastEventNotifier {
    public enum EVENT_TYPE {
        MEMBERSHIP_EVENT, DISTRIBUTED_OBJECT_EVENT, PATITION_LOST_EVENT
    }

    private Map<EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifier;

    public void setNotifier(Map<EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifier) {
        this.notifier = notifier;
    }

    public Map<EVENT_TYPE, List<HazelCastEventNotifierChannel>> getNotifier() {
        if(notifier == null){
            notifier = new HashMap<EVENT_TYPE, List<HazelCastEventNotifierChannel>>();
        }
        return notifier;
    }

    public void inform(EVENT_TYPE event_type, HazelCastEvent event){
        this.notifier.get(event).stream().forEach(e -> e.sendEvent(event));
    }
}
