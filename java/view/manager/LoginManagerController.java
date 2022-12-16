package view.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;
import dao.UserDAO;
import view.controller.Controller;

public class LoginManagerController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO dao = new ManagerDAO();
		
		String id = request.getParameter("id");
		String password = request.getParameter("pw");

		if(dao.managerLogin(id, password)) {
			return "manage-sel.do?sel=1";
		} else {
			return "manager_login.jsp";
		}
	}

}
