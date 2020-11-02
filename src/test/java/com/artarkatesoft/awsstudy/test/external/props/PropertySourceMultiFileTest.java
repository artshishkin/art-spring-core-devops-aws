package com.artarkatesoft.awsstudy.test.external.props;

import com.artarkatesoft.awsstudy.test.jms.FakeJmsBroker;
import com.artarkatesoft.test.config.external.props.ExternalPropsMultiFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExternalPropsMultiFile.class})
public class PropertySourceMultiFileTest {

    @Autowired
    FakeJmsBroker fakeJmsBroker;

    @Test
    public void testPropsSet() {
        assertEquals("10.10.10.123", fakeJmsBroker.getUrl());
        assertEquals(3330L, fakeJmsBroker.getPort().intValue());
        assertEquals("Nazar", fakeJmsBroker.getUser());
        assertEquals("jskfjskf89849tj4oj))()_)", fakeJmsBroker.getPassword());
    }
}
