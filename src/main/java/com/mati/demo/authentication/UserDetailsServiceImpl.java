package com.mati.demo.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.BaseModel;

public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String ADMIN_USER = "admin";
	private static final String ADMIN_PASS = "admin";
	
	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	
	@Setter private BaseModel baseModel;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if(username.equals("admin")){
			return admin();
		}
		return authUser(baseModel.getModel().loadUserByUsername(username));
	}

	private UserDetails admin() {
		org.springframework.security.core.userdetails.User authUser = null;
		authUser = new org.springframework.security.core.userdetails.User(
				ADMIN_USER,
				ADMIN_PASS,
				adminRoles()
				);
		return authUser;
	}


	private UserDetails authUser(User user) {
		
		if(user == null) return null;
		
		org.springframework.security.core.userdetails.User authUser = null;
		authUser = new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(),
				roles(user)
				);
		return authUser;
	}

	private Collection<? extends GrantedAuthority> roles(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	private Collection<? extends GrantedAuthority> adminRoles() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(ROLE_USER));
		authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
		return authorities;
	}
	
}
