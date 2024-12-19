package com.wolf.common.convention.service;

public interface Lifecycle {
    void start();

    void shutdown();

    boolean isRunning();
}
