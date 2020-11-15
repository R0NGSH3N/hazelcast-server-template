package com.r0ngsh3n.hazelcast.server.controller;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("events")
public class ServerEventRestController {


    @PostMapping("/sendEvent")
    public HazelCastEvent sendEvent(@RequestBody @Valid HazelCastEvent event){

        return event;
    }
}
