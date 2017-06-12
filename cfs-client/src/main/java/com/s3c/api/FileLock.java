package com.s3c.api;

/**
 *
 */
public interface FileLock {
    void lock();

    LockInfo getLockInfo();

    void unlock();

    LockStatus getLockStatus();

    enum LockStatus {
        LOCKED, UNLOCKED
    }
}
