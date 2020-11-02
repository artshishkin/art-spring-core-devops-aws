package com.artarkatesoft.awsstudy.test.config;

import com.artarkatesoft.awsstudy.test.jms.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootJavaConfig {

    @Value("${app.jms.server}")
    private String url;
    @Value("${app.jms.port}")
    private Integer port;
    @Value("${app.jms.user}")
    private String user;
    @Value("${app.jms.password}")
    private String password;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        return FakeJmsBroker.builder()
                .url(url)
                .port(port)
                .user(user)
                .password(password)
                .build();
    }
}
