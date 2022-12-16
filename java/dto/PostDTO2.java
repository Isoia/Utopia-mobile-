package dto;

import java.util.Date;
import java.util.ArrayList;

public class PostDTO2 {		//게시글 테이블 dto
	private int postnum;		//게시글번호
	private Date nowdate;		//게시날짜
	private int viewscount;			//조회수
	private String username;	//사용자이름
	private String userid;		//사용자ID
	private String title;		//제목
	private String content;		//내용
	private String tag;			//태그
	private String mainimage;	//대표이미지
	private String imagelist;	//이미지리스트
	
	public PostDTO2(int postnum, Date nowdate, int viewscount, String username, String userid,
			String title, String content, String tag, String mainimage, String imagelist) {
		super();
		this.postnum = postnum;
		this.nowdate = nowdate;
		this.viewscount = viewscount;
		this.username = username;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.mainimage = mainimage;
		this.imagelist = imagelist;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public Date getNowdate() {
		return nowdate;
	}

	public void setNowdate(Date nowdate) {
		this.nowdate = nowdate;
	}

	public int getViewscount() {
		return viewscount;
	}

	public void setViewscount(int viewscount) {
		this.viewscount = viewscount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserID(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getImagelist() {
		return imagelist;
	}

	public void setImagelist(String imagelist) {
		this.imagelist = imagelist;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setMainimage(String mainimage) {
		this.mainimage = mainimage;
	}

	public String getMainimage() {
		return mainimage;
	}

	public void setMainImage(String mainimage) {
		this.mainimage = mainimage;
	}
}
