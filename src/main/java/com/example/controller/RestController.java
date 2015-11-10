package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author redutan
 * @since 2015. 11. 9.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/rest")
    public String check() {
        return "OK";
    }
}
