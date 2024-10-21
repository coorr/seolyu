package kr.mainstream.seolyu.infrastructure.redis;

public class RedisEnv {
    public static final int ACCESS_KEY_TTL = 1;
    public static final String REFERENCE_KEY_PREFIX = "SL";
    public static final int LOGIN_INFO_TTL = 30;
    public static final String LOGIN_INFO_KEY = "loginInfo";
    public static final String EVENT_KEY = "event";
    public static final int EVENT_KEY_TTL = 1;
    public static final String EVENT_REQUEST_WITH_EVENT_ID_PREFIX = "event.request.eventId=%s";
}
