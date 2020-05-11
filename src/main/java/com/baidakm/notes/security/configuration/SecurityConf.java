package com.baidakm.notes.security.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.baidakm.notes.security.AuthProcessingFilterImp;
import com.baidakm.notes.security.SkipPathRequestMatcher;
import com.baidakm.notes.security.resolvers.AuthResolver;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter{
	
	@Autowired private AuthResolver resolver;
	
	@Autowired private AuthenticationProvider provider;

	@Autowired private UserDetailsService userDetailsService;
	
	@Autowired private AuthenticationFailureHandler failHandle;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
		auth.userDetailsService(userDetailsService);
	}
	
	protected AuthProcessingFilterImp buildAuthProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList("/signin");
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, "/home");
        AuthProcessingFilterImp filter 
            = new AuthProcessingFilterImp(matcher, this.resolver, this.failHandle);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable();
		
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/signup").permitAll()
				.antMatchers("/signin").permitAll()
		.and()
			.authorizeRequests()
				.antMatchers("/home").authenticated()
		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore(buildAuthProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
