package br.edu.unicesumar.pubsub.controller;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Binding.DestinationType;
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
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/group/{groupName}")
    public ResponseEntity<Void> CreateGroup(@PathVariable(name = "groupName") String groupName) {
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("grupo-" + groupName, true, false);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);

        Binding binding = new Binding(Message.myUser, DestinationType.QUEUE, fanoutExchangeTeste.getName(),
                Message.myUser, null);
        this.amqpAdmin.declareBinding(binding);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/group/join/{groupName}/{username}")
    public ResponseEntity<Void> EnterGroup(@PathVariable(name = "groupName") String groupName,
            @PathVariable(name = "username") String username) {
        Binding binding = new Binding(username, DestinationType.QUEUE, "group-" + groupName,
                Message.myUser, null);
        this.amqpAdmin.declareBinding(binding);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/direct/{username}")
    public ResponseEntity<Void> SendDirectMessage(@PathVariable(name = "username") String username,
            @RequestBody Message msg) {
        System.out.println(msg);

        this.rabbitTemplate.convertAndSend("msg-direct", username, msg);

        return ResponseEntity.ok().build();
    }
}
