package com.example.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * @author redutan
 * @since 2015. 11. 9.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String REMEMBER_ME_KEY = "redutanKey";
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configuGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN");
//        auth.jdbcAuthentication().dataSource(dataSource);
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll();
        // for h2
//        http.authorizeRequests().antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("/h2/**").permitAll();
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();

        http.formLogin()
                .loginPage("/login").permitAll();
        http.rememberMe().key(REMEMBER_ME_KEY).rememberMeServices(tokenBasedRememberMeServices());
    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices =
                new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        tokenBasedRememberMeServices.setCookieName("redutanToken");
        return tokenBasedRememberMeServices;
    }

    // h2 web console
//    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/h2/*");
        return registration;
    }
}
