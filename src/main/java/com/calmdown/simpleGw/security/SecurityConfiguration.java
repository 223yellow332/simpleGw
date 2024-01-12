package com.calmdown.simpleGw.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.calmdown.simpleGw.service.ShopService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
	
	private final ShopService shopService;
	
	@Value("${calmdown.simpleGw.http.auth-token-header.name}")
    private String principalRequestHeader;
 
	@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            String[] principals = principal.split(":");
            
            if (!shopService.getShopKey(principals[0]).equals(principals[1]))
                throw new BadCredentialsException("The API key was not found or not the expected value.");
                
            authentication.setAuthenticated(true);
            return authentication;
        });
 
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
//                    .authorizeRequests()
//                        .anyRequest()
//                            .authenticated()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/v1/admin/**"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/image/**")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin().disable();
        
        return http.build();
    }
}
