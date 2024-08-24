package com.pxc.ot_system.ot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserInfo {
	@Autowired
	private InMemoryUserDetailsManager userDetailsManager;
	
	public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }
	
	public String getUserRole() {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(getLoggedInUsername());
        return userDetails.getAuthorities().toString();
    }
}
