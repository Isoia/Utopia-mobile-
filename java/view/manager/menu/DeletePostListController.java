package view.manager.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;
import view.controller.Controller;

public class DeletePostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO dao = new ManagerDAO();
		
		try {
			String[] id = request.getParameterValues("id");
			
			for(String i: id) {
				dao.postDelete(Integer.parseInt(i));
			}
		} catch(Exception e) {}
		
		return "manage-sel.do?sel=3";
	}

}
