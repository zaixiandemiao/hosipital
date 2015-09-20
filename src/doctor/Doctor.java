package doctor;

import java.io.Serializable;

public class Doctor implements Serializable{

	private int docId;
	private String docName;
	private int morLimit;
	private int aftLimit;
	private int docTitle;
	private String departName;
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	public Doctor(int d,String dn,int ml,int al,int dt,String di){
		docId=d;
		docName=dn;
		morLimit=ml;
		aftLimit=al;
		docTitle=dt;
		departName=di;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public int getMorLimit() {
		return morLimit;
	}
	public void setMorLimit(int morLimit) {
		this.morLimit = morLimit;
	}
	public int getAftLimit() {
		return aftLimit;
	}
	public void setAftLimit(int aftLimit) {
		this.aftLimit = aftLimit;
	}
	public int getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(int docTitle) {
		this.docTitle = docTitle;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
}
