package com.wolf.framework.layer.domain.wolfno.factory;

import com.wolf.framework.layer.domain.wolfno.model.WolfNoContext;
import java.time.LocalDateTime;

public interface WolfNoCreator {

    void setContext(WolfNoContext context);
    void setWolfID(String ID);
    void setCreateTime(LocalDateTime createTime);

    WolfNoContext getContext();
    String getWolfID();
    LocalDateTime getCreateTime();

    String create();
}
