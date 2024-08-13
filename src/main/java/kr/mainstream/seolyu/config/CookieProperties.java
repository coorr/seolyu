package kr.mainstream.seolyu.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "session.cookie")
public class CookieProperties {
    private String domain;
    private String name;
}
