package com.artarkatesoft.awsstudy.bootstrap.profilesysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevProfileSysOut {

    @Autowired
    public DevProfileSysOut(@Value("${com.artarkatesoft.profile.message}") String msg, Environment env) {
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("##           DEV          ##");
        System.out.println(msg);
        System.out.println(env.getProperty("com.artarkatesoft.profile.message2"));
        System.out.println("############################");
        System.out.println("############################");
    }
}
