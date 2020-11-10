package com.r0ngsh3n.hazelcast.server.listener;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.core.*;
import com.hazelcast.partition.PartitionLostEvent;
import com.hazelcast.partition.PartitionLostListener;
import com.r0ngsh3n.hazelcast.server.notifier.HazelCastEventNotifier;
import org.springframework.beans.factory.annotation.Autowired;

public class HazelCastEventListener implements MembershipListener, DistributedObjectListener, PartitionLostListener, LifecycleListener {
    private HazelCastEventNotifier hazelCastEventNotifier;
    private HazelcastInstance serverInstance;

    @Autowired
    public void setHazelCastEventNotifier(HazelCastEventNotifier hazelCastEventNotifier) {
        this.hazelCastEventNotifier = hazelCastEventNotifier;
    }

    public void setServerInstance(HazelcastInstance serverInstance) {
        this.serverInstance = serverInstance;
    }

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {

    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {

    }

    @Override
    public void distributedObjectCreated(DistributedObjectEvent event) {

    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent event) {

    }

    @Override
    public void partitionLost(PartitionLostEvent event) {

    }

    @Override
    public void stateChanged(LifecycleEvent event) {
    }
}
