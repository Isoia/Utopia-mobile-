package view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import view.controller.Controller;

public class LoginController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 받아오기
		HttpSession session = request.getSession();
		
		// 1. 유저가 입력한 데이터 받아오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. 유저가 입력한 정보가 DB와 일치하는지 확인
		UserDAO dao = new UserDAO();
		Boolean isUser = dao.login(id, pw);
		
		if(isUser) {
			session.setAttribute("login", "ok");
			session.setAttribute("id", id);
			session.setAttribute("name", dao.getUserName(id));
			session.setAttribute("memaddr", dao.getUserAddress(id));
		}

		return "index.jsp";
	}

}