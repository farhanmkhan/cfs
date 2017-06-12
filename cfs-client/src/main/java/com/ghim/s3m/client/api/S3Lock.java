package com.ghim.s3m.client.api;

import com.s3c.api.S3LockInfo;

import java.io.Closeable;
import java.io.IOException;

/**
 * Lock on an S3 object in a given Region. The locking mechanism will only be effective if {@link
 * AmazonS3LockableClient} implementation is used to modify S3 objects.
 * <p/>
 *
 */
public interface S3Lock extends Closeable {
    /**
     * Gets the ID of the lock; Every lock has unique ID which identifies a lock on an S3 object in any given region.
     *
     * @return
     */
    String lockID();

    /**
     * @return
     */
    S3LockInfo getLockInfo();

    boolean lock();

    LockStatus getStatus();

    void unLock();

    @Override
    default void close() throws IOException {
        unLock();
    }

    enum LockStatus {
        LOCKED, UNLOCKED
    }
}
