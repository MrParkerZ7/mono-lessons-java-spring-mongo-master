package com.park.spring.mongo.grid.fs.api.springmongogridfsapi.controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequestMapping
public class HomeController {

    @PostMapping("/files")
    public ObjectId upLoad(@RequestParam("file") MultipartFile file) {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.JAPAN);
            ObjectId fileId = null;

            MongoDatabase database = mongoClient.getDatabase("spring.data.mongodb.database");
            GridFSBucket gridFSBuckets = GridFSBuckets.create(database);

            // Create some custom options
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions()
                    .chunkSizeBytes(1024)
                    .metadata(new Document("type", "image")
                            .append("upload_data", format.parse("2018-01-15T00:00:00Z"))
                            .append("content_type", "image/jpg"));
            fileId = gridFSBuckets.uploadFromStream(file.getOriginalFilename(), file.getInputStream(), uploadOptions);

            return fileId;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
