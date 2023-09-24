package com.co.veterinariagian.demo;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
public class MainPetsApplication {

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema1.sql")));
        return initializer;
    }

    @Bean
    ConnectionFactoryInitializer initializer2(ConnectionFactory connectionFactory2) {

        ConnectionFactoryInitializer initializer2 = new ConnectionFactoryInitializer();

        initializer2.setConnectionFactory(connectionFactory2);
        initializer2.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema2.sql")));

        return initializer2;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainPetsApplication.class, args);
    }

}
