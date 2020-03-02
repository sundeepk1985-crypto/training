package com.examples.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Camel Timer Example
 */
public final class CamelTimerExample {

    private CamelTimerExample() {
    }

    public static void main(String args[]) throws Exception {

        // Step 1: Create CamelContext
        CamelContext context = new DefaultCamelContext();

        // Step 2: Create Routes and Add to Context
        context.addRoutes(new RouteBuilder() {
            public void configure() {

                onException(Exception.class).log("Error occured ${body}").to("rabbitmq:");

//                from("timer:demo?delay=10s&period=10s")
//                        .log("Timer Triggered");
//
//                from("timer:demo1")
//                        .log("Testing...");

                from("file:input?fileName=abc.txt").doTry()
                        .log("Testing")
                        .doCatch(RuntimeException e)
                        .to("file:output");


            }
        });


        // Step 3: Start CamelContext
        context.start();

        // wait a bit and then stop
        Thread.sleep(300000);

        // Step 4: Stop CamelContext
        context.stop();
    }
}
