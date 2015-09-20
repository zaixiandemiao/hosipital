package assign;

import java.io.Serializable;
import java.sql.Date;

public class assignBean implements Serializable{

	private int assignId;
	private double treat;
	private String classTh;
	private int waitNum;
	private String departName;
	private int docTitle;
	private int docId;
	private Date dateTime;
	public assignBean() {
		// TODO Auto-generated constructor stub
	}
	public assignBean(int i,double t,String clth,int wn,String depart,int dt,int di,Date d){
		this.assignId =i;
		this.treat =t;
		this.classTh =clth;
		this.waitNum=wn;
		this.departName =depart;
		this.docTitle = dt;
		this.docId =di;
		this.dateTime = d;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public double getTreat() {
		return treat;
	}
	public void setTreat(double treat) {
		this.treat = treat;
	}
	public String getClassTh() {
		return classTh;
	}
	public void setClassTh(String classTh) {
		this.classTh = classTh;
	}
	public int getWaitNum() {
		return waitNum;
	}
	public void setWaitNum(int waitNum) {
		this.waitNum = waitNum;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public int getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(int docTitle) {
		this.docTitle = docTitle;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
