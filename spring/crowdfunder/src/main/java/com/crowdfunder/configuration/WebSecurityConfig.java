package com.crowdfunder.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 ********************************************************************
 * A class that contains our WebSecurity configuration.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String CSS_MATCHER = "/css/**";
	private static final String JS_MATCHER = "/js/**";
	private static final String REG_MATCHER = "/registration";
	private static final String ROOT_MATCHER = "/";
	private static final String API_MATCHER = "/api/**";
	private static final String PROJECTS_MATCHER = "/projects";
	private static final String PROJECTS_SUB_MATCHER = "/projects/**";
	
	private static final String LOGIN_ROUTE = "/login";

	@Autowired
	private UserDetailsService _userDetServ;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(CSS_MATCHER, JS_MATCHER, REG_MATCHER, ROOT_MATCHER, API_MATCHER, PROJECTS_MATCHER, PROJECTS_SUB_MATCHER)
		.permitAll().anyRequest().authenticated()
				.and().formLogin()
				.loginPage(LOGIN_ROUTE).permitAll().and().logout().permitAll();
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(_userDetServ).passwordEncoder(bCryptPasswordEncoder());
    }
}
