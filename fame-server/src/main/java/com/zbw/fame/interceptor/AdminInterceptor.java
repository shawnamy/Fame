package com.zbw.fame.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbw.fame.model.Users;
import com.zbw.fame.util.ErrorCode;
import com.zbw.fame.util.FameUtil;
import com.zbw.fame.util.RestResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 拦截器
 *
 * @author zbw
 * @since 2017/10/11 14:10
 */
@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

	private static final String AUTH_URIS = "/admin";

	private static final String[] IGNORE_URIS = { "/admin/login", "/admin/logout", "/admin/register" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		String ip = FameUtil.getIp();

		log.info("用户访问地址: {}, Http类型: {}, ip地址: {}", url, request.getMethod(), ip);

		if (url.contains(AUTH_URIS)) {
			boolean auth = true;
			// 登录拦截忽略url
			for (String param : IGNORE_URIS) {
				if (StringUtils.endsWithIgnoreCase(url, param)) {
					auth = false;
				}
			}
			// 登录拦截
			if (auth) {
				Users user = FameUtil.getLoginUser();
				if (null == user) {
					// 要设置跨域，不然输出信息没有
					if (request.getHeader(HttpHeaders.ORIGIN) != null) {
						response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
								request.getHeader(HttpHeaders.ORIGIN));
						response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
						response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, DELETE");
						response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
						response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "x-requested-with");
					}
					PrintWriter out = response.getWriter();
					ObjectMapper mapper = new ObjectMapper();
					out.print(mapper.writeValueAsString(
							RestResponse.fail(ErrorCode.NOT_LOGIN.getCode(), ErrorCode.NOT_LOGIN.getMsg())));
					out.flush();
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {

	}
}
