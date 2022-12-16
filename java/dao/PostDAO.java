package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletContext;

import com.oreilly.servlet.MultipartRequest;

import database.DBConnect;
import dto.PostDTO;
import dto.PostListDTO;

public class PostDAO {

	private DBConnect pool = new DBConnect();
	Connection con = pool.getConnection();
	
	// getPostList 1번
	public Vector<PostListDTO> getBtnList(String searchValue, String type, int page){
		if(type.equals("new")) {		//최신
			return getPostList(searchValue, 1, page, false);
		} else if(type.equals("rec")) {		//추천
			return getPostList(searchValue, 2, page, false);
		} else {		//조회
			return getPostList(searchValue, 3, page, false);
		}
	}
	
	// getPostList 2번
	public Vector<PostListDTO> getPostList(String searchValue, int orderType, int page, boolean isRandom) {		//게시글 리스트 띄우기
		ArrayList<String> titleList = new ArrayList<>();
		ArrayList<String> tagList = new ArrayList<>();
		
		String[] titlesAndTags = searchValue.split(" ");
		
		for(int i = 0; i < titlesAndTags.length; i++) {
			if(titlesAndTags[i].contains("#")) {
				String tag = titlesAndTags[i].substring(1, titlesAndTags[i].length());
				tagList.add(tag);
			} else {
				titleList.add(titlesAndTags[i]);
			}
		}
		
		if(isRandom) {
			return searchWithAdder(titleList, tagList);
		} else {
			return searchWithFilter(titleList, tagList, page, orderType);
		}
		
	}
	
	// and 연산으로 필터링
	public Vector<PostListDTO> searchWithFilter(ArrayList<String> titles, ArrayList<String> tags, int page, int orderType) {
		String sql = "select post.*, count(comment.comnum) as comcount from post left outer join comment on comment.postnum = post.postnum" ;
		Vector<PostListDTO> posts = new Vector<>();
		
		if(titles.size() + tags.size() > 0) {
			sql += " where";
			
			for(String title: titles) {
				sql += " post.title like \'%" + title + "%\' and";
			}
			for(String tag: tags) {
				sql += " post.tag like \'%" + tag + "%\' and";
			}
			
			sql = sql.substring(0, sql.length() - 4);
		}
		
		sql += " group by post.postnum";
		
		switch(orderType) {
			case 1:
				sql += " order by post.postnum desc";
				break;
			case 2:
				sql += " order by comcount desc";
				break;
			case 3:
				sql += " order by post.viewscount desc";
				break;
			default:
				break;
		}
		
		sql += " limit " + ((page-1) * 20) + ", " + 20;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
					posts.add(
							new PostListDTO(
									rs.getInt("postnum"),
									rs.getString("title"),
									rs.getString("mainimage"),
									rs.getString("username"),
									rs.getString("tag"),
									rs.getInt("viewscount")
								));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public int getPostCount() {
		String sql = "select count(*) as count from post";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	// or 연산으로 추가
	public Vector<PostListDTO> searchWithAdder(ArrayList<String> titles, ArrayList<String> tags) {
		String sql = "select * from post";
		Vector<PostListDTO> posts = new Vector<>();
		int postnum;
		
		if(titles.size() + tags.size() > 0) {
			sql += " where";
			
			for(String title: titles) {
				sql += " title like \'%" + title + "%\' or";
			}
			for(String tag: tags) {
				sql += " tag like \'%" + tag + "%\' or";
			}
			
			sql = sql.substring(0, sql.length() - 3);
		}
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
					posts.add(
							new PostListDTO(
									rs.getInt("postnum"),
									rs.getString("title"),
									rs.getString("mainimage"),
									rs.getString("username"),
									rs.getString("tag"),
									rs.getInt("viewscount")
								));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	// 비수정 코드
	public PostDTO getOnePost(int postnum){		//게시글 불러오기
		PostDTO post = null;
		ArrayList<String> tag = new ArrayList<>();
		ArrayList<String> imagelist = new ArrayList<>();
		
		try {
			String sql = "select * from post where postnum = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);			
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sql = "update post set viewscount = ? where postnum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("viewscount") + 1);
				pstmt.setInt(2, postnum);
				pstmt.executeUpdate();
				
				for(String s : rs.getString("tag").split(",")) 
					tag.add(s.toString());
				for(String i: rs.getString("imagelist").split(","))
					imagelist.add(i.toString());
				
				post = new PostDTO(
						rs.getInt("postnum"),
						rs.getDate("nowdate"),
						rs.getInt("viewscount") + 1,
						rs.getString("username"),
						rs.getString("userid"),
						rs.getString("title"),
						rs.getString("content").split("&&"),
						tag,
						rs.getString("mainimage"),
						imagelist,
						rs.getString("ingredient").split(",")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return post;
	}
	
	public boolean writePost(String name,String id,String title,String[] content,ArrayList<String> tag,String mainimage,
								ArrayList<String> imagelist,String[] ingredient) {
		int row = 0;
		//? 순서 : 이름,아이디,제목,내용,태그,썸네일,이미지리스트,재료 마지막은 칼로리
		String sql = "insert into post values(null,now(),0,?,?,?,?,?,?,?,?)";	
		String resultContent = null;
		String[] shopTag;
		String taglist = "";
		String sqlimageslist = "";
		String sqlingredientlist = "";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//1 이름
			pstmt.setString(1, name);
			//2 아이디
			pstmt.setString(2, id);
			//3 제목
			pstmt.setString(3, title);
			//4 내용
			for(int i=0; i<content.length; i++) {
				resultContent += content[i].toString() + "&&";	// &&는 여러 내용을 구분하기 위한 구분자
			}
			resultContent = resultContent.substring(0,resultContent.length()-2);	//문장 끝에 구분자제거
			if(resultContent.substring(0, 4).equals("null"))
				resultContent = resultContent.substring(4,resultContent.length());
			pstmt.setString(4, resultContent);
			//5 태그
			shopTag = tag.get(0).split("#");		//#별로 분리
			
			for(int i=0; i<shopTag.length; i++) {	//#로 분리시 #태그1#태그2 #기준 앞뒤로 나누어 ""도 추가되어 삭제작업
				if(shopTag[i].equals("")) {				// 공백일시
					continue;
				}else {
					if(i != shopTag.length-1) {
						while(shopTag[i].substring(shopTag[i].length()-1).equals(" ")) {		//태그가 #태그1(공백) 일 경우
							shopTag[i] = shopTag[i].substring(0, shopTag[i].length()-1);
						}
						taglist += shopTag[i].toString()+",";	//끝이 아니면 구분자 추가
					}
					else {
						while(shopTag[i].substring(shopTag[i].length()-1).equals(" ")) {
							shopTag[i] = shopTag[i].substring(0, shopTag[i].length()-1);
						}
						taglist += shopTag[i].toString();		//끝이면 추가 x
					}
				}
			}
			pstmt.setString(5, taglist);
			//6 썸네일
			pstmt.setString(6, mainimage);
			//7 이미지리스트
			for(int i=0; i<imagelist.size(); i++) {
				if(i != imagelist.size()-1)
					sqlimageslist += imagelist.get(i) + ",";	//이미지 구분자 주기 위한 ,
				else
					sqlimageslist += imagelist.get(i);			// 마지막이면 x
			}
			pstmt.setString(7, sqlimageslist);
			//8 재료
			for(int i=0; i<ingredient.length; i++) {
				if(i != ingredient.length -1)
					sqlingredientlist += ingredient[i].toString()+",";
				else
					sqlingredientlist += ingredient[i].toString();
			}
			pstmt.setString(8, sqlingredientlist);
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(row != 0)
			return true;
		else
			return false;
	}
	
	
	
	public Vector<PostListDTO> getNewList(){			//최신 게시글
		String sql ="select * from post where timediff(nowdate,'2020-05-20 13:00:00') order by nowdate desc limit 5";
		Vector<PostListDTO> posts = new Vector<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				posts.add(
						new PostListDTO(
								rs.getInt("postnum"),
								rs.getString("title"),
								rs.getString("mainimage"),
								rs.getString("username"),
								rs.getString("tag"),
								rs.getInt("viewscount"))
						);
			}
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public Vector<PostListDTO> getMonthBestList(){		//이달의 베스트 게시물 3 선정코드
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		Vector<PostListDTO> posts;
		String month = now.getMonth().toString();
		String sql = "select * from post where date_format(nowdate, '%Y') = ? and date_format(nowdate, '%M') = ? order by viewscount desc limit 3";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.setString(2, month);
			ResultSet rs = pstmt.executeQuery();
			posts = new Vector<>();
			while(rs.next()) {
				posts.add(
						new PostListDTO(
								rs.getInt("postnum"),
								rs.getString("title"),
								rs.getString("mainimage"),
								rs.getString("username"),
								rs.getString("tag"),
								0)
						);
			}
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<String> getTagList(int postnum){				// 태그만 리스트로 모아 값전달하기 위한 함수
		Vector<String> taglist = new Vector<>();
		String sql = "select * from post where postnum = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);		// 특정 단어에 대한 태그만 가져올거니까 %% 구분자 필요 x
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String[] tags = rs.getString("tag").split(",");
				
				for(String t: tags) {
					taglist.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taglist;
	}
	
	public Vector<PostListDTO> getMyPostList(String id) {
		Vector<PostListDTO> posts = new Vector<>();
		
		String sql = "select * from post where userid = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				posts.add(
						new PostListDTO(
								rs.getInt("postnum"),
								rs.getString("title"),
								rs.getString("mainimage"),
								rs.getString("username"),
								rs.getString("tag"),
								rs.getInt("viewscount")
							));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public void deletePost(int postnum) {
		String sql = "delete from post where postnum = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updatePost(String name,String id,String title,String[] content,ArrayList<String> tag,String mainimage,
			ArrayList<String> imagelist,String[] ingredient, int postnum) {
		int row = 0;
		//? 순서 : 이름,아이디,제목,내용,태그,썸네일,이미지리스트,재료 마지막은 칼로리
		String sql = "update post set username = ?, userid = ?, title = ?, content = ?, tag = ?, mainimage = ?, imagelist = ?, ingredient = ? where postnum = ?";	
		String resultContent = null;
		String[] shopTag;
		String taglist = "";
		String sqlimageslist = "";
		String sqlingredientlist = "";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//1 이름
			pstmt.setString(1, name);
			//2 아이디
			pstmt.setString(2, id);
			//3 제목
			pstmt.setString(3, title);
			//4 내용
			for(int i=0; i<content.length; i++) {
				resultContent += content[i].toString() + "&&";	// &&는 여러 내용을 구분하기 위한 구분자
				}
			resultContent = resultContent.substring(0,resultContent.length()-2);	//문장 끝에 구분자제거
			if(resultContent.substring(0, 4).equals("null"))
				resultContent = resultContent.substring(4,resultContent.length());
			pstmt.setString(4, resultContent);
			//5 태그
			shopTag = tag.get(0).split("#");		//#별로 분리
			
			for(int i=0; i<shopTag.length; i++) {	//#로 분리시 #태그1#태그2 #기준 앞뒤로 나누어 ""도 추가되어 삭제작업
				if(shopTag[i].equals("")) {				// 공백일시
				continue;
				}else {
				if(i != shopTag.length-1) {
					while(shopTag[i].substring(shopTag[i].length()-1).equals(" ")) {		//태그가 #태그1(공백) 일 경우
						shopTag[i] = shopTag[i].substring(0, shopTag[i].length()-1);
					}
					taglist += shopTag[i].toString()+",";	//끝이 아니면 구분자 추가
				}
				else {
					while(shopTag[i].substring(shopTag[i].length()-1).equals(" ")) {
						shopTag[i] = shopTag[i].substring(0, shopTag[i].length()-1);
					}
					taglist += shopTag[i].toString();		//끝이면 추가 x
					}
				}
			}
			pstmt.setString(5, taglist);
			//6 썸네일
			pstmt.setString(6, mainimage);
			//7 이미지리스트
			for(int i=0; i<imagelist.size(); i++) {
				if(i != imagelist.size()-1)
					sqlimageslist += imagelist.get(i) + ",";	//이미지 구분자 주기 위한 ,
				else
					sqlimageslist += imagelist.get(i);			// 마지막이면 x
				}
			pstmt.setString(7, sqlimageslist);
			//8 재료
			for(int i=0; i<ingredient.length; i++) {
				if(i != ingredient.length -1)
					sqlingredientlist += ingredient[i].toString()+",";
				else
					sqlingredientlist += ingredient[i].toString();
				}
			pstmt.setString(8, sqlingredientlist);
			pstmt.setInt(9, postnum);
			row = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(row != 0)
			return true;
		else
			return false;
	}
}
