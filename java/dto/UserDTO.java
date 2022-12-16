package dto;

public class UserDTO {
	private String memid;
	private String mempw;
	private String memname;
	private String memaddr;
	private String memphone;
	private String mememail;
	
	public UserDTO() {
		
	}
	public UserDTO(String memid, String mempw, String memname, String memaddr, String memphone, String mememail) {
		super();
		this.memid = memid;
		this.mempw = mempw;
		this.memname = memname;
		this.memaddr = memaddr;
		this.memphone = memphone;
		this.mememail = mememail;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getMempw() {
		return mempw;
	}

	public void setMempw(String mempw) {
		this.mempw = mempw;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public String getMemaddr() {
		return memaddr;
	}

	public void setMemaddr(String memaddr) {
		this.memaddr = memaddr;
	}

	public String getMemphone() {
		return memphone;
	}

	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}

	public String getMememail() {
		return mememail;
	}

	public void setMememail(String mememail) {
		this.mememail = mememail;
	}
	
	
	
}
