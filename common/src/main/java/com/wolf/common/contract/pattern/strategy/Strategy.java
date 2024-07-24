package com.wolf.common.contract.pattern.strategy;

public interface Strategy<T extends StrategyContext> {

    void execute(T context);
}
