package view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;
import view.controller.Controller;

public class UpdateUserController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 객체 불러오기
		HttpSession session = request.getSession();
		
		// 파라미터
		String id = session.getAttribute("id").toString();
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		UserDAO userDao = new UserDAO();
		UserDTO userDto = new UserDTO(id, pw, name, addr, phone, email);
		
		userDao.memUpdate(userDto);
		
		session.setAttribute("name", name);
		session.setAttribute("user", userDto);
		session.setAttribute("memaddr", addr);
		
		return "user-mypage.do?page=myPost";
	}

}
