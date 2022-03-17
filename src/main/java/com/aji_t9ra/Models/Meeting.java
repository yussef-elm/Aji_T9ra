
package com.aji_t9ra.Models;

public class Meeting{
	
	private int id;
	private int user_id;
	private String link;
	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Meeting(int id, int user_id) {
		this.id = id;
		this.user_id = user_id;
		
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	
	
	
	

}
