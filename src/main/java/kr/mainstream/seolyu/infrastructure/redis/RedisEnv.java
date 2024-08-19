package kr.mainstream.seolyu.infrastructure.redis;

public class RedisEnv {
    public static final int ACCESS_KEY_TTL = 1;           // 90일
    public static final String REFERENCE_KEY_PREFIX = "SL";
    public static final int LOGIN_INFO_TTL = 30;
    public static final String LOGIN_INFO_KEY = "loginInfo";
}
