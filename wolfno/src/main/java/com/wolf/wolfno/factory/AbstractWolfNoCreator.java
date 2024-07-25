package com.wolf.wolfno.factory;

import com.wolf.wolfno.model.WolfNoContext;
import lombok.Data;

@Data
public abstract class AbstractWolfNoCreator implements WolfNoCreator {
    protected WolfNoContext context;
    protected String wolfID;
}
