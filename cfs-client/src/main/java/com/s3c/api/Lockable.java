package com.s3c.api;

import java.util.Optional;

/**
 *
 */
public interface Lockable {
    boolean tryLock(int timeout, int idleExpiration);

    Optional<LockInfo> getLockInfo();

    void unlock();
}
