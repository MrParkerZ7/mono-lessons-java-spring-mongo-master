package com.park.spring.mongo.grid.fs.springmongogridfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@SpringBootApplication
public class SpringMongoGridFsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoGridFsApplication.class, args);
    }

}
