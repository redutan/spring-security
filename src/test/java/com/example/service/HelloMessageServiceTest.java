package com.example.service;

import com.example.SpringSecurityApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    HelloMessageService messageService;

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetMessage_InvalidAuth() throws Exception {
        messageService.getMessage();
        fail("AuthenticationCredentialsNotFoundException 무조건 발생해야함");
    }

    @Test
    @WithMockUser
    public void testGetMessage() throws Exception {
        String message = messageService.getMessage();
        log.info("message = {}", message);
        assertThat(message, is(notNullValue()));
    }

    @WithMockUser(username = "user", password = "1234", roles = "USER")
    @Test(expected = AccessDeniedException.class)
    public void testGetAdminMessage_InvalidAuth() throws Exception {
        messageService.getAdminMessage();
        fail("AccessDeniedException 무조건 발생해야함");
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    public void testGetAdminMessage() throws Exception {
        String adminMessage = messageService.getAdminMessage();
        log.info("adminMessage = {}", adminMessage);
        assertThat(adminMessage, is(notNullValue()));
    }
}