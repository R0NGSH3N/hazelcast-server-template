package com.r0ngsh3n.hazelcast.server.service;

import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.notifier.channel.EmailChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

public class ScheduledMonitorTask {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private EmailChannel emailChannel;

    @Scheduled(fixedRate = 60 * 1000)
    public void checkStatus(){
        //check cluster safety
        if(!hazelcastInstance.getPartitionService().isClusterSafe()){
            HazelCastEvent event = new HazelCastEvent(this, String.format("Cluster %s is Not safe.", hazelcastInstance.getName()));
            emailChannel.sendEvent(event);
        }

        //check each member safety
        Set<Member> members =  hazelcastInstance.getCluster().getMembers();
        members.forEach(e -> {
            if(!hazelcastInstance.getPartitionService().isMemberSafe(e)){
                HazelCastEvent event = new HazelCastEvent(this, String.format("Member %s is Not safe.", e.getAddress()));
                emailChannel.sendEvent(event);
            }
        });
    }
}
