package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import database.DBConnect;
import dto.CommentDTO;

public class CommentDAO {
	private DBConnect pool = new DBConnect();
	UserDAO userDAO = new UserDAO();
	Connection con = pool.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs;
	
	public int commentLength(int postnum) {				// 게시글 댓글 갯수 확인
		String sql = "select * from comment where postnum = ?";
		int row = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
				row++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public boolean insertComment(int postnum, String userid, String comcontent) {		//댓글 작성 코드
		String sql = "insert into comment values(null,?,?,?,now(),?)";
		int ok = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			String name = userDAO.getUserName(userid);
			pstmt.setInt(1, postnum);
			pstmt.setString(2, userid);
			pstmt.setString(3, name);
			pstmt.setString(4, comcontent);
			ok = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ok != 0)
			return true;
		else
			return false;
	}
	
	public Vector<CommentDTO> getComment(int postnum){		//게시글 댓글 불러오기
		int comnum;
		int postnums;
		String userid;
		String username;
		Date comdate;
		String comcontent;
		
		String sql = "select * from comment where postnum = ?";
		Vector<CommentDTO> comments = new Vector<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comnum = rs.getInt("comnum");
				postnums = rs.getInt("postnum");
				userid = rs.getString("userid");
				username = rs.getString("username");
				comdate = rs.getTimestamp("comdate");
				comcontent = rs.getString("comcontent");
				comments.add(new CommentDTO(comnum, postnums, userid, username, comdate, comcontent));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}
}
