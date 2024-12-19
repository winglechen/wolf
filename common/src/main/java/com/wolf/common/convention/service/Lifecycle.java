package com.wolf.common.convention.service;

public interface Lifecycle {
    void start();

    void stop();

    boolean isRunning();
}
