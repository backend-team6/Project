package com.group.board.util;

import jakarta.servlet.http.HttpServletRequest;

public class ServletUtil {
	public static void setRequestMsgAndPath(HttpServletRequest request, String msg, String path) {
		request.setAttribute("msg", msg);
		request.setAttribute("path", path);
	}
}
