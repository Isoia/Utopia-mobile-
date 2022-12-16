package view.user;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dao.UserDAO;
import dto.PostListDTO;
import dto.UserDTO;
import view.controller.Controller;

public class LoadMyPageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		PostDAO dao = new PostDAO();
		
		// 
		String id = session.getAttribute("id").toString();
		String page= request.getParameter("page");
		
		// 
		if(page.equals("myPost")) {
			Vector<PostListDTO> posts = dao.getMyPostList(id);
			session.setAttribute("posts", posts);
		} else if(page.equals("myInfo")) {
			UserDAO userDao = new UserDAO();
			UserDTO user = userDao.getUser(id);
			
			session.setAttribute("isPassed", false);
			session.setAttribute("user", user);
		} else {
			new UserDAO().memWithdraw(id);
			return "logout.do";
		}
		
		return "my_page.jsp?page=" + page;
	}

}
