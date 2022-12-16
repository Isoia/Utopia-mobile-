package dto;

public class TagDTO {
	private int tagnum;
	private String tagname;
	
	public TagDTO(int tagnum, String tagname) {
		super();
		this.tagnum = tagnum;
		this.tagname = tagname;
	}

	public int getTagnum() {
		return tagnum;
	}

	public void setTagnum(int tagnum) {
		this.tagnum = tagnum;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
	
}
