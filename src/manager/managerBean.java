package manager;

import java.io.Serializable;

public class managerBean implements Serializable{

	private String managerName;
	private String managerPass;
	private boolean canSelect;
	private boolean canInsert;
	private boolean canUpdate;
	private boolean canDel;
	
	public managerBean(){
		
	}
	public managerBean(String m1,String m2){
		this.managerName=m1;
		this.managerPass=m2;
	}
	public managerBean(String m1,String m2,int b1,int b2,int b3,int b4){
		this.managerName=m1;
		this.managerPass=m2;
		this.canSelect=(b1==1?true:false);
		this.canInsert=(b2==1?true:false);
		this.canUpdate=(b3==1?true:false);
		this.canDel=(b4==1?true:false);
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPass() {
		return managerPass;
	}
	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}
	public boolean isCanSelect() {
		return canSelect;
	}
	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
	}
	public boolean isCanInsert() {
		return canInsert;
	}
	public void setCanInsert(boolean canInsert) {
		this.canInsert = canInsert;
	}
	public boolean isCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}
	public boolean isCanDel() {
		return canDel;
	}
	public void setCanDel(boolean canDel) {
		this.canDel = canDel;
	}
	
}
