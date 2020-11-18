package com.r0ngsh3n.hazelcast.server.notifier.channel;

import com.r0ngsh3n.hazelcast.server.listener.HazelCastEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailChannel implements HazelCastEventNotifierChannel {
    @Autowired
    private JavaMailSender emailSender;
    @Value("${emailTo}")
    private String emailTo;

    @Override
    public void sendEvent(HazelCastEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hazelcast.server@gmail.com");
        message.setTo(emailTo);
        message.setSubject("Server Error");
        message.setText(event.getMessage());
        emailSender.send(message);
    }
}
