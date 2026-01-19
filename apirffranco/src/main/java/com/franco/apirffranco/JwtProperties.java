package com.franco.apirffranco;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    public static String secretKey;
    public static int timeAuthMs;
    public static int timeRefreshMs;

    public void setSecretKey(String secretKey) {
        JwtProperties.secretKey = secretKey;
    }

    public void setTimeAuthMs(int timeAuthMs) {
        JwtProperties.timeAuthMs = timeAuthMs;
    }

    public void setTimeRefreshMs(int timeRefreshMs) {
        JwtProperties.timeRefreshMs = timeRefreshMs;
    }
}
