package view.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDAO;
import dao.UserDAO;
import view.controller.Controller;

public class MoveToManageMenu implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. 유저가 입력한 데이터 받아오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. 유저가 입력한 정보가 DB와 일치하는지 확인
		ManagerDAO dao = new ManagerDAO();
		Boolean isUser = dao.managerLogin(id, pw);
		
		if(isUser) {
			return "manager_main.jsp";
		} else {
			return "manager_login.jsp";
		}
		
		
	}

}
