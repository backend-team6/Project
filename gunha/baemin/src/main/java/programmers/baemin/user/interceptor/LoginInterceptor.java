package programmers.baemin.user.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		String requestURI = request.getRequestURI();
		log.info("LoginInterceptor {}", requestURI);

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("login") == null) {
			log.info("Not Login User");
			response.sendRedirect("/user/login?redirectURL=" + requestURI);
			return false;
		}
		return true;
	}
}
