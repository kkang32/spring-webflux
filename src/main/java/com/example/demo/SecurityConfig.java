package com.example.demo;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import com.example.security.handler.CustomAuthenticationSuccessHandler;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig{
	
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
//		return http.authorizeExchange()
//				.pathMatchers("/**")
//					.hasRole("ADMIN")
//				.and()
//					.csrf()					
//				.and()
//					.httpBasic()
//				.and()
//					//.addFilterAfter(webFilter, order)
//					//.addFilterAt(webFilter, order)
//					//.addFilterBefore(webFilter, order)
//					.formLogin()
//						.loginPage("/login")
//						//.authenticationSuccessHandler(new CustomAuthenticationSuccessHandler())
//				.and()
//				.logout()
//					.logoutUrl("/logout")
//					.logoutSuccessHandler(logoutSuccessHandler("/login"))
//				.and()
//				.build();
		http
	      .authorizeExchange()
	      .anyExchange()
	      .authenticated()
	      .and()
	      .httpBasic()
	      .and()
	      .formLogin();
	    return http.build();
	}
	
//	@Bean 
//	public ServerLogoutSuccessHandler logoutSuccessHandler(String url) {
//		RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
//		successHandler.setLogoutSuccessUrl(URI.create(url));
//		return successHandler;
//	}
	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("test")
				.password("1111")
				.roles("ADMIN")
				.build();
		
		return new MapReactiveUserDetailsService(user);
						
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
