package view.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDAO;
import view.controller.Controller;

public class WriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
		
		// 1. 파라미터 받아오기
		CommentDAO dao = new CommentDAO();
		
		String id = (String)session.getAttribute("id");
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		String comment = (String)request.getParameter("comment");
		
		// 2. 댓글 삽입
		dao.insertComment(postnum, id, comment);
		
		// url == 게시글 번호
		return "post.do?postnum=" + postnum;
	}

}
