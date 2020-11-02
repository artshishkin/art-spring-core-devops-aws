package com.artarkatesoft.awsstudy.test.dstest;

import com.artarkatesoft.awsstudy.test.config.DataSourceConfig;
import com.artarkatesoft.awsstudy.test.ds.FakeDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DataSourceConfig.class)
//@ActiveProfiles("dev")
public class DataSourceTest {

    @Autowired
    FakeDataSource fakeDataSource;

    @Test
    public void testDataSource() {
        System.out.println(fakeDataSource.getConnectionInfo());
    }
}
