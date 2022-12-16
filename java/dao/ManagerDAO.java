package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import database.DBConnect;
import dto.IngredientDTO;
import dto.ManagerDTO;
import dto.NewsDTO;
import dto.PostDTO2;
import dto.TagDTO;
import dto.UserDTO;

/*
 * 1. 관리자 관리 - 관리자 목록, 삭제, 추가
 * 2. 유저 관리 - 유저 목록, 삭제
 * 3. 게시글 관리 - 게시글 목록, 삭제
 * 4. 재료 관리 - 재료 목록, 추가, 삭제, 수정
 * 5. 태그 관리 - 태그 목록, 추가, 삭제, 수정
 * 6. 식단뉴스 - 식단뉴스 목록, 추가, 삭제, 수정
 * */
public class ManagerDAO {
	private String managerId = "";
	
	private DBConnect pool = new DBConnect();
	private Connection con = pool.getConnection();
	
	// 1. 관리자 로그인, 추가, 삭제, 목록
	
	public boolean managerLogin(String id, String password) {
		String sql = "select * from manager where manager_id = ? and manager_pw = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			// 로그인 성공
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean managerAdd(String id, String password) {
		String sql = "insert into manager values(?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			// 추가 성공
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean managerDelete(String id) {
		String sql = "delete from manager where manager_id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			// 추가 성공
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<ManagerDTO> managerList() {
		String sql = "select * from manager";
		Vector<ManagerDTO> dtos = new Vector<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new ManagerDTO(rs.getString("manager_id"), rs.getString("manager_pw")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	// 2. 유저 목록, 삭제
	
	public Vector<UserDTO> userList() {
		String sql = "select * from user";
		Vector<UserDTO> dtos = new Vector<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new UserDTO(
						rs.getString("memid"),
						rs.getString("mempw"),
						rs.getString("memname"),
						rs.getString("memaddr"),
						rs.getString("memphone"),
						rs.getString("mememail")
						)
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean userDelete(String id) {
		String sql = "delete from user where memid = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 3. 게시글 목록, 게시글 삭제
	
	// 제목과 이름으로 검색
	public Vector<PostDTO2> postList() {
		String sql = "select * from post";
		Vector<PostDTO2> dtos = new Vector<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new PostDTO2(
							rs.getInt("postnum"),
							rs.getDate("nowdate"),
							rs.getInt("viewscount"),
							rs.getString("username"),
							rs.getString("userid"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getString("tag"),
							rs.getString("mainimage"),
							rs.getString("imagelist")
						)
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean postDelete(int id) {
		String sql = "delete from post where postnum = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 4. 재료 목록, 추가, 삭제, 수정
	public ArrayList<IngredientDTO> ingredientList(String name) {
		String sql = "select * from ingredient where ingredientName like ?";
		ArrayList<IngredientDTO> dtos = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			String paramName = "%";
			
			if(name != null) paramName += name + "%";
			
			pstmt.setString(1, paramName);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new IngredientDTO(
							rs.getInt("ingredientId"),
							rs.getString("ingredientName"),
							rs.getInt("ingredientCal"),
							rs.getInt("ingredientStandardCal")
						)
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean ingredientAdd(String name, int cal, int standardCal) {
		String sql = "insert into ingredient(ingredientName, ingredientCal, ingredientStandardCal) values (?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, cal);
			pstmt.setInt(3, standardCal);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean ingredientDelete(int id) {
		String sql = "delete from ingredient where ingredientId = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean ingredientUpdate(int id, String name, int cal, int standardCal) {
		String sql = "update ingredient set ingredientName = ?, ingredientCal = ?, ingredientStandardCal = ? where ingredientId = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, cal);
			pstmt.setInt(3, standardCal);
			pstmt.setInt(4, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 5. 태그 관리 - 태그 목록, 추가, 삭제, 수정
	public ArrayList<TagDTO> tagList(String name) {
		String sql = "select * from tag where tagname like ?";
		ArrayList<TagDTO> dtos = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			String paramName = "%";
			
			if(name != null) paramName += name + "%";
			
			pstmt.setString(1, paramName);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new TagDTO(
							rs.getInt("tagnum"),
							rs.getString("tagname")
						)
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean tagAdd(String name) {
		String sql = "insert into tag (tagname) values (?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean tagDelete(int id) {
		String sql = "delete from tag where tagnum = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean tagUpdate(int id, String name) {
		String sql = "update tag set tagname = ? where tagnum = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 6. 식단뉴스 - 최근, 목록, 추가, 삭제, 수정
	public NewsDTO newsRecent() {
		String sql = "select * from news order by newsDate DESC limit 1";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return new NewsDTO(
						rs.getInt("newsId"),
						rs.getString("newsImage"),
						rs.getDate("newsDate"),
						rs.getString("newsContent"),
						rs.getString("newsTags")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<NewsDTO> newsList() {
		String sql = "select * from news";
		Vector<NewsDTO> dtos = new Vector<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dtos.add(new NewsDTO(
							rs.getInt("newsId"),
							rs.getString("newsImage"),
							rs.getDate("newsDate"),
							rs.getString("newsContent"),
							rs.getString("newsTags")
						)
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean newsAdd(String image, Date date, String content, String tags) {
		String sql = "insert into news(newsImage, newsDate, newsContent, newsTags) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, image);
			pstmt.setDate(2, date);
			pstmt.setString(3, content);
			pstmt.setString(4, tags);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean newsDelete(int id) {
		String sql = "delete from news where newsId = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean newsUpdate(int id, String image, Date date, String content, String tags) {
		String sql = "update news set newsImage = ?, newsDate = ?, newsContent = ?, newsTags = ? where newsId = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, image);
			pstmt.setDate(2, date);
			pstmt.setString(3, content);
			pstmt.setString(4, tags);
			pstmt.setInt(5, id);
			
			if(pstmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
