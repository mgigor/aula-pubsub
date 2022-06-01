package br.edu.unicesumar.pubsub.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.pubsub.dto.Message;

@RestController
@RequestMapping("/msg")
public class MessageController {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/direct/{username}")
    public ResponseEntity<Void> SendDirectMessage(@PathVariable(name="username") String username, @RequestBody Message msg ) {
        System.out.println(msg);

        this.rabbitTemplate.convertAndSend("msg-direct", username, msg);

        return ResponseEntity.ok().build();
    }
}
