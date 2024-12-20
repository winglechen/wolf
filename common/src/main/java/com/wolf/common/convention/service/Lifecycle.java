package com.wolf.common.convention.service;

public interface Lifecycle {
    void start();
    void shutdown();

    /**
     * Initialization
     */
    void initialize();

    /**
     * cleanup
     */
    void cleanup();

    State getState();

    default boolean isRunning() {
        return State.RUNNING.equals(getState());
    }

    enum State {
        INITIALIZING,
        STARTING,
        RUNNING,
        STOPPING,
        STOPPED,
        SHUTTING_DOWN,
        TERMINATED
    }
}
