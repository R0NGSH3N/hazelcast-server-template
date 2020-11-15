package com.r0ngsh3n.hazelcast.server.service;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class HazelCastEventService {

    @Autowired
    private List<HazelCastEvent> eventQueue;

    public Boolean addEvent(HazelCastEvent event){
        return eventQueue.add(event);
    }

    public HazelCastEvent getAllEvent(){

    }

}