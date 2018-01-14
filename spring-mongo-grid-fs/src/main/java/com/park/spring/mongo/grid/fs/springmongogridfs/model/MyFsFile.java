package com.park.spring.mongo.grid.fs.springmongogridfs.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "fs.files")
public class MyFsFile {

    @Id
    private ObjectId id;
    private String filename;
    private String aliases;
    private Long chunkSize;
    private Date uploadDate;
    private Long length;
    private String contentType;
    private String md5;

    public MyFsFile() {
    }

    public MyFsFile(ObjectId id, String filename, String aliases, Long chunkSize, Date uploadDate, Long length, String contentType, String md5) {
        this.id = id;
        this.filename = filename;
        this.aliases = aliases;
        this.chunkSize = chunkSize;
        this.uploadDate = uploadDate;
        this.length = length;
        this.contentType = contentType;
        this.md5 = md5;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getAliases() {
        return aliases;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Long getLength() {
        return length;
    }

    public String getContentType() {
        return contentType;
    }

    public String getMd5() {
        return md5;
    }
}
