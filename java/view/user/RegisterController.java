package view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;
import view.controller.Controller;

public class RegisterController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		
		// 1. 회원가입 정보 불러오기
		UserDTO dto = new UserDTO(
					request.getParameter("memid"),
					request.getParameter("mempw"),
					request.getParameter("memname"),
					request.getParameter("memaddr"),
					request.getParameter("memphone"),
					request.getParameter("mememail")
				);
		
		// 2. 회원가입 확인
		UserDAO dao = new UserDAO();
		
		// 1 == 성공, 0 == 실패, -1 == 중복아이디로 인한 취소
		int isRegistered = dao.memSignUp(dto);
		session.setAttribute("RegisterStatus", isRegistered);
		
		return "index.jsp";
	}

}
