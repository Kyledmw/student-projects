package com.voting_app.util.cookies;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	public void addCookies(List<Cookie> cookieList, HttpServletResponse response) {
		for(Cookie cookie: cookieList) {
			response.addCookie(cookie);
		}
	}
}
