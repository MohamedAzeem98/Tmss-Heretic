package com.travelmanagementservice.tmss.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailService;
	
	
	public JwtFilter(JwtUtil jwtUtil,CustomUserDetailsService userDetailsService) {
		this.jwtUtil=jwtUtil;
		this.userDetailService=userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader=request.getHeader("Authorization");
		
		String username=null;
		String jwtToken=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken=authorizationHeader.substring(7);
			username=jwtUtil.extractUsername(jwtToken);
		}
		UserDetails userDetails=null;

		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			userDetails=userDetailService.loadUserByUsername(username);
		}
		
			if(userDetails!=null && jwtUtil.validateToken(jwtToken, username)) {
				UsernamePasswordAuthenticationToken authenticationToken =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);

	}

}
