package com.example.controller;

import com.example.domain.Account;
import com.example.repository.AccountRepository;
import com.example.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author redutan
 * @since 2015. 11. 9.
 */
@Controller
public class HomeController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @RequestMapping("/registrationForm")
    public void registrationForm() {

    }

    @RequestMapping("/register")
    public String register(Account account) {
        // DB저장
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);

        // SecurityContextHolder에서 받아서 Context 받아 인증 설정
        User userDetails = new UserDetailsImpl(account);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    @RequestMapping("/getPrivateMessage")
    @ResponseBody
    @PreAuthorize("#account.username == principal.username or hasRole('ADMIN')")
    public String authString(Account account) {
        return "당신은 관리자이거나, 요청 파라미터와 아이디가 같습니다.";
    }

    @RequestMapping("/getUserMessage")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public String userMessage(Account account) {
        return "당신은 한낱 유저입니다 ㅠ";
    }

    @RequestMapping("/403")
    public void accessDeniedPage() {
    }
}