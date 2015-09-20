package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import secureUtil.makeMD5;

import DBbean.dbBean;

public class managerchkBean {

	protected managerBean mb;
	public managerchkBean(){
		
	}
	public managerchkBean(managerBean mBean){
		mb=mBean;
	}
	public managerBean getMb() {
		return mb;
	}
	public void setMb(managerBean mb) {
		this.mb = mb;
	}
	public boolean validate(dbBean conBean) throws NamingException, SQLException {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		String result="";
		try{
			conn=conBean.getConnection();
			stmt=conn.createStatement();
			String sql="select managerPass from manager where managerName = \""+mb.getManagerName()+"\"";
			rs=stmt.executeQuery(sql);
			while(rs.next())
				result+=rs.getString("managerPass");
		}finally{
			conBean.closeResultSet(rs);
			conBean.closeStatement(stmt);
			conBean.closeConnection(conn);
		}
		if(result.equals(makeMD5.Md5(mb.getManagerPass())))
			return true;
		else 
			return false;
		/*if(pasString.equals(mb.getManagerPass())){
			return true;
		}else{
			return false;
		}*/
	}
}
