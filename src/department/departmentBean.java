package department;

import java.io.Serializable;

public class departmentBean implements Serializable{
    private int id;
	private String Name;
	private int docTitle;
	private double treatcost;
	public departmentBean() {
		// TODO Auto-generated constructor stub
	}
	public departmentBean(int id,String n,int dt,double tc){
		this.id=id;
		this.Name=n;
		this.docTitle=dt;
		this.treatcost=tc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(int docTitle) {
		this.docTitle = docTitle;
	}
	public double getTreatcost() {
		return treatcost;
	}
	public void setTreatcost(double treatcost) {
		this.treatcost = treatcost;
	}
	
}
