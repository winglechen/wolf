package study.daydayup.wolf.framework.middleware.cloud.storage;

import study.daydayup.wolf.common.lang.exception.SystemException;

public class InvalidCloudStorageConfigException extends SystemException {
    public InvalidCloudStorageConfigException() {
        super(160500, "invalid cloud storage config");
    }

    public InvalidCloudStorageConfigException(String message) {
        super(160500, message);
    }

    public InvalidCloudStorageConfigException(long code, String message) {
        super(code, message);
    }
}
