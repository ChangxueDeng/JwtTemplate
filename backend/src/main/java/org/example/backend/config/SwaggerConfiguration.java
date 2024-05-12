package org.example.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.example.backend.entity.Result;
import org.example.backend.entity.vo.response.AuthorizeVO;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ChangxueDeng
 * @date 2024/03/04
 */
@Configuration

@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer",
            name = "Authorization", in = SecuritySchemeIn.HEADER) //安全方案
@OpenAPIDefinition(security = {@SecurityRequirement(name = "Authorization")})

public class SwaggerConfiguration {

    /**
     * 配置文档介绍
     * @return {@link OpenAPI}
     */
    @Bean
    public OpenAPI myOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Jwt API 文档")
                        .description("欢迎来到本项目API测试文档，在这里可以快速进行接口调试")
                        .version("1.0")
                        .license(new License().name("项目开源地址")
                                .url("https://github.com/ChangxueDeng/Jwt")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring官网")
                        .url("https://spring.io/"));
    }

    /**
     * 处理自定义的openApi信息
     * @return {@link OpenApiCustomizer}
     */
    @Bean
    public OpenApiCustomizer myOpenApiCustomizer(){
        return api -> this.authorizePathItems().forEach(api.getPaths()::addPathItem);
    }

    /**
     * 自定义的路径路径项(退出登陆接口)
     * @return {@link Map}<{@link String}, {@link PathItem}>
     */
    private Map<String, PathItem> authorizePathItems(){
        Map<String, PathItem> map = new HashMap<>();
        //login
        map.put("/api/auth/login", new PathItem()
                .post(new Operation()
                        .tags(List.of("登陆校验相关"))
                        .summary("登陆验证接口")
                        .addParametersItem(new QueryParameter()
                                .name("username")
                                .required(true))
                        .addParametersItem(new QueryParameter()
                                .name("password")
                                .required(true))
                        .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                .description("OK")
                                .content(new Content().addMediaType("*/*", new MediaType()
                                        .example(Result.success(new AuthorizeVO())
                                                        .toJsonString())))))));

        map.put("api/auth/logout", new PathItem()
                .get(new Operation().
                        tags(List.of("登陆校验相关"))
                        .description("退出登陆接口")
                        .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                .description("OK")
                                .content(new Content().addMediaType("*/*", new MediaType()
                                        .example(Result.success())))))));

        return map;
    }






}
