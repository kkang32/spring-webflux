package com.example.demo;

//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
public class SecurityConfig{
	
	
	//@Bean
	//public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
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
		
	
	
	
//	http
//	      .authorizeExchange()
//	      .anyExchange()
//	      .authenticated()
//	      .and()
//	      .httpBasic()
//	      .and()
//	      .formLogin();
//	    return http.build();
//	}




	
//	@Bean 
//	public ServerLogoutSuccessHandler logoutSuccessHandler(String url) {
//		RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
//		successHandler.setLogoutSuccessUrl(URI.create(url));
//		return successHandler;
//	}
	
	/**
	 * 기본 security설정에 
	 * AbstractUserDetailsReactiveAuthenticationManager -> UserDetailsRepositoryReactiveAuthenticationManager 에서 인증을 수행하고 있고
	 * UserDetailsRepositoryReactiveAuthenticationManager에서는  retrieveUser 메서드를 통해 사용자 정보를 얻어온다.
	 * 해당 메서드에서는 ReactiveUserDetailsService 인터페이스에서 사용자 정보를 얻어오게 되어있으며 
	 * 기본적으로 해당 인터페이스 구현체는 MapReactiveUserDetailsService 밖에 없다.
	 * 해당 메서드에서 사용자를 조회해오면 되겠다.
	 * @return
	 */
	//@Bean
//	public MapReactiveUserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("test")
//				.password("1111")
//				.roles("ADMIN")
//				.build();
//		
//		return new MapReactiveUserDetailsService(user);
//						
//	}

	
	
	
	
}
