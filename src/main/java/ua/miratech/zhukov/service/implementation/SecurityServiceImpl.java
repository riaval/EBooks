package ua.miratech.zhukov.service.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.service.SecurityService;

@Component
public class SecurityServiceImpl implements SecurityService {

	public String getUserEmail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

}
