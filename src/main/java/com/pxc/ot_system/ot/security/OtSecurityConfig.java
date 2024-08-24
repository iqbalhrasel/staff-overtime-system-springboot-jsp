package com.pxc.ot_system.ot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class OtSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails adminLoginDetails = createNewUser("admin", "admin.thai", "ADMIN");
		UserDetails userLoginDetails = createNewUser("user", "user.thai", "USER");
		
		return new InMemoryUserDetailsManager(adminLoginDetails, userLoginDetails);
	}

	private UserDetails createNewUser(String username, String password, String role) {
		UserDetails userDetails = User.builder()
				.passwordEncoder(input -> encoder().encode(input))
				.username(username)
				.password(password)
				.roles(role).build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated());
		
		http.formLogin(Customizer.withDefaults());
		
		http.logout(logout -> logout
	            .logoutUrl("/admin/logout") // This will handle logout for the given URL
	            .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET")) // Allow GET request
	            .permitAll())
			.logout(logout -> logout
	            .logoutUrl("/user/logout") // This will handle logout for the given URL
	            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "GET")) // Allow GET request
	            .permitAll());
		
		http.csrf((csrf) -> csrf.disable());
		http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()));
		
		return http.build();
	}

}
