package com.song.basic_x.enums;

/**
 * 锁类型
 */
public enum LockType {
    /**
     * 可重入锁
     */
    ReentrantLock,
    /**
     * 公平锁
     */
    FairLock,
    /**
     * 非公平锁
     */
    NonFairLock;

}

