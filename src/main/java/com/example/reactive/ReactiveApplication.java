package com.example.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableWebFlux
public class ReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> helloRouterFunction() {
//        HandlerFunction<ServerResponse> handlerFunction = serverRequest ->
//                ServerResponse.ok().body(Mono.just("Hello World!"), String.class);

        RouterFunction<ServerResponse> routerFunction =
                RouterFunctions.route(RequestPredicates.path("/"), helloHandler()::handle);

        return routerFunction;
    }

    @Bean
    public HelloHandler helloHandler() {
        return new HelloHandler();
    }

    public class HelloHandler {
        public Mono<ServerResponse> handle(ServerRequest request) {
            return ServerResponse.ok().body(Mono.just("Hola reactive"), String.class);
        }
    }

    @RestController
    public class HelloController {

        @GetMapping("/hello")
        public String handle() {
            return "Hello WebFlux";
        }


    }


}
