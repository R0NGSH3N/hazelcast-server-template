package com.r0ngsh3n.hazelcast.server.controller;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import com.r0ngsh3n.hazelcast.server.service.HazelCastEventService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("events")
@Setter
public class ServerEventRestController {

    @Autowired
    private HazelCastEventService hazelCastEventService;

    @PostMapping("/sendEvent")
    public HazelCastEvent sendEvent(@RequestBody @Valid HazelCastEvent event){
        hazelCastEventService.addEvent(event);
        return event;
    }

    @GetMapping("/getAllEvents")
    public List<HazelCastEvent> getAllEvents(){
        return hazelCastEventService.getAllEvents();
    }


}
