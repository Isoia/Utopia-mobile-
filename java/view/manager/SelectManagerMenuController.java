package view.manager;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDAO;
import view.controller.Controller;

public class SelectManagerMenuController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		int sel = Integer.parseInt(request.getParameter("sel"));
		
		String pageInfo = null;
		Object data = null;
		
		switch(sel) {
		case 1:
			pageInfo = "manager";
			data = new ManagerDAO().managerList();
			break;
		case 2:
			pageInfo = "user";
			data = new ManagerDAO().userList();
			break;
		case 3:
			pageInfo = "postlist";
			data = new ManagerDAO().postList();
			break;
		case 4:
			pageInfo = "news";
			data = new ManagerDAO().newsList();
			break;
		default:
			break;
		}
		
		session.setAttribute("pageInfo", pageInfo);
		session.setAttribute("data", data);
		
		return "manager_main.jsp";
	}

}
