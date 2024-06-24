package com.task.uploaddocument.helper;

import jakarta.servlet.http.HttpServletRequest;

public class SessionHelper {

	public static boolean isSessionValid(HttpServletRequest request) {
        return (request.getSession(false) != null && request.getSession().getAttribute("userId") != null);
    }

	
}
