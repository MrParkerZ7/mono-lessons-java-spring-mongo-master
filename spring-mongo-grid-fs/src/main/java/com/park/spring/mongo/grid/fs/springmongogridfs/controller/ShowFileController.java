package com.park.spring.mongo.grid.fs.springmongogridfs.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsChunks;
import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsChunksRepository;
import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsFile;
import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

/**
 * Fetch list by
 */

@RestController
@RequestMapping("/shows")
public class ShowFileController {

    @Autowired
    private MyFsFileRepository myFsFileRepository;

    @Autowired
    private MyFsChunksRepository myFsChunksRepository;

    @GetMapping("/files")
    public List<MyFsFile> getAllFile() {
        return myFsFileRepository.findAll();
    }

    @GetMapping("/files/{filename}")
    public MyFsFile getFile(@PathVariable("filename") final String filename) {
        return myFsFileRepository.findByFilename(filename);
    }

    @GetMapping("/chunks")
    public List<MyFsChunks> getAllChunks() {
        return myFsChunksRepository.findAll();
    }



}
