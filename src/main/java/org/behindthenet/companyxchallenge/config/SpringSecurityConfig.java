package org.behindthenet.companyxchallenge.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    // for demo, setting up user/pass in application.properties. In a real application you'd have a
    // configureGlobal(AuthenticationManagerBuilder auth) method here that gets you an external auth.
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.GET,"/api/user/**").authenticated()
            .antMatchers(HttpMethod.POST,"/api/user/**").authenticated()
            .anyRequest().authenticated();
    }

}
