package com.r0ngsh3n.hazelcast.server.listener;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.core.*;
import com.hazelcast.partition.PartitionLostEvent;
import com.hazelcast.partition.PartitionLostListener;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import static com.hazelcast.map.EventLostEvent.EVENT_TYPE;

public class HazelCastEventListener implements MembershipListener, DistributedObjectListener, PartitionLostListener, LifecycleListener, ApplicationListener<HazelCastEvent> {
    private HazelCastEventNotifier hazelCastEventNotifier;

    @Autowired
    public void setHazelCastEventNotifier(HazelCastEventNotifier hazelCastEventNotifier) {
        this.hazelCastEventNotifier = hazelCastEventNotifier;
    }

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
       hazelCastEventNotifier.inform(new HazelCastEvent(membershipEvent, HazelCastEvent.EVENT_TYPE.MEMBERS_ADDED_EVENT, membershipEvent));

    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        hazelCastEventNotifier.inform(new HazelCastEvent(membershipEvent, HazelCastEvent.EVENT_TYPE.MEMBERS_REMOVED_EVENT, membershipEvent));
    }

    @Override
    public void distributedObjectCreated(DistributedObjectEvent event) {
        hazelCastEventNotifier.inform(new HazelCastEvent(event, HazelCastEvent.EVENT_TYPE.DISTRIBUTED_OBJECT_CREATED_EVENT, event));
    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent event) {
        hazelCastEventNotifier.inform(new HazelCastEvent(event, HazelCastEvent.EVENT_TYPE.DISTRIBUTED_OBJECT_DESTROYED_EVENT, event));
    }

    @Override
    public void partitionLost(PartitionLostEvent event) {
        hazelCastEventNotifier.inform(new HazelCastEvent(event, HazelCastEvent.EVENT_TYPE.PARTITION_LOST_EVENT, event));
    }

    @Override
    public void stateChanged(LifecycleEvent event) {
        hazelCastEventNotifier.inform(new HazelCastEvent(event, HazelCastEvent.EVENT_TYPE.LIFECYCLE_CHANGE_EVENT, event));
    }

    @Override
    public void onApplicationEvent(HazelCastEvent event) {
        hazelCastEventNotifier.inform(event);
    }
}
