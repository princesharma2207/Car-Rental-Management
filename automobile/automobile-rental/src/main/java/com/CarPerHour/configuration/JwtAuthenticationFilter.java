package com.CarPerHour.configuration;



 
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException; 

import jakarta.servlet.http.HttpServletRequest; 

import jakarta.servlet.http.HttpServletResponse; 

import lombok.RequiredArgsConstructor; 

import org.apache.commons.lang3.StringUtils;

import org.springframework.lang.NonNull; 

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 

import org.springframework.security.core.context.SecurityContext; 

import org.springframework.security.core.context.SecurityContextHolder; 

import org.springframework.security.core.userdetails.UserDetails; 

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; 

import org.springframework.stereotype.Component; 

import org.springframework.web.filter.OncePerRequestFilter;

import com.CarPerHour.services.jwt.UserServiceImpl;
import com.CarPerHour.utils.JwtUtil;

import java.io.IOException;
 
@Component 
 
@RequiredArgsConstructor 
 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
private final JwtUtil jwtUtil; 

private final UserServiceImpl userService;
 
@Override //overriding a method from a superclass

protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
//main part of filter logic, gets called for each incoming HTTP request
	
final String authHeader = request.getHeader("Authorization"); 
//retrieves the value of the Authorization header, contains authentication token

final String jwt; 

final String userEmail; 

if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")){

	  filterChain.doFilter(request, response); 

	  return; 

} 

	jwt =  authHeader.substring(7); userEmail = jwtUtil.extractUserName(jwt); 

	if(StringUtils.isNotEmpty(userEmail) &&  SecurityContextHolder.getContext().getAuthentication() == null) { 

		UserDetails  userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

	if(jwtUtil.isTokenValid(jwt, userDetails)) {

	  SecurityContext context =  SecurityContextHolder.createEmptyContext();

	  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 

	  authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	  context.setAuthentication(authToken);

	  SecurityContextHolder.setContext(context);

} 

} 

filterChain.doFilter(request,response);
 
}
 
 
}
