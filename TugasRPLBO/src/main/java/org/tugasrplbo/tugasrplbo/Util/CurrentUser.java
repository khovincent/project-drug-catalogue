package org.tugasrplbo.tugasrplbo.Util;

public class CurrentUser {
    private static String username;
    private static String status;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        CurrentUser.status = status;
    }

    public static void clear() {
        username = null;
        status = null;
    }
}
