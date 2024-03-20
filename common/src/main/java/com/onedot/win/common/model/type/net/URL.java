package com.onedot.win.common.model.type.net;

import lombok.Data;
import com.onedot.win.common.model.contract.DataType;

/**
 * com.onedot.win.common.model.type.string
 *
 * @author Wingle
 * @since 2019/10/15 1:34 下午
 **/
@Data
public class URL implements DataType {
    private String protocol;
    private String host;
    private int port = -1;
    private String file;
    private String query;
    private String path;
    private String ref;

}
