package com.concretepage.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface IInfoService {
	@PreAuthorize("hasAnyRole('admin','staff')")
	public String getMsg();
}
