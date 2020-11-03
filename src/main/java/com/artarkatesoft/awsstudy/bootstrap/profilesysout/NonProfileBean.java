package com.artarkatesoft.awsstudy.bootstrap.profilesysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NonProfileBean {
    @Autowired
    public NonProfileBean(@Value("${com.artarkatesoft.profile.message}") String msg) {
        System.out.println("***********" + msg + "************");
    }
}
