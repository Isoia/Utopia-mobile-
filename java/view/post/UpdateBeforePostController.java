package view.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dto.PostDTO;
import view.controller.Controller;

public class UpdateBeforePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		

		PostDAO postDao = new PostDAO();
		PostDTO postDto = new PostDTO();
		

		int postnum = Integer.parseInt(request.getParameter("postnum"));
		postDto = postDao.getOnePost(postnum);
		int count = postDto.getContent().length;
		String jaeryolist = String.join(",", postDto.getIngredient());
		String imagelist = String.join(",", postDto.getImagelist());
		String contentslist = String.join(",", postDto.getContent());

		session.setAttribute("postnum", postnum);
		session.setAttribute("postDto", postDto);	
		session.setAttribute("stepcount", count);
		session.setAttribute("jaeryolist", jaeryolist);
		session.setAttribute("imagelist", imagelist);
		session.setAttribute("contentslist", contentslist);
		
		return "update_post.jsp?stepcount="+count;
	}

}
