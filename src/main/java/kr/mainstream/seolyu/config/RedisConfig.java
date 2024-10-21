package kr.mainstream.seolyu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.*;


@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
    private static final String REDISSON_HOST_PREFIX = "redis://";
    private final String redisHost;

    private final int redisPort;
    private final ObjectMapper objectMapper;

    public RedisConfig(@Value("${spring.data.redis.host}") String redisHost,
                       @Value("${spring.data.redis.port}") int redisPort,
                       ObjectMapper objectMapper) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.objectMapper = objectMapper;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory())
                .cacheDefaults(redisCacheConfiguration())
                .withInitialCacheConfigurations(redisCacheConfigurationMap())
                .build();
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + redisHost + ":"  + redisPort);
        return Redisson.create(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(springSessionDefaultRedisSerializer(objectMapper));
        /* Redis Pub/Sub기능에서 Message 직렬화를 위해 추가 */
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(springSessionDefaultRedisSerializer(objectMapper));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer(ObjectMapper objectMapper) {
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(30))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
        RedisCacheConfiguration loginInfo = redisCacheConfiguration().entryTtl(Duration.ofMinutes(LOGIN_INFO_TTL));
        RedisCacheConfiguration event = redisCacheConfiguration().entryTtl(Duration.ofMinutes(EVENT_KEY_TTL));

        Map<String, RedisCacheConfiguration> cacheConfiguration = new HashMap<>();
        cacheConfiguration.put(LOGIN_INFO_KEY, loginInfo);
        cacheConfiguration.put(EVENT_KEY, event);
        return cacheConfiguration;
    }
}
