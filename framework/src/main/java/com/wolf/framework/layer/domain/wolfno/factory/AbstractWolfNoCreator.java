package com.wolf.framework.layer.domain.wolfno.factory;

import com.wolf.framework.layer.domain.wolfno.model.WolfNoContext;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public abstract class AbstractWolfNoCreator implements WolfNoCreator {
    protected WolfNoContext context;
    protected String wolfID;
    protected LocalDateTime createTime;
}
