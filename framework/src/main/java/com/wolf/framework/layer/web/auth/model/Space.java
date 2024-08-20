package com.wolf.framework.layer.web.auth.model;

import com.wolf.common.ds.map.ObjectMap;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Space implements Serializable {
    private Long accountId;
    private Long spaceId;
    private Integer role;

    private String name;
    private String icon;
    private String cover;
    private String introduction;

    private ObjectMap property;
    private ObjectMap permission;
}
