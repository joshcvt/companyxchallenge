package org.behindthenet.companyxchallenge.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    // for demo, setting up user/pass in application.properties. In a real application you'd have a
    // configureGlobal(AuthenticationManagerBuilder auth) method here that gets you an external auth.
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/api/user/**").hasRole("ROLE_USER")
            .antMatchers(HttpMethod.POST,"/api/user/**").hasRole("ROLE_USER")
            .and()
            .csrf().disable()
            .formLogin().disable();
    }
}
