package com.ghim.s3m.client.api;

/**
 *
 */
public interface S3LockingService {
    S3Lock newLock(String bucket, String key);

    S3Lock newLock(String bucket, String key, long expiration);
}
