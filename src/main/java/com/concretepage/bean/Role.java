package com.concretepage.bean;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
	STAFF("staff"),
	TRAINER("trainer"),
	INSTRUCTOR("instructor"),
	MANAGER("manager"),
	SUPER_ADMIN("superadmin"),
	ADMIN("admin");

	private String authority;


	Role(String authority)
	{
		this.authority = authority;
	}

	@Override
	public String getAuthority()
	{
		return this.authority;
	}
}
