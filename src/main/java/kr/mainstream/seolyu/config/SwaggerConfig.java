package kr.mainstream.seolyu.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Set;

@EnableSwagger2
public class SwaggerConfig  {
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("kr.mainstream.template"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalRequestParameters(globalRequestParameters())
                .useDefaultResponseMessages(false)
                .produces(Set.of(APPLICATION_JSON_CHARSET_UTF_8));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Template API")
                .description("Template API swagger 문서입니다.")
                .version("1.0")
                .build();
    }

    private List<RequestParameter> globalRequestParameters() {

        RequestParameter testParameter = new RequestParameterBuilder()
                .name("TEST")
                .description("TEST ID")
                .in(ParameterType.HEADER)
                .required(false)
                .build();

        return List.of(testParameter);
    }
}
