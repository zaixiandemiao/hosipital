package HLcard;

import java.io.Serializable;

public class healcardAssign implements Serializable{
  
	private int healcardId;
	private int assignId;
	
	public healcardAssign() {
		// TODO Auto-generated constructor stub
	}
	public healcardAssign(int hc,int ai){
		this.healcardId = hc;
		this.assignId = ai;
	}
	
	public int getHealcardId() {
		return healcardId;
	}
	public void setHealcardId(int healcardId) {
		this.healcardId = healcardId;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	
	
}
