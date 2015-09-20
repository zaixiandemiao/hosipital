package doctor;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class docAssign implements Serializable {

	private String classTh;
	private Date   date;
	private int docId;
	private int assignId;
	private double cost;
	public docAssign() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 默认当天预约的安排表
	 * @param ct
	 * @param di
	 * @param ai
	 * @param cs
	 */
	public docAssign(String ct,int di,int ai,double cs){
		this.classTh = ct;
		java.util.Date test =new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String sDate = sdf.format(test);
	    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
	    this.date = mydate;
		this.docId = di;
		this.assignId =ai;
		this.cost =cs;
	}
	public docAssign(String ct,Date date,int di,int ai,double cs){
		this.classTh = ct;
		this.date = date;
		this.docId = di;
		this.assignId =ai;
		this.cost =cs;
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
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
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
	
	
}
