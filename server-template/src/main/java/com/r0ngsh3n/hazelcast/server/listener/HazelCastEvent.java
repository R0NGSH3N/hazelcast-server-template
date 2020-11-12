package com.r0ngsh3n.hazelcast.server.listener;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.partition.PartitionLostEvent;
import org.springframework.context.ApplicationEvent;

public class HazelCastEvent extends ApplicationEvent {

    public enum EVENT_TYPE {
        MEMBERS_ADDED_EVENT,
        MEMBERS_REMOVED_EVENT,
        DISTRIBUTED_OBJECT_CREATED_EVENT,
        DISTRIBUTED_OBJECT_DESTROYED_EVENT,
        PARTITION_LOST_EVENT,
        LIFECYCLE_CHANGE_EVENT
    }

    private EVENT_TYPE eventType;
    private final String message;
    private String EventMSG;

    public EVENT_TYPE getEventType() {
        return eventType;
    }

    public String getEventMSG() {
        return EventMSG;
    }

    public String getMessage() {
        return message;
    }

    public HazelCastEvent(Object source, String message){
        super(source);
        this.message = message;
    }

    public HazelCastEvent(Object source, EVENT_TYPE event_type, MembershipEvent membershipEvent) {
        super(source);
        this.eventType = event_type;
        message = String.format("Member Info [Address: %s; uuid: %s;]", membershipEvent.getMember().getAddress(), membershipEvent.getMember().getUuid().toString());
        this.EventMSG = membershipEvent.toString();
    }

    public HazelCastEvent(Object source, EVENT_TYPE event_type, DistributedObjectEvent distributedObjectEvent) {
        super(source);
        this.eventType = event_type;
        DistributedObject ob = distributedObjectEvent.getDistributedObject();
        message = String.format("Distributed Object Info [ name:  %s; serviceName: %s]", ob.getName(), ob.getServiceName());
        this.EventMSG = distributedObjectEvent.toString();
    }

    public HazelCastEvent(Object source, EVENT_TYPE event_type, PartitionLostEvent partitionLostEvent) {
        super(source);
        this.eventType = event_type;
        message = String.format(" Lost Partition Info [ PartitionId: %s; Address: %S]", partitionLostEvent.getPartitionId(), partitionLostEvent.getEventSource().toString());
        this.EventMSG = partitionLostEvent.toString();
    }

    public HazelCastEvent(Object source, EVENT_TYPE event_type, LifecycleEvent lifecycleEvent) {
        super(source);
        this.eventType = event_type;
        message = String.format("Life Cycle State: [ %s ]", lifecycleEvent.getState());
        this.EventMSG = lifecycleEvent.toString();
    }
}
