package com.artarkatesoft.awsstudy.test.external.props;

import com.artarkatesoft.awsstudy.SpringCoreDevOpsApplication;
import com.artarkatesoft.awsstudy.test.jms.FakeJmsBroker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringCoreDevOpsApplication.class)
@WebIntegrationTest(randomPort = true)
@TestPropertySource("/application.properties")
public class SpringBootPropertiesTest {

    @Autowired
    FakeJmsBroker fakeJmsBroker;

    @Test
    public void testPropsSet() {
        assertEquals("10.10.10.123", fakeJmsBroker.getUrl());
        assertEquals(3330L, fakeJmsBroker.getPort().intValue());
        assertEquals("Nazar", fakeJmsBroker.getUser());
        assertEquals("123", fakeJmsBroker.getPassword());
    }
}
