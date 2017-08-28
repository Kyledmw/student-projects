package com.appl_maint_mngt.auth.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appl_maint_mngt.auth.security.jwt.ITokenExtractor;
import com.appl_maint_mngt.auth.security.jwt.JwtAuthenticationProcessingFilter;
import com.appl_maint_mngt.auth.security.jwt.JwtAuthenticationProvider;
import com.appl_maint_mngt.auth.security.rest.RestAuthenticationEntryPoint;
import com.appl_maint_mngt.auth.security.util.SkipPathRequestMatcher;
import com.appl_maint_mngt.auth.web.constants.IAuthRestEntryPoints;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String REST_ENTRY_POINT = "/**";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    
	@Autowired
	private AuthenticationFailureHandler failureHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    
	@Autowired
	private ITokenExtractor tokenExtractor;
	
    @Autowired
    private AuthenticationManager authManager;

    protected JwtAuthenticationProcessingFilter buildJwtAuthenticationProcessingFilter() throws Exception {
    	List<String> pathsToSkip = Arrays.asList(IAuthRestEntryPoints.REST_LOGIN_ENTRY_POINT, IAuthRestEntryPoints.REST_REG_ENTRY_POINT);
    	SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, REST_ENTRY_POINT);
    	JwtAuthenticationProcessingFilter filter = new JwtAuthenticationProcessingFilter(matcher, tokenExtractor, failureHandler);
        filter.setAuthenticationManager(authManager);
    	return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
    	auth.authenticationProvider(jwtAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(IAuthRestEntryPoints.REST_REFRESH_ENTRY_POINT).permitAll()
        .antMatchers(IAuthRestEntryPoints.REST_LOGIN_ENTRY_POINT).permitAll()
        .antMatchers(IAuthRestEntryPoints.REST_REG_ENTRY_POINT).permitAll()
        .and()
        .authorizeRequests()
            .antMatchers(REST_ENTRY_POINT).authenticated()
            .and()
            .addFilterBefore(buildJwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
