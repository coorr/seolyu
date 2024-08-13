package kr.mainstream.seolyu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.LOGIN_INFO_KEY;
import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.LOGIN_INFO_TTL;


@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
    private final String redisHost;

    public RedisConfig(@Value("${spring.data.redis.host}") String redisHost) {
        this.redisHost = redisHost;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, 6379);
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

        Map<String, RedisCacheConfiguration> cacheConfiguration = new HashMap<>();
        cacheConfiguration.put(LOGIN_INFO_KEY, loginInfo);
        return cacheConfiguration;
    }
}
