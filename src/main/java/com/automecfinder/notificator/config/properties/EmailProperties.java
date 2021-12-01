package com.automecfinder.notificator.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {

    private Credential credential;
    private Smtp smtp;

    @Data
    public static class Credential {
        private String user;
        private String password;
    }

    @Data
    public static class Smtp {
        private String host;
        private SocketFactory socketFactory;
        private String auth;
        private String port;

        @Data
        public static class SocketFactory {
            private String port;

            @Value("${spring.mail.smtp.socketFactory.class}")
            private String clazz;
        }
    }
}
