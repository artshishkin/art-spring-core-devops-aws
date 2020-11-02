package com.artarkatesoft.test.config.external.props;

import com.artarkatesoft.awsstudy.test.jms.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
        @PropertySource("classpath:testing.properties"),
        @PropertySource("classpath:encrypted-testing.properties")
})
public class ExternalPropsMultiFileS4 {

    @Autowired
    Environment env;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        return FakeJmsBroker.builder()
                .url(env.getProperty("app.jms.server"))
                .port(env.getProperty("app.jms.port", Integer.class))
                .user(env.getProperty("app.jms.user"))
                .password(env.getProperty("app.jms.encrypted.password"))
                .build();
    }
}
