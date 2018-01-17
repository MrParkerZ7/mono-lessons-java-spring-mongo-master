package com.park.spring.mongo.grid.fs.springmongogridfs.component;

import com.park.spring.mongo.grid.fs.springmongogridfs.repository.MyFsFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyGridFsFileLoader implements CommandLineRunner{

    @Autowired
    private MyFsFileRepository myFsFileRepository;

    @Override
    public void run(String... args) throws Exception {


    }
}
