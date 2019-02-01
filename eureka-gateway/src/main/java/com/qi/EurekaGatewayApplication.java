package com.qi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
public class EurekaGatewayApplication {
    private static final Logger logger = LoggerFactory.getLogger( EurekaGatewayApplication.class );

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes( )
                .route( r -> r.path( "/baidu" )
                        .uri( "http://baidu.com:80/" )
                )
                .route( "websocket_route", r -> r.path( "/**" )
                        .uri( "ws://127.0.0.1:8081" ) )
                .route( r -> r.path( "/userapi3/**" )
                        .filters( f -> f.addResponseHeader( "X-AnotherHeader", "testapi3" ) )

                        .uri( "lb://user-service/" )
                )
                .build( );
    }

    public static void main(String[] args) {
        SpringApplication.run( EurekaGatewayApplication.class, args );
        logger.info( " Start APIGatewayApplication Done" );
    }

}

