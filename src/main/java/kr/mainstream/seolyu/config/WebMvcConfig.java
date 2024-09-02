package kr.mainstream.seolyu.config;

import kr.mainstream.seolyu.domain.reviewer.login.LoginService;
import kr.mainstream.seolyu.interceptor.CookieInterceptor;
import kr.mainstream.seolyu.interceptor.LoginCheckInterceptor;
import kr.mainstream.seolyu.login.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final GatewayService gatewayService;
    private final LoginService loginService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = Arrays.asList(
                "/",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/ping",
                "/favicon.ico",
                "/index.html",
                "/test",
                "/test/**",
                "/health"
        );

        registry.addInterceptor(new CookieInterceptor(gatewayService, loginService))
                .excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(new LoginCheckInterceptor())
                .excludePathPatterns(excludePathPatterns)
                .excludePathPatterns("/applicants/**", "/login", "/resume-reviews/**", "/resume-reviews");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }
}
