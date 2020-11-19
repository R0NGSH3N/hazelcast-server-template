package com.r0ngsh3n.hazelcast.server.listener;

import com.hazelcast.cluster.Address;
import com.hazelcast.cluster.Member;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.internal.json.JsonObject;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import com.r0ngsh3n.hazelcast.server.notifier.channel.HazelCastEventNotifierChannel;
import com.r0ngsh3n.hazelcast.server.notifier.channel.StringOutputChannel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.UnknownHostException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestHazelCastEventListener {
    private HazelCastEventNotifier getHazelCastEventNotifier(HazelCastEvent.EVENT_TYPE eventType, HazelCastEventNotifierChannel eventNotifierChannel){
        //create notifier
        HazelCastEventNotifier hazelCastEventNotifier = new HazelCastEventNotifier();
        //add StringoutputNotifierChannel
        Map<HazelCastEvent.EVENT_TYPE, List<HazelCastEventNotifierChannel>> notifierChannel = new HashMap<>();
        List<HazelCastEventNotifierChannel> channels  = new ArrayList<HazelCastEventNotifierChannel>();
        channels.add(eventNotifierChannel);
        notifierChannel.put(eventType, channels);
        hazelCastEventNotifier.setNotifier(notifierChannel);
        return hazelCastEventNotifier;
    }

    @Test
    public void testmemberAdded() throws UnknownHostException {
        //mock membershipEvent
        MembershipEvent membershipEventMock = Mockito.mock(MembershipEvent.class);
        Member memberMock = Mockito.mock(Member.class);
        when(memberMock.getAddress()).thenReturn(new Address("127.0.0.1", 5701));
        when(memberMock.getUuid()).thenReturn(UUID.randomUUID());
        when(membershipEventMock.getMember()).thenReturn(memberMock);


        //create event listener
        StringOutputChannel stringOutputChannel = new StringOutputChannel();
        HazelCastEventListener listener = new HazelCastEventListener();
        listener.setHazelCastEventNotifier(getHazelCastEventNotifier(HazelCastEvent.EVENT_TYPE.MEMBERS_ADDED_EVENT, stringOutputChannel));
        listener.memberAdded(membershipEventMock);
        assertEquals(HazelCastEvent.EVENT_TYPE.MEMBERS_ADDED_EVENT, stringOutputChannel.getEvent().getEventType());
        assertTrue(stringOutputChannel.getEvent().getMessage().contains("Member Info [Address: [127.0.0.1]:5701"));
        assertTrue(stringOutputChannel.getEvent().getTimestamp() - System.currentTimeMillis() < 1000);
//        System.out.println(stringOutputChannel.getMessage());
    }
}
