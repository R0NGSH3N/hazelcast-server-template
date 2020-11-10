package com.r0ngsh3n.hazelcast.server.listener;

import com.hazelcast.cluster.Cluster;
import com.hazelcast.cluster.Member;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.partition.PartitionLostEvent;

import java.io.Serializable;
import java.util.Set;

public class HazelCastEvent implements Serializable {
    public enum EVENT_TYPE {
        MEMBERS_ADDED_EVENT,
        MEMBERS_REMOVED_EVENT,
        DISTRIBUTED_OBJECT_CREATED_EVENT,
        DISTRIBUTED_OBJECT_DESTROYED_EVENT,
        PARTITION_LOST_EVENT,
        LIFECYCLE_CHANGE_EVENT
    }
    private EVENT_TYPE eventType;
    private Cluster cluster;
    private Member member;
    private Set<Member> members;
    private String toStringMSG;

    public EVENT_TYPE getEventType() {
        return eventType;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public Member getMember() {
        return member;
    }

    public Set<Member> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return toStringMSG;
    }

    public HazelCastEvent(EVENT_TYPE event_type, MembershipEvent membershipEvent){
        this.eventType = event_type;
        this.cluster = membershipEvent.getCluster();
        this.members = membershipEvent.getMembers();
        this.member = membershipEvent.getMember();
        this.toStringMSG = membershipEvent.toString();
    }

    public HazelCastEvent(EVENT_TYPE event_type, DistributedObjectEvent distributedObjectEvent){
       distributedObjectEvent.getDistributedObject();
    }

    public HazelCastEvent(EVENT_TYPE event_type, PartitionLostEvent partitionLostEvent){
        partitionLostEvent.getPartitionId();
    }

    public HazelCastEvent(EVENT_TYPE event_type, LifecycleEvent lifecycleEvent){
        lifecycleEvent.getState();
    }
}
