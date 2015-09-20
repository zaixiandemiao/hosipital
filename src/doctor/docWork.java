package doctor;

import java.io.Serializable;

/**
 * 医生安排表的JavaBean ，用于对该表操作
 * @author Administrator
 *
 */
public class docWork implements Serializable{

	private int id;
	private String docName;
	private String mon;
	private String tues;
	private String wed;
	private String thurs;
	private String fri;
	
	public docWork() {
		// TODO Auto-generated constructor stub
	}
	public docWork(int id,String docName,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday){
		this.id=id;
		this.docName=docName;
		this.mon=Monday;
		this.tues=Tuesday;
		this.wed = Wednesday;
		this.thurs = Thursday;
		this.fri  = Friday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getTues() {
		return tues;
	}
	public void setTues(String tues) {
		this.tues = tues;
	}
	public String getWed() {
		return wed;
	}
	public void setWed(String wed) {
		this.wed = wed;
	}
	public String getThurs() {
		return thurs;
	}
	public void setThurs(String thurs) {
		this.thurs = thurs;
	}
	public String getFri() {
		return fri;
	}
	public void setFri(String fri) {
		this.fri = fri;
	}
	/**
	 * 判断是否在职
	 * @param day
	 * @param time
	 * @return
	 */
	public boolean onWork(int day,String time){
		switch (day) {
			case 1:
				if(this.getMon().equals(time))
					return true;
				break;
			case 2:
				if(this.getTues().equals(time))
					return true;		
				break;
			case 3:
				if(this.getWed().equals(time))
					return true;
				break;
			case 4:
				if(this.getThurs().equals(time))
					return true;
				break;
			case 5:
				if(this.getFri().equals(time))
					return true;
				break;
		default:
			break;
		}
		return false;
	}
	
	
}
