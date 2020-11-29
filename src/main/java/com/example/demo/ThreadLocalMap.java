package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gao.rui36
 * @Date 2020/11/29
 **/
public abstract class ThreadLocalMap {

    private static ThreadLocal<Map<String, Object>> currentSession = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public static void put(String key, Object value) {
        System.out.println(Thread.currentThread().getId());
        Map<String, Object> session = currentSession.get();
        if (session != null) {
            session.put(key, value);
        }
    }

    public static void remove(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null) {
            session.remove(key);
        }
    }

    public static Object get(String key) {
        System.out.println(Thread.currentThread().getId());
        Map<String, Object> session = currentSession.get();
        if (session != null) {
            return session.get(key);
        }
        else {
            return null;
        }
    }

    public static Map<String, Object> getSession() {
        return currentSession.get();
    }

    public static Long getLong(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null && session.get(key) != null) {
            return (Long) session.get(key);
        }
        else {
            return null;
        }
    }

    public static String getString(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null && session.get(key) != null) {
            return session.get(key).toString();
        }
        else {
            return null;
        }
    }

    public static void setChannelId(Long channelId) {
        put(Constants.TOKEN_CHANNEL_ID, channelId);
    }

    public static Long getChannelId() {
        return getLong(Constants.TOKEN_CHANNEL_ID);
    }

    /**
     * 添加token中的userGuid
     *
     * @param userGuid
     */
    public static void setUserGuid(String userGuid) {
        put(Constants.TOKEN_USER_GUID, userGuid);
    }

    /**
     * 添加token中的profileGuid
     *
     * @param profileGuid
     */
    public static void setProfileGuid(String profileGuid) {
        put(Constants.TOKEN_PROFILE_GUID, profileGuid);
    }

    /**
     * 添加token中的profileName
     *
     * @param profileName
     */
    public static void setProfileName(String profileName) {
        put(Constants.TOKEN_PROFILE_NAME, profileName);
    }

    /**
     * 添加token中的staffCode
     *
     * @param staffCode
     */
    public static void setStaffCode(String staffCode) {
        put(Constants.TOKEN_STAFF_CODE, staffCode);
    }

    /**
     * 获取token中的userId
     */
    public static String getUserGuid() {
        return getString(Constants.TOKEN_USER_GUID);
    }

    /**
     * 获取token中的profileId
     */
    public static String getProfileGuid() {
        return getString(Constants.TOKEN_PROFILE_GUID);
    }

    public static String getInternalUserGuid() {
        if (getString(Constants.TOKEN_PROFILE_GUID) != null && getString(Constants.TOKEN_PROFILE_GUID) != "") {
            return getString(Constants.TOKEN_USER_GUID);
        }
        return null;
    }

    /**
     * 获取token中的profileName
     */
    public static String getProfileName() {
        return getString(Constants.TOKEN_PROFILE_NAME);
    }

    /**
     * 获取token中的staffCode
     */
    public static String getStaffCode() {
        return getString(Constants.TOKEN_STAFF_CODE);
    }

    /**
     * 移除用户相关信息
     */
    public static void removeUser() {
        remove(Constants.TOKEN_USER_GUID);
        remove(Constants.TOKEN_PROFILE_GUID);
        remove(Constants.TOKEN_PROFILE_NAME);
        remove(Constants.TOKEN_STAFF_CODE);
        remove(Constants.TOKEN_CHANNEL_ID);
    }

    /**
     * ThreadLocalMap相关常量定义
     */
    public abstract static class Constants {
        public static final String TOKEN_USER_GUID = "_TOKEN_USER_GUID_";

        public static final String TOKEN_PROFILE_GUID = "_TOKEN_PROFILE_GUID_";

        public static final String TOKEN_PROFILE_NAME = "_TOKEN_PROFILE_NAME_";

        public static final String TOKEN_STAFF_CODE = "_TOKEN_STAFF_CODE_";

        public static final String TOKEN_CHANNEL_ID = "_TOKEN_CHANNEL_ID_";

        private Constants() {
        }
    }
}

