package view.post;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import dto.PostListDTO;
import view.controller.Controller;

public class GetPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오고
		HttpSession session = request.getSession();
		PostDAO dao = new PostDAO();
		
		// 1. searchword를 통해 리스트 불러오기		
		String searchword = request.getParameter("searchword");
		String btn = request.getParameter("btn");
		
		System.out.println(request.getParameter("page"));
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		Vector<PostListDTO> posts = dao.getBtnList(searchword, btn, page);
		int pageCount = (int)Math.ceil((double)dao.getPostCount() / 20);
		
		// 2. 리스트와 버튼 value를 session에 저장
		session.setAttribute("posts", posts);
		session.setAttribute("btn", btn);
		session.setAttribute("count", pageCount);
		session.setAttribute("searchword", searchword.replaceAll("#", "%23"));
		
		return "community_search.jsp?searchword=" + searchword + "&btn=" + btn + "&page=" + page;
	}

}
