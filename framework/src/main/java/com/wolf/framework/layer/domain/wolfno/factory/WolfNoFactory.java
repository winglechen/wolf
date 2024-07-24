package com.wolf.framework.layer.domain.wolfno.factory;

import com.wolf.framework.layer.domain.wolfno.model.WolfNoContext;

public class WolfNoFactory {
    private final WolfNoContext context;

    public WolfNoFactory(WolfNoContext context) {
        this.context = context;
    }

    public String create() {
        return "";
    }
}
