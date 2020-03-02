package com.examples.camel;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRouteConfig {

    // Timer & Logger Example
//    @Bean
    public RouteBuilder timerLogRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Log greeting meeting
                from("timer:demo")
                        .log("${headers}")
                        .log("${body}")
                        // log endpoint
                        .to("log:Welcome to Camel Training!");

                // just logs message
//                from("timer:demo")
//                        .log("Welcome to Camel Training!");
            }
        };
    }

        // File copy
//        @Bean
        public RouteBuilder fileCopyRoute() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // File
                    from("file:input")
                            .log("${headers}")
                            .log("${body}")
                            .to("file:output");
                }
            };
        }


            // File copy: input => validated => processed
            // Processor example
//            @Bean
            public RouteBuilder fileCopyRoute1() {
                return new RouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        // File
                        from("file:input")
                                .log("${headers}")
                                .log("${body}")
                                .process(new Processor() {
                                    @Override
                                    public void process(Exchange exchange) throws Exception {
                                        System.out.println("Before Validation: " + exchange.getIn().getBody());
                                        String body = exchange.getIn().getBody(String.class);
                                        body = body + " Validated";
                                        exchange.getOut().setBody(body);
                                    }
                                })
                                .to("file:validated");

                        from("file:validated")
                                .log("${headers}")
                                .log("${body}")
                                .process(new Processor() {
                                    @Override
                                    public void process(Exchange exchange) throws Exception {
                                        System.out.println("Before Processing: " + exchange.getIn().getBody());
                                        String body =  exchange.getIn().getBody(String.class);
                                        body = body + " Processed";
                                        exchange.getOut().setBody(body);
                                    }
                                })
                                .log("${headers}")
                                .log("${body}")
                                .to("file:processed");
                    }
                };
    }

    // File copy with Direct component
//    @Bean
    public RouteBuilder fileCopyWithDirect() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // File
                from("file:input")
                        .log("${headers}")
                        .log("${body}")
                        .to("direct:fileProcess");

                from("direct:fileProcess")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("Before Validation: " + exchange.getIn().getBody());
                                String body = exchange.getIn().getBody(String.class);
                                body = body + " Validated";
                                exchange.getOut().setBody(body);
                            }
                        })
                        .to("file:validated");
            }
        };
    }

        // File copy with SEDA component
//        @Bean
        public RouteBuilder fileCopyWithSeda() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // File
                    from("file:input")
                            .log("${headers}")
                            .log("${body}")
                            .to("seda:fileProcess");

                    from("seda:fileProcess")
                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    System.out.println("Before Validation: " + exchange.getIn().getBody());
                                    String body = exchange.getIn().getBody(String.class);
                                    body = body + " Validated";
                                    exchange.getOut().setBody(body);
                                }
                            })
                            .to("file:validated");
                }
            };
    }

    // Content Based Routing component
    // input => xml => xmlOutput
    //          txt => txtOutput
    //          others => invalid
//    @Bean
    public RouteBuilder contentBasedRouter() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // File
                from("file:input")
                        .log("${headers}")
                        .log("${body}")
                        .to("seda:fileProcess");

                from("seda:fileProcess")
                	.choice()
                		.when(header("CamelFileName").endsWith("txt")).to("direct:text")
                		.when(header("CamelFileName").endsWith("xml")).to("direct:xml")
                		.otherwise().to("direct:invalid")
                        .end();

                from("direct:text").to("file:txtOutput");
                from("direct:xml").to("file:xmlOutput");
                from("direct:invalid").to("file:invalid");
            }
        };
    }

    // Splitter example. Split input data by | delimeter
//    @Bean
    public RouteBuilder splitter() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // File
                from("file:input")
                        .log("${headers}")
                        .log("${body}")
                        .to("seda:fileProcess");

                from("seda:fileProcess")
                        .split(body().tokenize("\\|"))
                        // .tokenize("\\|")
                        .log("${body}")
                        .filter(body().isEqualToIgnoreCase("Hello"))
                        .to("file:splitOutput?fileName=splitOutput.txt&fileExist=Append");
            }
        };
    }

    // Rabbit MQ Connection Factory bean
    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
        rabbitConnectionFactory.setHost("localhost");
        rabbitConnectionFactory.setPort(5672);
        return rabbitConnectionFactory;
    }

    // File to RabbitMQ (Direct Exchange)
//    @Bean
    public RouteBuilder fileToRabbitMQ1() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // File
                from("file:input")
                        .log("SENT - ${headers}")
                        .log("SENT - ${body}")
                        // specify routingKey to route the message as per DIRECT exchange binding
                        .setHeader("rabbitmq.ROUTING_KEY", simple("test"))
                        // .to("rabbitmq://localhost:5672/rabbitmq.xchange.direct?autoDelete=false");
                        .to("rabbitmq:rabbitmq.xchange.direct?autoDelete=false");

                from("rabbitmq:rabbitmq.xchange.direct?queue=test-queue&autoDelete=false")
                        .log("RECEIVED - ${headers}")
                        .log("RECEIVED - ${body}")
                        .to("file://output");
            }
        };
    }

    // File to RabbitMQ (Topic Exchange)
//    @Bean
    public RouteBuilder fileToRabbitMQ2() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:input")
                        .log("SENT - ${headers}")
                        .log("SENT - ${body}")
                        // specify routingKey to route the message as per TOPIC exchange binding
                        .setHeader("rabbitmq.ROUTING_KEY", simple("test.greeting"))
                        // rabbitmq://hostname[:port]/exchangeName?[options]
                        .to("rabbitmq://rabbitmq.xchange.topic?exchangeType=topic&autoDelete=false");

                // specify rountingKey if binding not there
                // from("rabbitmq:rabbitmq.xchange.topic?exchangeType=topic&queue=test-queue1&routingKey=test.*&autoDelete=false")

                // routingKey not required if binding is there already
                from("rabbitmq:rabbitmq.xchange.topic?exchangeType=topic&queue=test-queue1&autoDelete=false")
                        .log("RECEIVED - ${headers}")
                        .log("RECEIVED - ${body}")
                        .to("file://output");
            }
        };
    }

    // File to RabbitMQ (Fanout Exchange)
//    @Bean
    public RouteBuilder fileToRabbitMQ3() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // routingKey not required for FANOUT exchange
                from("file:input")
                        .log("SENT - ${headers}")
                        .log("SENT - ${body}")
                        .to("rabbitmq://rabbitmq.xchange.fanout?exchangeType=fanout&autoDelete=false");

                from("rabbitmq:rabbitmq.xchange.fanout?exchangeType=fanout&queue=test-queue2&autoDelete=false")
                        .log("RECEIVED - ${headers}")
                        .log("RECEIVED - ${body}")
                        .to("file://output");
            }
        };
    }

//    @Bean
//    public Processor  fileProcessor () {
////        return () -> {
////            throw new CamelException("Custom camel exception");
////        };
//
//        new Processor() {
//
//        }
//
//    }

    // Exception Handling
    @Bean
    public RouteBuilder defaultExceptionHandler() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // File
                from("file:input")
                        .log("${headers}")
                        .log("${body}")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                throw new CamelException("Customer camel exception");
                            }
                        })
                        .to("file:output");
            }
        };
    }

}