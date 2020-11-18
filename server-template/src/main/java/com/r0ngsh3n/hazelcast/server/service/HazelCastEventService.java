package com.r0ngsh3n.hazelcast.server.service;

import com.hazelcast.core.HazelcastInstance;
import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Setter
public class HazelCastEventService {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private Map<Date, HazelCastEvent> eventQueue;

    public void addEvent(HazelCastEvent event){
        eventQueue.put(new Date(), event);
    }

    public List<HazelCastEvent> getAllEvents(){
        return eventQueue.values().stream().collect(Collectors.toList());
    }

}