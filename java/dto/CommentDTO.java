package dto;

import java.util.Date;

public class CommentDTO {
	private int comnum;
	private int postnum;
	private String userid;
	private String username;
	private Date comdate;
	private String comcontent;
	
	public CommentDTO(int comnum, int postnum, String userid, String username, Date comdate,String comcontent) {
		super();
		this.comnum = comnum;
		this.postnum = postnum;
		this.userid = userid;
		this.username = username;
		this.comdate = comdate;
		this.comcontent = comcontent;
	}

	public int getComnum() {
		return comnum;
	}

	public void setComnum(int comnum) {
		this.comnum = comnum;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getComdate() {
		return comdate;
	}

	public void setComdate(Date comdate) {
		this.comdate = comdate;
	}

	public String getComcontent() {
		return comcontent;
	}

	public void setComcontent(String comcontent) {
		this.comcontent = comcontent;
	}
	
	
}
