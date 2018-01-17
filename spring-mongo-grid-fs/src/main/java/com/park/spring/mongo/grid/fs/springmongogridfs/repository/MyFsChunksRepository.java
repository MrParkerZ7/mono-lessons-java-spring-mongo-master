package com.park.spring.mongo.grid.fs.springmongogridfs.repository;

import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsChunks;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyFsChunksRepository extends MongoRepository<MyFsChunks,ObjectId> {
}
