package userPart;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import secureUtil.makeMD5;

public class UserCheck {
	protected User user;
	public UserCheck(){
		
	}
	public UserCheck(User user){
	     this.user=user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean validate(){
		String username=user.getUsername();
		String passwd=user.getPasswd();
		Context ctx=null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataSource ds=null;
		try {
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String chkstr="";
		try{
			conn=ds.getConnection();
			stmt=conn.createStatement();
			String sql="select `user`.PassWord from `user` where `user`.UserName = \""+username+"\"";
			rs=stmt.executeQuery(sql);
			if(rs.next())
				chkstr=rs.getString(1);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(chkstr.equals(makeMD5.Md5(passwd))){
			return true;
		}else{
			return false;
		}
		    
	}

}
