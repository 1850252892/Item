package security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandle implements AuthenticationSuccessHandler, InitializingBean {
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authen)
			throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		String targetUrl = "/mainPage";

		if (savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		response.sendRedirect("/loginSuccess?url=" + targetUrl);

	}

	@Override
	public void afterPropertiesSet() {
	}

}
