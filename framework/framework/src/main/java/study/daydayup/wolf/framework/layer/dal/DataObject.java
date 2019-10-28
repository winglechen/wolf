package study.daydayup.wolf.framework.layer.dal;

import lombok.Data;

import java.util.Date;

/**
 * study.daydayup.wolf.framework.layer.dal
 *
 * @author Wingle
 * @since 2019/10/29 12:22 上午
 **/
@Data
public class DataObject {
    private long id;
    private boolean deleted;
    private int version;

    private long creator;
    private long lastEditor;

    private Date createdAt;
    private Date updatedAt;
}
