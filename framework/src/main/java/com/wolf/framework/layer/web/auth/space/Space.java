package com.wolf.framework.layer.web.auth.space;

import com.wolf.common.ds.map.ObjectMap;
import java.io.Serializable;
import lombok.Data;

@Data
public class Space implements Serializable {
    private Long spaceId;
    private SpaceRoleEnum role;
    private String name;
    private String icon;
    private String cover;
    private String introduction;
    private ObjectMap property;
    private ObjectMap permission;
}
