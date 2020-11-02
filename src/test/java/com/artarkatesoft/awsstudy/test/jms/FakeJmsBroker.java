package com.artarkatesoft.awsstudy.test.jms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FakeJmsBroker {
    private String url;
    private Integer port;
    private String user;
    private String password;
}
