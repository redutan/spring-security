package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author redutan
 * @since 2015. 11. 9.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/admin")
    public void admin() {
    }

    @RequestMapping("/login")
    public void login() {
    }

    @RequestMapping("/user")
    public void user() {
    }
}



