package view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import view.controller.Controller;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		
		// 1. 로그인 상태(ok)와 주소 제거
		session.removeAttribute("login");
		session.removeAttribute("memaddr");
		
		return "index.jsp";
	}

}
