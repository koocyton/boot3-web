package com.doopp.boot3.web.admin.server.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.doopp.boot3.web.admin.server.Application;


@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Test
    public void testBase() {
        String s = null;
        Assert.isNull(s, s);
    }
}




