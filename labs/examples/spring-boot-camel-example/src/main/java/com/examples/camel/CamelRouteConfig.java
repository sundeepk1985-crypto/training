package com.examples.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRouteConfig {

//    @Bean
    public RouteBuilder timerLogRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Log greeting meeting
                from("timer:demo")
                        .log("${headers}")
                        .log("${body}")
                        .to("log:Welcome to Camel Training!");

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

//    from("file://test").log("${headers}").log("${body}").to("direct:process");
//
//    // Content Based Routing
//    from("direct:process")
//                	.choice()
//                		.when(header("CamelFileName").endsWith("txt")).to("direct:text")
//                		.when(header("CamelFileName").endsWith("csv")).to("direct:csv")
//                		.otherwise().to("direct:invalid");
//
//
//    from("direct:text").log("${body}").to("file:text");
//    from("direct:csv").log("${body}").to("file:csv");
//    from("direct:invalid").log("${body}").to("file:invalid");

    // Content Based Routing component
    // input => xml => xmlOutput
//                txt => txtOutput
//             others => invalid
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
                        .
                        .to("seda:fileProcess");


                from("seda:fileProcess")
                        .split(body().tokenize("\\|"))
                        // .tokenize("\\|")
                        .log("${body}")
                        .filter(body().isEqualToIgnoreCase("Hello"))
                        .b
                        .to("file:splitOutput?fileName=splitOutput.txt&fileExist=Append");
            }
        };
    }

    // File to RabbitMQ
    @Bean
    public RouteBuilder fileToRabbitMQ() {
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
                        // rabbitmq://hostname[:port]/exchangeName?[options]
                        .to("rabbitmq://localhost:5672/rabbitmq.demo.xchange?exchangeType=fanout&autoDelete=false");

            }
        };
    }

}
