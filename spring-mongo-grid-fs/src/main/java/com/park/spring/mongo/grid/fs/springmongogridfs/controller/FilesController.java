package com.park.spring.mongo.grid.fs.springmongogridfs.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @PostMapping // Import file from web page and insert into database
    public HttpEntity<byte[]> upLoad(@RequestParam("file") MultipartFile file) throws ParseException {
        System.out.println(file.getSize());

        // Add metadata detail file in fs.file collection
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        GridFSUploadOptions uploadOptions = new GridFSUploadOptions()
                .metadata(new Document("type", "image") // set metadata field in fs.file collection
                        .append("owner", "Park")
                        .append("upload_date", format.parse("2016-09-01T00:00:00Z")) // First argument is field name second is detail
                        .append("content_type", "image/jpg"));
        try {
            gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), uploadOptions);

            String resp = "<script>window.location = '/';</script>";
            return new HttpEntity<>(resp.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseBody // fetch list of file from database
    public List<String> list() {
        List<String> list = new ArrayList<>();
        gridFsTemplate.find(Query.query(GridFsCriteria.where(null))) // you can also use GridFsCriteria.whereFilename() and etc.
                .forEach((Consumer<? super GridFSFile>) gridFSFile -> list.add(gridFSFile.getFilename()));
        return list;
    }

    @GetMapping("/{filename:.+}") // .+ is mean include type file
    public HttpEntity<byte[]> getFile(@PathVariable("filename") final String filename) {

        GridFsResource gridFsResource = gridFsTemplate.getResource(filename);

        try (InputStream fileIn = gridFsResource.getInputStream();
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] data = new byte[512];
            while (fileIn.read(data) != -1)
                os.write(data);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, gridFsResource.getContentType());

            return new HttpEntity<>(os.toByteArray(), headers);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{filename:.+}")
    public String deleteFile(@PathVariable("filename") final String filename) {
        gridFsTemplate.delete(Query.query(GridFsCriteria.whereFilename().is(filename)));
        return "Delete File " + filename + " Successful";
    }


}
