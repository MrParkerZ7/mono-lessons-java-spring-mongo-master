package com.park.spring.mongo.grid.fs.springmongogridfs.model;

public abstract class AbstractMyGridFsFile<M extends AbstractMyGridFsFile> {

    private M metadata;

    public M getMetadata() {
        return metadata;
    }

    public void setMetadata(M metadata) {
        this.metadata = metadata;
    }
}
