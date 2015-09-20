package userPart;

import java.sql.Date;

public class userAppoint {

	private String username;
	private int assignId;
	private Date  datetime;
	private String ClassTh;
	private String depart;
	private int docTitle;
	private int waitNum;
	private double money;
	public userAppoint() {
		// TODO Auto-generated constructor stub
	}
	public userAppoint(String user,int assId,Date date,String cl,String depart,int docTitle,int wait,double cost){
		this.username = user;
		this.assignId = assId;
		this.datetime = date;
		this.ClassTh = cl;
		this.depart = depart;
		this.docTitle = docTitle;
		this.waitNum = wait;
		this.money = cost;
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
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getClassTh() {
		return ClassTh;
	}
	public void setClassTh(String classTh) {
		ClassTh = classTh;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public int getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(int docTitle) {
		this.docTitle = docTitle;
	}
	public int getWaitNum() {
		return waitNum;
	}
	public void setWaitNum(int waitNum) {
		this.waitNum = waitNum;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	
}
