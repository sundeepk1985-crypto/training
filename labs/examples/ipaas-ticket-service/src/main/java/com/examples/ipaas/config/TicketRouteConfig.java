package com.examples.ipaas.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketRouteConfig {

    // Rabbit MQ Connection Factory bean
    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
        rabbitConnectionFactory.setHost("localhost");
        rabbitConnectionFactory.setPort(5672);
        return rabbitConnectionFactory;
    }

    @Bean
    public RouteBuilder acceptAndSendTicketToMQ() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // capture ticket details
                from("direct:acceptTicket")
                        .log("TICKET RECEIVED - ${body}")
                        // send to rabbit mq
                        .to("rabbitmq:ipaas.xchange.direct?queue=tickets&routingKey=ticket&autoDelete=false")
                        .log("Ticket send to MQ");
            }
        };
    }

}
