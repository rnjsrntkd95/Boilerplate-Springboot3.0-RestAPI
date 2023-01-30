package com.boilerplate.global.security.profile;

import org.springframework.core.env.Environment;

public class AppProfiles {

    public static final String LOCAL = "local";
    public static final String KR_DEVELOPMENT = "kr-development";
    public static final String KR_STAGING = "kr-staging";
    public static final String KR_PRODUCTION = "kr-production";

    public static String getActiveProfile(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length < 1) {
            throw new IllegalStateException("Active Profile Not Found");
        }
        return profiles[0];
    }
}
