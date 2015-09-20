package assign;

public class registorCounter {
	private String registor;
	private double cost;
	public registorCounter() {
		// TODO Auto-generated constructor stub
	}
	public registorCounter(String registor,double treat){
		this.registor = registor;
		this.cost = treat;
	}
	public String getRegistor() {
		return registor;
	}
	public void setRegistor(String registor) {
		this.registor = registor;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	

}
