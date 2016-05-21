package com.importsource.util.concurrent.locks;
public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}