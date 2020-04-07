package com.example.demo;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import com.example.security.handler.CustomAuthenticationSuccessHandler;

import net.lotte.chamomile.security.access.intercept.ReloadableFilterInvocationSecurityMetadataSource;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
		return http.authorizeExchange()
				.pathMatchers("/**")
					.hasRole("ADMIN")
				.and()
					.csrf()					
				.and()
					.httpBasic()
				.and()
					//.addFilterAfter(webFilter, order)
					//.addFilterAt(webFilter, order)
					//.addFilterBefore(webFilter, order)
					.formLogin()
						.loginPage("/login")
						.authenticationSuccessHandler(new CustomAuthenticationSuccessHandler())
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessHandler(logoutSuccessHandler("/login"))
				.and()
				.build();
	}
	
	@Bean 
	public ServerLogoutSuccessHandler logoutSuccessHandler(String url) {
		RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
		successHandler.setLogoutSuccessUrl(URI.create(url));
		return successHandler;
	}
//	
//	public FilterSecurityInterceptor filterSecurityInterceptor(AuthenticationManager authenticationManager
//			, AccessDecisionManager accessDecisionManager
//			, ReloadableFilterInvocationSecurityMetadataSource reloadableFilterInvocationSecurityMetadataSource){
//		FilterSecurityInterceptor customFilterSecurityInterceptor = new FilterSecurityInterceptor();
//		customFilterSecurityInterceptor.setAuthenticationManager(authenticationManager);
//		customFilterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
//		customFilterSecurityInterceptor.setSecurityMetadataSource(reloadableFilterInvocationSecurityMetadataSource);
//		customFilterSecurityInterceptor.setRejectPublicInvocations(true);										
//		return customFilterSecurityInterceptor;
//	}
	
}
