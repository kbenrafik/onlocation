package ma.onlocation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthentificationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		// Get the role of logged in user
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String role = authentication.getAuthorities().toString();

		String targetUrl = "";

		if (role.contains(ControllersConstants.ROLE2)) {
			targetUrl = "/dashboard-manager";
		} else if (role.contains(ControllersConstants.ROLE1)) {
			targetUrl = "/dashboard";
		}

		return targetUrl;
	}

}
