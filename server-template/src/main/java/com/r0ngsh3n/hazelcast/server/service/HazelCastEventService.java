package com.r0ngsh3n.hazelcast.server.service;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Setter
public class HazelCastEventService {

    @Autowired
    private Map<Date, HazelCastEvent> eventQueue;

    public void addEvent(HazelCastEvent event){
        eventQueue.put(new Date(), event);
    }

    public List<HazelCastEvent> getAllEventBetweenDate(Date startDate, Date afterDate){
        return null;
    }

}