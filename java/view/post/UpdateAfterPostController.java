package view.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PostDAO;
import dto.PostDTO;
import view.controller.Controller;

public class UpdateAfterPostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// session 불러오기
		HttpSession session = request.getSession();
        try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 1. Post 관련 변수 선언
		PostDAO dao = new PostDAO();
		
		String username;					//사용자이름
		String userid;						//사용자ID
		String title;						//제목
		String[] content;					//내용
		ArrayList<String> contentList;		//arraylist에 담을 내용
		ArrayList<String> tag;				//태그
		String mainimage;					//대표이미지
		
		ArrayList<String> imagelist;		//이미지리스트
		HashMap<String, String> imageSet;
		ArrayList<String> hiddenImagelist;	//히든 이미지리스트
		HashMap<String, String> hiddenImageSet; // 이미지 셋
		
		String[] ingredient;				//재료,용량
		String postnumSt = String.valueOf(session.getAttribute("postnum"));
		int postnum = Integer.parseInt(postnumSt);
		PostDTO postDTO = dao.getOnePost(postnum);
		
		// 2. Multipart로 파라미터 및 사진 불러오기
		String uploadDir = this.getClass().getResource("").getPath();
		// 수정됨 (각자 환경에 맞춰 다시 주소 수정)
		uploadDir = uploadDir.substring(1,uploadDir.indexOf(".metadata"))+"Utopia/src/main/webapp/images";
		 
		String saveFolder = "images"; // out폴더에 fileSave 폴더 생성
		String encType = "utf-8";
		int maxSize = 10*1024*1024; // 최대 업로드 5mb
	 
		ArrayList<String> arr = new ArrayList<String>();
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = null;
		tag = new ArrayList<>();
		
		imageSet = new HashMap<>();
		imagelist = new ArrayList<>();
		hiddenImageSet = new HashMap<>();
		hiddenImagelist = new ArrayList<>();

		try{
			multi = new MultipartRequest(request,uploadDir,maxSize,encType,new DefaultFileRenamePolicy());
			username = (String)session.getAttribute("name");
			userid = (String)session.getAttribute("id");
			title = multi.getParameter("recipe-title");
			
			content = multi.getParameterValues("recipe-item-step");
			
			tag.add(multi.getParameter("recipe-tag"));
			if(multi.getFilesystemName("recipe-thumbnail") != null)
				mainimage = multi.getFilesystemName("recipe-thumbnail");
			else {
				mainimage = postDTO.getMainimage();
			}
			
			// hiddenImage 정렬 및 ArrayList화
			Enumeration<?> hiddenImages = multi.getParameterNames();
			while(hiddenImages.hasMoreElements()) {
				String hiddenimage = (String)hiddenImages.nextElement();
				
				if(hiddenimage.contains("recipe-image-hidden")) {
					hiddenImageSet.put(hiddenimage, multi.getParameter(hiddenimage));
				}
			}
			
			for(int img = 1; img <= hiddenImageSet.size(); img++) {
				hiddenImagelist.add(hiddenImageSet.get("recipe-image-hidden" + img));
			}
			
			// inputImage 정렬 및 ArrayList화
			Enumeration<?> files = multi.getFileNames();
			
			while(files.hasMoreElements()) { 
				String element = (String)files.nextElement(); 
				
				if(element.contains("recipe-item-image")){
					imageSet.put(element, multi.getFilesystemName(element));
				}
			}
			
			for(int img = 1; img <= imageSet.size(); img++) {
				String imageStr = imageSet.get("recipe-item-image" + img);
				
				if(imageStr == null) {
					imageStr = "이미지없음";
				}
				
				imagelist.add(imageStr);
			}
			
			// image 비교
			for(int img = 0; img < imagelist.size(); img++) {
				String image = imagelist.get(img);
				String hiddenImage = hiddenImagelist.get(img);
				
				if(image.equals("이미지없음") && !hiddenImage.equals("이미지없음")) {
					imagelist.set(img, hiddenImage);
				}
			}
			
			ingredient = multi.getParameterValues("recipe-item-material-text");
			
	        boolean postTF = dao.updatePost(username, userid, title, content, tag, mainimage, imagelist, ingredient, postnum);
	        
	        if(postTF){
	        	return "user-mypage.do?page=myPost";
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return "index.jsp";
	}	
}
