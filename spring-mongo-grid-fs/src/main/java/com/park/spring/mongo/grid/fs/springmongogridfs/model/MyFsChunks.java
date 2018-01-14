package com.park.spring.mongo.grid.fs.springmongogridfs.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fs.chunks")
public class MyFsChunks {

    @Id
    private ObjectId id;
    @DBRef
    private ObjectId files_id;
    private Integer n;
    private Binary data;

    public MyFsChunks() {
    }

    public MyFsChunks(ObjectId id, ObjectId files_id, Integer n, Binary data) {
        this.id = id;
        this.files_id = files_id;
        this.n = n;
        this.data = data;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getFiles_id() {
        return files_id;
    }

    public Integer getN() {
        return n;
    }

    public Binary getData() {
        return data;
    }
}
