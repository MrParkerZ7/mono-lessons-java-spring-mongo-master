package com.park.spring.mongo.grid.fs.springmongogridfs.controller;


import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class FileStoreController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @GetMapping("/find")
    public GridFSFile getFind() {
        return gridFsTemplate.findOne(Query.query(Criteria.where("filename").is("ตารางนำเสนอโปรเจค.xlsx")));
    }

    @GetMapping("/filter")
    public void getFilter() {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(),"spring.data.mongodb.database");
        GridFS gridFS = new GridFS((DB) mongoTemplate.getDb());
    }
}
