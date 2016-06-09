package com.concretepage.service;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.concretepage.bean.Role;
import com.concretepage.bean.UserModel;
import com.concretepage.dao.UserDAO;
@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserModel userInfo = userDAO.getUserCredsByName(username);
		TreeSet<String> roles = userDAO.getUserRoles(username);
		for (String role : roles) {
			Role r = getRole(role);
			if (r != null) {
				userInfo.addRole(r);
			}
		}
		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), 
				userInfo.getPassword(), userInfo.getAuthorities());
		return userDetails;
	}

	private Role getRole(String role) {
		for (Role r : Role.values()) {
			if (r.getAuthority().equals(role)) {
				return r;
			}
		}
		return null;
	}
}
