package br.edu.unicesumar.pubsub.service;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConnection {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void adiciona() {

        Queue filaTeste = new Queue("fila-teste", true, false, false);
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("fanout-exchange-teste", true, false);
        Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, fanoutExchangeTeste.getName(), null, null);
        this.amqpAdmin.declareQueue(filaTeste);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTeste);

        this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), null, "message");

    }

}
