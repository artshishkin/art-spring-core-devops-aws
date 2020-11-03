package com.artarkatesoft.awsstudy.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
@EncryptablePropertySource(name = "qaEncryptedProperties",value = "classpath:qa.encrypted.properties")
public class QaEncryptedConfig {
}