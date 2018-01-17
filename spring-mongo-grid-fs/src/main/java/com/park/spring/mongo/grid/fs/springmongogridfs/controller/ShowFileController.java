package com.park.spring.mongo.grid.fs.springmongogridfs.controller;

import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsChunks;
import com.park.spring.mongo.grid.fs.springmongogridfs.repository.MyFsChunksRepository;
import com.park.spring.mongo.grid.fs.springmongogridfs.model.MyFsFile;
import com.park.spring.mongo.grid.fs.springmongogridfs.repository.MyFsFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/files/{filename:.+}")
    public MyFsFile getFile(@PathVariable("filename") final String filename) {
        return myFsFileRepository.findByFilename(filename);
    }

    @GetMapping("/chunks")
    public List<MyFsChunks> getAllChunks() {
        return myFsChunksRepository.findAll();
    }



}
