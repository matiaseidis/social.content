package com.mati.demo.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.Setter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.mati.demo.model.user.User;
import com.mati.demo.prevalence.PrevalenceModelProvider;



public class AfterAuthenticationFilter extends GenericFilterBean {
	
	@Setter private PrevalenceModelProvider prevalenceModelProvider;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getSession().getAttribute("user")==null){
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User loggedInUser = prevalenceModelProvider.loadUserByUsername(username);
			((HttpServletRequest)request).getSession().setAttribute("user", loggedInUser);
		}
	}

}
