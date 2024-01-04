package com.NotesApplication;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
class DatabaseConfiguration {


        @Bean
        MongoClient mongoClient() {
            return MongoClients.create("mongodb://localhost:27017");
        }

        @Bean
        MongoOperations mongoTemplate(MongoClient mongoClient) {
            return new MongoTemplate(mongoClient, "notes_application");
        }
    }