package view.manager.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;
import view.controller.Controller;

public class AddManagerController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO dao = new ManagerDAO();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		dao.managerAdd(id, pw);
		
		return "manage-sel.do?sel=1";
	}

}
