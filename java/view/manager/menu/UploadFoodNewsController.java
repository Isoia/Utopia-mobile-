package view.manager.menu;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ManagerDAO;
import view.controller.Controller;

public class UploadFoodNewsController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {		
		ManagerDAO dao = new ManagerDAO();
		
		try {				
			String realFolder = "";
			 
			String saveFolder = "images";
			String encType = "utf-8";
			int maxSize =10*1024*1024; // 최대 업로드 5mb
		 
			ArrayList<String> arr = new ArrayList<String>();
			
			ServletContext context = request.getServletContext();
			realFolder = context.getRealPath(saveFolder);
		 
		    MultipartRequest multi = null;
	 
		    multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
	 
		    Enumeration params = multi.getParameterNames();
	 
		    while(params.hasMoreElements()) {
		        String name = (String) params.nextElement();
		        String value = multi.getParameter(name);
		        arr.add(value);
		    }
	 
		    Enumeration files = multi.getFileNames();
	 
		    while(files.hasMoreElements()) {
		        String name = (String)files.nextElement();
		        String filename = multi.getFilesystemName(name);
		        String original = multi.getOriginalFileName(name);
		        String type = multi.getContentType(name);
		        String file = multi.getFile(name).toString();

				arr.add(filename);
		    }
		    
		    dao.newsAdd(arr.get(2), new Date(new java.util.Date().getTime()), arr.get(1), arr.get(0));
		    /* dao.newsAdd(image, date, content, tags); */
		}catch (IOException ioe) {
			System.out.println(ioe);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return "manage-sel.do?sel=4";
	}

}
