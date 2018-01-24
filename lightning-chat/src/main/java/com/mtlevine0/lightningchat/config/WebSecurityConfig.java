package com.mtlevine0.lightningchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mtlevine0.lightningchat.filter.JWTAuthenticationFilter;
import com.mtlevine0.lightningchat.filter.JWTLoginFilter;
import com.mtlevine0.lightningchat.service.DefaultUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DefaultUserDetailsService userDetailsService;
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.exceptionHandling()
        		.authenticationEntryPoint(restAuthenticationEntryPoint)
        	.and()
            	.authorizeRequests()
	            	.antMatchers("/console/**").permitAll()
	                .antMatchers("/**").authenticated()
	                .anyRequest().authenticated()
            .and()
            	.formLogin()
                	.loginPage("/api/v1/login")
                	.permitAll()
            .and()
            	.addFilterBefore(new JWTLoginFilter("/api/v1/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            	.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .logout()
                .permitAll();
        
    	http
    		.csrf().disable()
    		.headers().frameOptions().disable().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



    }
    
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
//        auth
//        .inMemoryAuthentication()
//            .withUser("mtlevine0").password("test123").roles("USER","ADMIN")
//            .and()
//            .withUser("user").password("test123").roles("USER");
		
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("mtlevine0").password("test123").roles("USER","ADMIN")
//                .and()
//                .withUser("user").password("test123").roles("USER");
//    }
}
