package com.onedot.win.common.lang.contract.pattern.strategy;

public interface Strategy<T extends StrategyContext> {

    void execute(T context);
}
