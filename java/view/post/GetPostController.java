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

public class GetPostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		
		// 1. dao, dto 불러오기
		PostDAO postDao = new PostDAO();
		PostDTO postDto = new PostDTO();
		CommentDAO commentDao = new CommentDAO(); 
		Vector<CommentDTO> comments;
		int commentCount;
		
		// 2. postnum으로 post 불러오기
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		postDto = postDao.getOnePost(postnum);
		comments = commentDao.getComment(postnum);
		commentCount = commentDao.commentLength(postnum);
		
		// 3. 반환
		session.setAttribute("postnum", postnum);
		session.setAttribute("postDto", postDto);
		session.setAttribute("comments", comments);
		
		return "post.jsp";
	}

}
