package com.boilerplate.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.boilerplate.global.swagger.SwaggerConfigConstants.BEARER_SCHEME;
import static com.boilerplate.global.swagger.SwaggerConfigConstants.JWT;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig()
                .replaceWithClass(LocalDateTime.class, String.class)
                .replaceWithClass(LocalDate.class, String.class)
                .replaceWithClass(LocalTime.class, String.class)
                .addAnnotationsToIgnore(AuthenticationPrincipal.class, CookieValue.class);
    }

    @Bean
    public GroupedOpenApi boilerplateApi() {
        String[] boilerplateApiPath = SwaggerApiInfo.getApiPathArray(SwaggerApiInfo.BOILERPLATE_API);
        return GroupedOpenApi.builder()
                .group(SwaggerConfigConstants.BOILERPLATE_GROUP)
                .pathsToMatch(boilerplateApiPath)
                .build();
    }

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .info(createApiInfo())
                .components(createApiComponent())
                .addServersItem(createApiServer());
    }

    private Info createApiInfo() {
        return new Info()
                .title(SwaggerConfigConstants.TITLE)
                .description(SwaggerConfigConstants.DESCRIPTION)
                .version(SwaggerConfigConstants.VERSION)
                .license(createApiInfoLicense());
    }

    private License createApiInfoLicense() {
        return new License()
                .name(SwaggerConfigConstants.LICENSE)
                .url(SwaggerConfigConstants.LICENSE_URL);
    }

    private Components createApiComponent() {
        return new Components()
                .addSecuritySchemes(JWT, createJwtSecurityScheme());
    }

    private SecurityScheme createJwtSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER_SCHEME)
                .bearerFormat(JWT)
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }

    private Server createApiServer() {
        return new Server().url(SwaggerConfigConstants.SERVER_URL);
    }
}
