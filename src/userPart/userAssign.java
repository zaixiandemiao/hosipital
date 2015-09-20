package userPart;

import java.io.Serializable;

public class userAssign implements Serializable{
  private String username;
  private int assignId;
  private String idcard;
  public userAssign() {
	// TODO Auto-generated constructor stub
}
  
  public userAssign(String username,int assignid,String idcard){
	  this.username  = username;
	  this.assignId =assignid;
	  this.idcard = idcard;
  }
  
  
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
  
}
