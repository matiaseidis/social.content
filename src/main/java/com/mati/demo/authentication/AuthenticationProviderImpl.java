package com.mati.demo.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mati.demo.prevalence.PrevalenceModelProvider;

public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider{
	
	@Setter @Getter private UserDetailsService userDetailsService;
//	@Setter @Getter private PrevalenceModelProvider prevalenceModelProvider;
	
	private @Autowired HttpServletRequest request;
	

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
		
		if(userDetails.getPassword().equals(authentication.getCredentials().toString())){
//			HttpSession session = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
//			session.setAttribute("user", prevalenceModelProvider.loadUserByUsername(userDetails.getUsername()));
			return new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), 
					userDetails.getPassword(), 
					userDetails.getAuthorities());
		}
		return null;
	}

	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
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
