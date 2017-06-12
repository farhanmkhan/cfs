package com.s3c.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 *
 */
public interface S3CachedObject extends Lockable {
    S3CachedObject createAtomically(Path pathInBucket);

    boolean exists();

    boolean notExists();

    String getBucket();

    String getRegion();

    Path getPathInBucket();

    void loadInCache();

    void evict();

    InputStream openInputStream();

    OutputStream openOutputStream();
}
