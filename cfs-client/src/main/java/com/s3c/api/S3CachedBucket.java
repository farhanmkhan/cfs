package com.s3c.api;

import java.nio.file.Path;
import java.util.Optional;

/**
 *
 */
public interface S3CachedBucket {
    String getBucketName();

    String getRegion();

    Optional<S3CachedObject> get(Path pathInBucket);

}
