package userPart;

import java.io.Serializable;

public class User implements Serializable{

	private String username;
	private String passwd;
	private String realName;
	private String idCard;
	private String tel;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String s1,String s2,String s3,String s4,String s5){
		username=s1;
		passwd=s2;
		realName=s3;
		idCard=s4;
		tel=s5;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
