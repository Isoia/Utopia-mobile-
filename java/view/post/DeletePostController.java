package view.post;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDAO;
import dao.PostDAO;
import dto.CommentDTO;
import dto.PostDTO;
import view.controller.Controller;

public class DeletePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		
		// 1. dao 불러오기
		PostDAO postDao = new PostDAO();
		
		// 2. postnum으로 post 삭제
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		postDao.deletePost(postnum);
		
		return "user-mypage.do?page=myPost";
	}

}
