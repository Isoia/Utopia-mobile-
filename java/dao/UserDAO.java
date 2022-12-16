package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DBConnect;
import dto.UserDTO;

public class UserDAO {
	private DBConnect pool = new DBConnect();
	Connection con = pool.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs;
	
	public UserDTO getUser(String id) {
		String sql = "select * from user where memid = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return new UserDTO(
							rs.getString("memid"),
							rs.getString("mempw"),
							rs.getString("memname"),
							rs.getString("memaddr"),
							rs.getString("memphone"),
							rs.getString("mememail")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getUserAddress(String id) {
		String sql = "select memaddr from user where memid = ?";
		String addr = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				addr = rs.getString("memaddr");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addr;
	}
	
	public String getUserName(String id) {
		String sql = "select memname from user where memid = ?";
		String name = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
				name = rs.getString("memname");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public boolean login(String id, String pw) {
		String sql = "select memid,mempw from user where memid = ? and mempw = ?";
		boolean checkId = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			checkId = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return checkId;
	}
	
	public int memSignUp(UserDTO user) {
		String sql = "select memid from user";
		Vector<String> idList = new Vector<>(); 
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				idList.add(rs.getString("memid"));
			}
			
			for(int i=0; i<idList.size(); i++) {
				if(user.getMemid().equals(idList.get(i).toString()))
					return -1;
			}
			
			sql = "insert into user values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getMemid());
			pstmt.setString(2, user.getMempw());
			pstmt.setString(3, user.getMemname());
			pstmt.setString(4, user.getMemaddr());
			pstmt.setString(5, user.getMemphone());
			pstmt.setString(6, user.getMememail());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void memWithdraw(String memid) {
		String sql = "delete from user where memid = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memid);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void memUpdate(UserDTO dto) {
		try {
			String sql = "UPDATE user SET mempw = ?, memaddr = ?, memphone = ?, mememail = ?, memname = ? WHERE memid = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMempw());
			pstmt.setString(2, dto.getMemaddr());
			pstmt.setString(3, dto.getMemphone());
			pstmt.setString(4, dto.getMememail());
			pstmt.setString(5, dto.getMemname());
			pstmt.setString(6, dto.getMemid());
			
			pstmt.executeUpdate();
			
			sql = "UPDATE post SET username = ? WHERE userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemname());
			pstmt.setString(2, dto.getMemid());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
