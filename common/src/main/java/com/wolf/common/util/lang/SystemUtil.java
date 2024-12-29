package com.wolf.common.util.lang;


public class SystemUtil {
    public static long getPID() {
        return ProcessHandle.current().pid();
    }

    public static long getPageSize() {
        return 0;
    }
}
