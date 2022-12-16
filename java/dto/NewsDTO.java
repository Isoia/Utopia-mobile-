package dto;

import java.sql.Date;

public class NewsDTO {
	private int newsId;
	private String newsImage;
	private Date newsDate;
	private String newsContent;
	private String newsTags;
	
	public NewsDTO(int newsId, String newsName, Date newsDate, String newsContent, String newsTags) {
		super();
		this.newsId = newsId;
		this.newsImage = newsName;
		this.newsDate = newsDate;
		this.newsContent = newsContent;
		this.newsTags = newsTags;
	}
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNewsImage() {
		return newsImage;
	}
	public void setNewsImage(String newsName) {
		this.newsImage = newsName;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsTags() {
		return newsTags;
	}
	public void setNewsTags(String newsTags) {
		this.newsTags = newsTags;
	}
	
	
}
