package com.travelmanagementservice.tmss.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import lombok.var;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;
	
	   public SecurityConfig(CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
	        this.userDetailsService = userDetailsService;
	        this.jwtUtil = jwtUtil;
	    }
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
			 .authorizeHttpRequests(auth -> auth
			 .requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs","/v3/api-docs/**","/v3/api-docs.yaml").permitAll()
             .requestMatchers("/auth/**","/user/register", "/user/login","/user/me/**").permitAll()
		     .requestMatchers("/travel/submit").hasRole("EMPLOYEE")
		     .requestMatchers("/travel/myrequests").hasRole("EMPLOYEE")
		     .requestMatchers("/travel/pending/**").hasRole("MANAGER")
		     .requestMatchers("/travel/approve/**").hasRole("MANAGER")
		     .requestMatchers("/travel/decline/**").hasRole("MANAGER")

		     .requestMatchers("/travel/admin/**").hasRole("ADMIN")
		     
		     .requestMatchers("/reimbursement/submit").hasRole("EMPLOYEE")
		     .requestMatchers("/reimbursement/myrequest").hasRole("EMPLOYEE")
		     .requestMatchers("/reimbursement/pending").hasRole("MANAGER")
		     .requestMatchers("/reimbursement/approve/**").hasRole("MANAGER")
		     .requestMatchers("/reimbursement/decline/**").hasRole("MANAGER")

		             .anyRequest().authenticated()
		             
		             )
		             .addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class);
		
		      return http.build();
	}
	
	 @Bean
	    public JwtFilter jwtFilter() {
	        return new JwtFilter(jwtUtil, userDetailsService);
	    }

	 @Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	   return authenticationConfiguration.getAuthenticationManager();     
	 }
	
	
	
	

}
