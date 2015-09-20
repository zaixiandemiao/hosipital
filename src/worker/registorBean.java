package worker;

import java.io.Serializable;

public class registorBean implements Serializable{
	private String registorName;
	private String registorPass;
	public registorBean() {
		// TODO Auto-generated constructor stub
	}
	public registorBean(String rn,String rp){
		registorName=rn;
		registorPass=rp;
	}
	public String getRegistorName() {
		return registorName;
	}
	public void setRegistorName(String registorName) {
		this.registorName = registorName;
	}
	public String getRegistorPass() {
		return registorPass;
	}
	public void setRegistorPass(String registorPass) {
		this.registorPass = registorPass;
	}
	

}
