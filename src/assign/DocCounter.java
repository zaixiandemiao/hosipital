package assign;

public class DocCounter {

	private String depart;
	private int docTitle;
	private String docName;
	private double totalMoney;
	public DocCounter() {
		// TODO Auto-generated constructor stub
	}
	public DocCounter(String depart,int docTitle,String docName,double cost){
		this.depart = depart;
		this.docTitle = docTitle;
		this.docName = docName;
		this.totalMoney = cost;
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
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
}
