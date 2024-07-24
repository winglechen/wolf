package com.wolf.common.contract.pattern.strategy;

public interface StrategyHandler<T extends StrategyContext> {

    void chooseStrategy(T context);

    void execute(T context);
}
