package com.mati.demo.authentication;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider{
	
	@Setter @Getter private UserDetailsService userDetailsService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
		
		if(userDetails == null){
			throw new AuthenticationCredentialsNotFoundException("Invalid username / password");
		}
		
		if(userDetails.getPassword().equals(authentication.getCredentials().toString())){
			return new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), 
					userDetails.getPassword(), 
					userDetails.getAuthorities());
		}
		throw new AuthenticationCredentialsNotFoundException("Invalid username / password");
//		return null;
	}

	public boolean supports(Class<?> authentication) {
		boolean supported = authentication.equals(UsernamePasswordAuthenticationToken.class); 
		return supported;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println();
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
