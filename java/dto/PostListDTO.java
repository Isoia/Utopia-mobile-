package dto;

public class PostListDTO {		//게시물 리스트 dto
	String title,mainimage,username,tag;
	int postnum,viewscount;
	
	public PostListDTO(int postnum, String title, String mainimage, String username, String tag, int viewscount) {
		this.postnum = postnum;
		this.title = title;
		this.mainimage = mainimage;
		this.username = username;
		this.tag = tag;
		this.viewscount = viewscount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMainimage() {
		return mainimage;
	}

	public void setMainimage(String mainimage) {
		this.mainimage = mainimage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getViewscount() {
		return viewscount;
	}

	public void setViewscount(int viewscount) {
		this.viewscount = viewscount;
	}
	
	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	
}
