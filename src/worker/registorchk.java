package worker;

import java.sql.SQLException;

import secureUtil.makeMD5;

import DBbean.dbBean;

public class registorchk {

	protected registorBean rBean;
	public registorchk() {
		// TODO Auto-generated constructor stub
	}
	public registorchk(registorBean r){
		rBean=r;
	}
	public registorBean getrBean() {
		return rBean;
	}
	public void setrBean(registorBean rBean) {
		this.rBean = rBean;
	}
	public boolean validate(registorControl rc){
		try {
			registorBean tmpBean = rc.getRegistorBean(rBean.getRegistorName());
			if(makeMD5.Md5(rBean.getRegistorPass()).equals(tmpBean.getRegistorPass()))
				return true;
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
}
