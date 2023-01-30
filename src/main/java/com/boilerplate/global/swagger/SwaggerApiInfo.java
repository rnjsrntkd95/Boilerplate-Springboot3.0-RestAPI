package com.boilerplate.global.swagger;

import java.util.List;

public enum SwaggerApiInfo {

    BOILERPLATE_API(List.of("/**"), "Boilerplate API"), // TODO: Path 지정 필요
    ;

    public final List<String> path;
    public final String title;

    SwaggerApiInfo(List<String> path, String title) {
        this.path = path;
        this.title = title;
    }

    public static String[] getApiPathArray(SwaggerApiInfo api) {
        return api.path.toArray(new String[0]);
    }
}
