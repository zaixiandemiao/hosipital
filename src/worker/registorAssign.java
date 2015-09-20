package worker;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class registorAssign implements Serializable{

	private String registorName;
	private int assignId;
	private double cost;
	private String classTh;
	private Date  date;
	public registorAssign() {
		// TODO Auto-generated constructor stub
	}
	public registorAssign(String name,int assId,double cost,String clth){
		this.registorName = name;
		this.assignId = assId;
		this.cost =cost;
		this.classTh = clth;
		java.util.Date test =new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String sDate = sdf.format(test);
	    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
	    this.date = mydate;
	}
	
	public String getRegistorName() {
		return registorName;
	}
	public void setRegistorName(String registorName) {
		this.registorName = registorName;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getClassTh() {
		return classTh;
	}
	public void setClassTh(String classTh) {
		this.classTh = classTh;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
