package ua.miratech.zhukov.controller.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

@Deprecated
@Component
public class DeleteBookFilter implements Filter {

	@Autowired
	UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//		Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
//		if (!roles.contains("ROLE_ANONYMOUS")) {
//			userService.
//
//
////			request.getSession().setAttribute("firstName", "myvalue");
//		}
//
//		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

}
