package com.example.service;

import com.example.SpringSecurityApplication;
import com.example.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @author redutan
 * @since 2015. 11. 12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringSecurityApplication.class)
//@WebAppConfiguration
@Slf4j
public class HelloMessageServiceTest {

    @Autowired
    HelloMessageService helloMessageService;

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetMessage_InvalidAuth() throws Exception {
        helloMessageService.getMessage();
        fail("AuthenticationCredentialsNotFoundException 무조건 발생해야함");
    }

    @Test
    @WithMockUser
    public void testGetMessage() throws Exception {
        String message = helloMessageService.getMessage();
        log.debug("message = {}", message);
        assertThat(message, is(notNullValue()));
    }
}