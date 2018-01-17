package com.park.spring.mongo.grid.fs.springmongogridfs.repository;

import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyFsFileRepository extends MongoRepository<MyFsFile,ObjectId>{


    MyFsFile findByFilename(String filename);
}
