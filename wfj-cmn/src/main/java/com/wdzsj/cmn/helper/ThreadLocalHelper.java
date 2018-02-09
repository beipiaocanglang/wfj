package com.wdzsj.cmn.helper;

public class ThreadLocalHelper {

    private static ThreadLocal<Long> userId = new ThreadLocal<Long>();

    public static void setUserId(Long _userId) {
        userId.set(_userId);
    }

    public static Long getUserId() {
        return userId.get() == null ? 0 : userId.get();
    }

    public static void removeUserId() {
        userId.remove();
    }

}
