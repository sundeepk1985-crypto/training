package com.examples.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileCopyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Log greeting meeting
        from("timer:demo")
                .to("log:Welcome to Camel Training!");
    }
}
