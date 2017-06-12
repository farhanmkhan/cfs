package com.s3c.api;

/**
 *
 */
public class LockInfo {
    private final String lockID;
    private final long expiration;
    private final long startTimestamp;

    public LockInfo(String lockID, int expiration, long startTimestamp) {
        this.lockID = lockID;
        this.expiration = expiration;
        this.startTimestamp = startTimestamp;
    }

    public String getLockID() {
        return lockID;
    }

    public long getExpiration() {
        return expiration;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    enum LockStatus {
        LOCKED, UNLOCKED
    }
}
