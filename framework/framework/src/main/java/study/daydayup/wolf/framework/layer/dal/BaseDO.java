package study.daydayup.wolf.framework.layer.dal;

import java.util.Date;

/**
 * study.daydayup.wolf.framework.layer.dal
 *
 * @author Wingle
 * @since 2019/10/29 12:22 上午
 **/
public class BaseDO {
    private boolean deleted;
    private int version;
    
    private long creator;
    private long lastEditor;

    private Date createdAt;
    private Date updatedAt;

}
