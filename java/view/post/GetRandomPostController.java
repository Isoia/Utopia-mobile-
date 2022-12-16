package view.post;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.WeatherApi;
import dao.PostDAO;
import dto.PostDTO;
import dto.PostListDTO;
import view.controller.Controller;

public class GetRandomPostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session
		HttpSession session = request.getSession();
		
		// 1. Session으로 SKY, T1H 받아오기
		PostDAO dao = new PostDAO();
		
		WeatherApi.SKY sky = (WeatherApi.SKY)session.getAttribute("sky");
		WeatherApi.T1H t1h = (WeatherApi.T1H)session.getAttribute("t1h");

		String searchWord = "";
		
		if(sky == WeatherApi.SKY.Sunny) {
			searchWord += "#맑은날 ";
		} else if(sky == WeatherApi.SKY.Rainy) {
			searchWord += "#비오는날 ";
		} else if(sky == WeatherApi.SKY.Cloudy) {
			searchWord += "#구름낀날 ";
		} else if(sky == WeatherApi.SKY.Foggy) {
			searchWord += "#흐린날 ";
		} else {}
		
		if(t1h == WeatherApi.T1H.Cold) {
			searchWord += "#추운날 ";
		} else if(t1h == WeatherApi.T1H.Cool) {
			searchWord += "#쌀쌀한날 ";
		} else if(t1h == WeatherApi.T1H.Warm) {
			searchWord += "#따듯한날 ";
		} else if(t1h == WeatherApi.T1H.Hot) {
			searchWord += "#더운날 ";
		}

		Vector<PostListDTO> postlist = dao.getPostList(searchWord, 1, 0, true);
		
		if(postlist.size() > 0) {
			int postnum = postlist.get((int)(Math.random() * postlist.size())).getPostnum();
			
			return "post.do?postnum=" + postnum;
		} else {
			return "index.jsp";
		}
		
	}

}
