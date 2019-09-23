package it.polito.ApplicazioniInternet.Config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration {
    private static final String DB_NAME = "mongo";
    private static final String MONGO_HOST = "192.168.99.100";
    private static final int MONGO_PORT = 27017;

    @Bean
    public MongoClient mongo()throws Exception{
        return new MongoClient(MONGO_HOST,MONGO_PORT);
    }

}
