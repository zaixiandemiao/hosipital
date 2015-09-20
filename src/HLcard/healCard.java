package HLcard;

import java.io.Serializable;
/**
 * ÕïÁÆ¿¨µÄJavabean
 * @author Administrator
 *
 */
public class healCard implements Serializable {

	private int hcId;
	private String username;
	private String idCard;
	private String tel;
	private String sex;
	public healCard() {
		// TODO Auto-generated constructor stub
	}
	public healCard(int id,String user,String idCard,String telphone,String sex){
		this.hcId=id;
		this.username=user;
		this.idCard=idCard;
		this.tel=telphone;
		this.sex=sex;
	}
	public int getHcId() {
		return hcId;
	}
	public void setHcId(int hcId) {
		this.hcId = hcId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
